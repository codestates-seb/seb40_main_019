import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

// 휴대폰 번호
let reg_mobile = /^\d{3}-\d{3,4}-\d{4}$/;
let reg_pw2 = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/;
// eslint-disable-next-line no-useless-escape
let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
// const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 서버에 휴대폰 번호 전송.
export const findEmailController = async (phone, setModalOn, setModalText) => {
  //휴대폰 번호 유효성 확인
  if (!phone) {
    setModalOn(true);
    setModalText('휴대폰 번호를 입력하세요');
    return;
  }
  if (!reg_mobile.test(phone)) {
    setModalOn(true);
    setModalText(
      '-를 포함하여 휴대폰 번호 11자리를 입력해주세요. \n EX ) 010-1234-5678'
    );
    return;
  }
  try {
    const submitResult = await axios.post(`${REACT_APP_API_URL}users/find-id`, {
      phoneNumber: phone,
    });
    //이메일 정보 받아옴
    if (submitResult.status === 200) {
      setModalOn(true);
      setModalText(`아이디: ${submitResult.data.email}`);
    }
    return submitResult;
  } catch (error) {
    if (error.response.data.status === 404) {
      setModalOn(true);
      setModalText('이메일 찾기 실패');
    }
  }
};

// 이메일 인증코드 전송
export const findPasswordSendEmail = async (
  email,
  setModalOn,
  setModalText
) => {
  if (!email) {
    setModalOn(true);
    setModalText('이메일을 입력하세요.');
    return;
  }
  if (!emailExptext.test(email)) {
    setModalOn(true);
    setModalText('유효하지 않은 형식의 이메일 주소입니다.');
    return;
  } else {
    try {
      const validationRequest = await axios.post(
        `${REACT_APP_API_URL}mail/registered`,
        { email }
      );

      return validationRequest;
    } catch (error) {
      if (error.response.data.status === 404) {
        setModalOn(true);
        setModalText('이메일 찾기 실패');
      } else {
        setModalOn(true);
        setModalText('이메일 찾기 에러');
      }
    }
  }
};

// 비밀번호 변경 요청
export const changePasssword = async (
  email,
  password,
  setModalOn,
  setModalText
) => {
  if (!password) {
    setModalOn(true);
    setModalText('새로운 비밀번호를 입력하세요.');
    return;
  }
  if (!reg_pw2.test(password)) {
    setModalOn(true);
    setModalText(
      '영문 대소문자/숫자/특수문자를 포함한 8자~16자 사이의 비밀번호를 입력해주세요'
    );
    return;
  }

  try {
    const res = await axios.post(`${REACT_APP_API_URL}users/change-password`, {
      email,
      password,
    });
    return res;
  } catch (error) {
    if (error.response.data.status === 404) {
      setModalOn(true);
      setModalText('비밀번호 변경 실패');
    }
  }
};
