import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] =
  window.sessionStorage.getItem('accesstoken');

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 포인트 조회
export const getPoint = async () => {
  try {
    console.log('포인트 조회 내부');
    const res = await axios.get(`${REACT_APP_API_URL}users11111`);
    console.log(res);
    return res;
  } catch (error) {
    return error.response.data;
  }
};

// 포인트로 결제
export const paymentPoint = async () => {
  try {
    console.log('포인트 결제 내부');
    const res = await axios.get(`${REACT_APP_API_URL}users11111`);
    console.log(res);
    return res;
  } catch (error) {
    return error.response.data;
  }
};
