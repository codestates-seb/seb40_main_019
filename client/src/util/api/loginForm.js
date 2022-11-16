import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 서버에 로그인 입력 데이터 전송
export const submitForm = async (userInfo) => {
  console.log(userInfo);
  try {
    const submitResult = await axios.post(
      `${REACT_APP_API_URL}users/login`,
      userInfo
    );
    console.log(submitResult);
    return submitResult;
  } catch (error) {
    return error.response.data;
  }
};

// 구매자 체험 로그인
// 받아온 아이디 이메일로 로그인 진행.
export const guestLogin = async () => {
  try {
    const userData = await axios.post(`${REACT_APP_API_URL}users/test/user`);
    console.log(userData);
    return userData;
  } catch (error) {
    return false;
  }
};

// 판매자 체험 로그인
// 받아온 아이디 이메일로 로그인 진행.
export const sellerLogin = async () => {
  try {
    const userData = await axios.post(`${REACT_APP_API_URL}users/test/admin`);
    console.log(userData);
    return userData;
  } catch (error) {
    return false;
  }
};
