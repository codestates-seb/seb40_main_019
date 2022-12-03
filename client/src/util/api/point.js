import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 포인트 조회
export const getPoint = async () => {
  try {
    const res = await axios.get(`${REACT_APP_API_URL}point`);
    return res;
  } catch (error) {
    return error.response.data;
  }
};

// 포인트로 결제
export const paymentPoint = async (orderId) => {
  try {
    const res = await axios.post(`${REACT_APP_API_URL}point/${orderId}`);
    if (res.status === 200) {
      return res;
    }
    // return res;
  } catch (error) {
    return error.response.data;
  }
};
