import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 서버에 로그인 입력 데이터 전송
export const submitForm = async (userInfo) => {
  console.log(userInfo);
  console.log('로그인');
  try {
    const res = await axios.post(`${REACT_APP_API_URL}users/login`, userInfo);
    console.log(res);
    if (res.status === 200) {
      let jwtToken = res.headers.get('authorization');
      let userData = res.data;
      window.sessionStorage.setItem('jwtToken', JSON.stringify(jwtToken));
      window.sessionStorage.setItem('userData', JSON.stringify(userData));
      // window.location.replace('/');
    }
  } catch (error) {
    return error.response.data;
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
