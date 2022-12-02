import axios from 'axios';
import { setCookie } from '../cookie/cookie';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 한글 이름만
let reg_name1 = /^[가-힣]+$/;
// 문자와 특수문자 조합의 6~24 자리
let reg_pw2 = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/;
// 휴대폰 번호
let reg_mobile = /^\d{3}-\d{3,4}-\d{4}$/;

// 유저 정보 상세 조회
export const getUserInfo = async () => {
  try {
    console.log('유저 정보 상세조회 내부');
    const res = await axios.get(`${REACT_APP_API_URL}users`);
    return res.data;
  } catch (error) {
    return error.response.data;
  }
};
// 유저 정보 수정
export const editUserInfo = async (data) => {
  let check = window.confirm('회원 정보를 수정하시겠습니까?');
  if (check) {
    // 닉네임 유효성 확인
    if (data.nickname.length < 2 || data.nickname.length > 16) {
      window.alert('2자~16자 사이의 닉네임을 입력해주세요');
      return;
    }

    // 현재 비밀번호 확인
    if (data.curPassword === '') {
      window.alert('현재 비밀번호를 입력해주세요');
      // 비밀번호 요청 API 필요
      return;
    }
    // 현재 비밀번호 일치 확인
    if (data.curPassword !== undefined) {
      // 유저 비밀번호 확인
      let check = await checkCurPassword(data.curPassword);
      if (!check.data) {
        window.alert('현재 비밀번호가 일치하지 않습니다.');
        return;
      }
    }
    // 새 비밀번호 입력
    if (data.newPassword === '') {
      window.alert('새로운 비밀번호를 입력해주세요');
      return;
    }
    if (data.newPassword !== undefined && !reg_pw2.test(data.newPassword)) {
      window.alert(
        '영문 대소문자/숫자/특수문자를 포함한 8자~16자 사이의 비밀번호를 입력해주세요'
      );
      return;
    }

    // 새 비밀번호 확인
    if (data.newPasswordConfirm === '') {
      window.alert('새 비밀번호 확인을 입력해주세요');
      return;
    }
    if (data.newPassword !== data.newPasswordConfirm) {
      window.alert('새 비밀번호 확인이 새 비밀번호와 일치하지 않습니다.');
      return;
    }

    // 이름 유효성 확인
    if (!reg_name1.test(data.username)) {
      window.alert('올바른 이름을 입력해주세요');
      return;
    }

    // 휴대폰 번호 유효성 확인
    if (!reg_mobile.test(data.phone)) {
      window.alert(
        '-를 포함하여 휴대폰 번호 11자리를 입력해주세요. \n EX ) 010-1234-5678'
      );
      return;
    }
    try {
      console.log('유저 정보 수정 내부');
      console.log(data);
      console.log(REACT_APP_API_URL);
      let temp = {
        nickname: data.nickname,
        password: data.newPassword,
        profileImage: data.profileImage,
        address: data.address,
        zipCode: data.zipCode,
        phone: data.phone,
        username: data.username,
      };
      const res = await axios.patch(`${REACT_APP_API_URL}users`, temp);
      console.log(res);
      if (res.status === 200) {
        window.location.replace('/mypage/user');
      }
      // return res;
      window.alert('회원 정보 수정 완료.');
    } catch (error) {
      return error.response.data;
    }
  }
};
// 유저 계정 삭제
export const deleteUserAccount = async () => {
  let check = window.confirm('회원 탈퇴를 진행하시겠습니까?');
  if (check) {
    try {
      console.log('유저 정보 삭제 내부');
      const res = await axios.delete(`${REACT_APP_API_URL}users`);
      console.log(res);
      if (res.status === 200) {
        window.sessionStorage.removeItem('accesstoken');
        window.sessionStorage.removeItem('userData');
        // 리프레시 토큰 삭제
        setCookie('refreshtoken', '', {
          path: '/',
          secure: true,
          sameSite: 'none',
          expires: new Date(Date.now() - 1),
        });
        window.location.replace('/');
        window.alert('회원 탈퇴 완료.');
      }
      return;
    } catch (error) {
      console.error(error);
      window.alert('회원 탈퇴 실패.');
      return;
    }
  }
};
// 비밀번호 일치 확인
export const checkCurPassword = async (password) => {
  try {
    console.log('비밀번호 확인요청');
    const res = await axios.post(`${REACT_APP_API_URL}users/password/confirm`, {
      password,
    });
    console.log(res);
    return res;
  } catch (error) {
    return error.response.data;
  }
};
