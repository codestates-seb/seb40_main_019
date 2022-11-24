import axios from 'axios';
import { setCookie } from '../cookie/cookie';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
console.log(REACT_APP_API_URL);

// 서버에 로그인 입력 데이터 전송
export const submitForm = async (userInfo) => {
  console.log(userInfo);
  console.log('로그인');
  try {
    const res = await axios.post(`${REACT_APP_API_URL}users/login`, userInfo);
    console.log(res);
    if (res.status === 200) {
      // 엑세스 토큰 세션 스토리지에 저장
      let accesstoken = res.headers.get('authorization');
      window.sessionStorage.setItem('accesstoken', JSON.stringify(accesstoken));
      // 리프레시 토큰 쿠키에 저장
      let refreshtoken = res.headers.get('refreshtoken');
      setCookie('refreshtoken', refreshtoken, {
        path: '/',
        secure: true,
        sameSite: 'none',
        expires: new Date(Date.now() + 1000 * 60 * 60 * 24 * 7),
      });
      let userData = res.data;

      window.sessionStorage.setItem('accesstoken', JSON.stringify(accesstoken));
      window.sessionStorage.setItem('userData', JSON.stringify(userData));
      window.location.replace('/');
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};
export const userLogout = async () => {
  console.log('로그아웃');

  try {
    const res = await axios.delete(`${REACT_APP_API_URL}users/logout`, {
      headers: {
        Authorization: JSON.parse(window.sessionStorage.getItem('accesstoken')),
        // 'ngrok-skip-browser-warning': '69420',
      },
    });
    console.log(res);
    if (res.status === 200) {
      // 스토리지 데이터 삭제.
      console.log('스토리지 데이터 삭제');
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
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};

// 구매자 체험 로그인
// 받아온 아이디 이메일로 로그인 진행.
export const guestLogin = async () => {
  try {
    const res = await axios.get(`${REACT_APP_API_URL}users/test/user`);
    console.log(res);
    if (res.status === 200) {
      let userInfo = res.data;
      console.log(userInfo);
      submitForm(userInfo);
    }
  } catch (error) {
    return false;
  }
};

// 판매자 체험 로그인
// 받아온 아이디 이메일로 로그인 진행.
export const sellerLogin = async () => {
  try {
    const res = await axios.get(`${REACT_APP_API_URL}users/test/admin`);
    console.log(res);
    if (res.status === 200) {
      let userInfo = res.data;
      console.log(userInfo);
      submitForm(userInfo);
    }
  } catch (error) {
    return false;
  }
};
