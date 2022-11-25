import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

// 휴대폰 번호
let reg_mobile = /^\d{3}-\d{3,4}-\d{4}$/;

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
// 서버에 휴대폰 번호 전송.
export const findPasswordController = async (userInfo) => {
  console.log(userInfo);
  //휴대폰 번호 유효성 확인
  if (!userInfo) {
    window.alert('휴대폰 번호를 입력하세요');
    return;
  }
  if (!reg_mobile.test(userInfo)) {
    window.alert(
      '-를 포함하여 휴대폰 번호 11자리를 입력해주세요. \n EX ) 010-1234-5678'
    );
    return;
  }
  try {
    // const submitResult = await axios.post(
    //   `${REACT_APP_API_URL}users`,
    //   userInfo
    // );
    // //이메일 정보 받아옴
    // console.log(submitResult);
    // // alert로 보여주기
    // return submitResult;
  } catch (error) {
    return error.response.data;
  }
};
// 비밀번호 변경
export const emailValidationCheck = async (email) => {
  console.log(JSON.stringify(email));
  try {
    const validationRequest = await axios.post(
      `${REACT_APP_API_URL}mail/confirm`,
      { email }
    );
    console.log(validationRequest);
    return validationRequest;
  } catch (error) {
    return false;
  }
};
