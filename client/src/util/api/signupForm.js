import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
// 서버에 회원가입 입력 데이터 전송
export const submitForm = async (userInfo) => {
  console.log(userInfo);
  try {
    const submitResult = await axios.post(
      `${REACT_APP_API_URL}users`,
      userInfo
    );
    console.log(submitResult);
    return submitResult;
  } catch (error) {
    return error.response.data;
  }
};
// 이메일 유효성 인증 검사 요청
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
