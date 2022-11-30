import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 한글 이름만
let reg_name1 = /^[가-힣]+$/;
// 휴대폰 번호
let reg_mobile = /^\d{3}-\d{3,4}-\d{4}$/;

//주문생성- 장바구니
export const handleOrderCart = async (data) => {
  if (!reg_name1.test(data.receiverName)) {
    window.alert('올바른 이름을 입력해주세요');
    return;
  }

  if (!reg_mobile.test(data.receiverPhone)) {
    window.alert(
      '-를 포함하여 휴대폰 번호 11자리를 입력해주세요. \n EX ) 010-1234-5678'
    );
    return;
  }

  if (!data.receiverAddress) {
    window.alert('배송지를 입력해주세요.');
    return;
  }

  if (!data.receiverZipcode) {
    window.alert('우편번호를 입력해주세요');
    return;
  }

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
  if (!reg_name1.test(data.receiverName)) {
    window.alert('올바른 이름을 입력해주세요');
    return;
  }

  if (!reg_mobile.test(data.receiverPhone)) {
    window.alert(
      '-를 포함하여 휴대폰 번호 11자리를 입력해주세요. \n EX ) 010-1234-5678'
    );
    return;
  }

  if (!data.receiverAddress) {
    window.alert('배송지를 입력해주세요.');
    return;
  }

  if (!data.receiverZipcode) {
    window.alert('우편번호를 입력해주세요');
    return;
  }

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
