import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

//주문생성- 장바구니
export const handleOrderCart = async (data) => {
  console.log(data);
  try {
    const res = await axios.post(`${REACT_APP_API_URL}orders/cart`, data);
    console.log(res);
    if (res.status === 201) {
      return res.data;
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};

//주문생성- 단품
export const handleOrderSingle = async (data) => {
  console.log(data);
  try {
    const res = await axios.post(`${REACT_APP_API_URL}orders`, data);
    console.log(res);
    if (res.status === 201) {
      return res.data;
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};

//주문 발송처리
export const handleDelivery = async (orderId) => {
  try {
    const res = await axios.patch(
      `${REACT_APP_API_URL}orders/status/${orderId}`
    );
    if (res.status === 201) {
      console.log(res.data);
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};
