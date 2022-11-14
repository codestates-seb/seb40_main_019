import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

// 서버에 회원가입 입력 데이터 전송
export const submitForm = async (userInfo) => {
  try {
    const submitResult = await axios.post(
      'http://localhost:3000/',
      JSON.stringify(userInfo)
    );
    console.log(submitResult);
    return true;
  } catch (errpr) {
    return false;
  }
};
// 이메일 유효성 인증 검사 요청
export const emailValidationCheck = async () => {
  try {
    const validationRequest = await axios.post('http://localhost:3000/');
    console.log(validationRequest);
    return validationRequest;
  } catch (error) {
    return false;
  }
};
// 이메일 유효성 검사 인증번호 전송
export const submitEmailValidation = async () => {
  // 응답으로 데이터 받아오기
  // 받아 온 데이터 리덕스 or 스토리지에 저장
  try {
    const submitResult = await axios.post('http://localhost:3000/');
    console.log(submitResult);
    return submitResult;
  } catch (errpr) {
    return false;
  }
};
