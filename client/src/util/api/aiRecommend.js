import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);

const check = /^[0-9]+$/;

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 유저 정보 상세 조회
export const recommendProduct = async (data) => {
  console.log(data.age);
  if (!check.test(data.age)) {
    window.alert('숫자만 입력 가능합니다.');
    return;
  }
  if (data.age > 30) {
    window.alert('선택 가능한 최대 나이는 30살 입니다.');
    return;
  }

  if (!check.test(data.weight)) {
    window.alert('숫자만 입력 가능합니다.');
    return;
  }
  if (data.weight > 155) {
    window.alert('선택 가능한 최대 몸무게는 155kg 입니다.');
    return;
  }

  try {
    console.log('유저 정보 상세조회 내부');
    const res = await axios.get(`${REACT_APP_API_URL}products/random`);
    return res.data;
  } catch (error) {
    return error.response.data;
  }
};
