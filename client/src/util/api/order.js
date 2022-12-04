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
export const handleOrderCart = async (data, setModalOkText, setModalOkOn) => {
  if (!reg_name1.test(data.receiverName)) {
    setModalOkOn(true);
    setModalOkText('올바른 이름을 입력해주세요');
    return;
  }

  if (!reg_mobile.test(data.receiverPhone)) {
    setModalOkOn(true);
    setModalOkText(
      '-를 포함하여 휴대폰 번호 11자리를 입력해주세요. \n EX ) 010-1234-5678'
    );
    return;
  }

  if (!data.receiverAddress) {
    setModalOkOn(true);
    setModalOkText('배송지를 입력해주세요.');
    return;
  }

  if (!data.receiverZipcode) {
    setModalOkOn(true);
    setModalOkText('우편번호를 입력해주세요');
    return;
  }

  try {
    const res = await axios.post(`${REACT_APP_API_URL}orders/cart`, data);
    if (res.status === 201) {
      return res.data;
    }
  } catch (error) {
    return error;
  }
};

//주문생성- 단품
export const handleOrderSingle = async (data, setModalOkText, setModalOkOn) => {
  if (!reg_name1.test(data.receiverName)) {
    setModalOkOn(true);
    setModalOkText('올바른 이름을 입력해주세요');
    return;
  }

  if (!reg_mobile.test(data.receiverPhone)) {
    setModalOkOn(true);
    setModalOkText(
      '-를 포함하여 휴대폰 번호 11자리를 입력해주세요. \n EX ) 010-1234-5678'
    );
    return;
  }

  if (!data.receiverAddress) {
    setModalOkOn(true);
    setModalOkText('배송지를 입력해주세요.');
    return;
  }

  if (!data.receiverZipcode) {
    setModalOkOn(true);
    setModalOkText('우편번호를 입력해주세요');
    return;
  }

  try {
    const res = await axios.post(`${REACT_APP_API_URL}orders`, data);
    if (res.status === 201) {
      return res.data;
    }
  } catch (error) {
    return error;
  }
};

//주문발송알람
export const handleDeliveryAlert = async (setModalText, setModalOn) => {
  setModalOn(true);
  setModalText('발송하시겠습니까?');
};

//주문 발송처리
export const handleDelivery = async (orderId) => {
  try {
    const res = await axios.patch(
      `${REACT_APP_API_URL}orders/status/${orderId}`
    );
    if (res.status === 200) {
      location.reload();
    }
  } catch (error) {
    return error;
  }
};
