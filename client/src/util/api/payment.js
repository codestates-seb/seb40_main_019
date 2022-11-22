import { loadTossPayments } from '@tosspayments/payment-sdk';
import axios from 'axios';

axios.defaults.withCredentials = true;

const clientKey = process.env.REACT_APP_PAYMENT_CLIENT_KEY;
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 토스페이 결제
// 포인트 선택하고 충전하기 누르면 세션 스토리지에 충전할 포인트 저장.
export const tossPay = (name) => {
  //orderId가 필요해서 만든 랜덤 아이디값
  const random = new Date().getTime() + Math.random();
  const randomId = btoa(random);

  loadTossPayments(clientKey).then((tossPayments) => {
    // 카드 결제 메서드 실행
    try {
      tossPayments.requestPayment(`카드`, {
        amount: 1, // 결제되는 금액입니다.
        orderId: `${randomId}`, // 상점에서 주문 건을 구분하기 위해 발급한 고유 ID입니다. 문자열이어야 합니다.
        orderName: `포인트결제`, // 결제에 대한 주문명입니다. 예를 들면 생수 외 1건 같은 형식입니다. 최대 길이는 100자입니다.
        customerName: `${name}`, // 고객의 이름입니다. 최소 1글자 이상 최대 10글자 이하여야 합니다.
        successUrl: 'http://localhost:3000/payment/success', // 성공시 리다이렉트 주소
        failUrl: 'http://localhost:3000/payment/failed', // 실패시 리다이렉트 주소
        flowMode: 'DIRECT',
        easyPay: '토스페이',
      });
    } catch (error) {
      console.log(error);
      if (error.code === 'USER_CANCEL') {
        // 결제 고객이 결제창을 닫았을 때 에러 처리
        window.alert('닫음');
      } else if (error.code === 'INVALID_CARD_COMPANY') {
        console.log('error');
      }
    }
  });
};

// 결제 정보 서버로 전송.
export const submitPaymentData = async (data) => {
  console.log('결제 데이터 전송 함수');
  console.log(data);
  try {
    const res = await axios.post(`${REACT_APP_API_URL}point`, data);
    console.log(res);
    if (res.status === 200) {
      console.log('결제 성공');
    }
    console.log('결제 데이터 전송 함수 종료');
  } catch (error) {
    console.error(error);
    return error;
  }
};