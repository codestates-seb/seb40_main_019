import { useEffect } from 'react';
import { submitPaymentData } from '../../../util/api/payment';
export default function Success() {
  useEffect(() => {
    const orderId = new URL(window.location.href).searchParams.get('orderId');
    const paymentKey = new URL(window.location.href).searchParams.get(
      'paymentKey'
    );
    const amount = new URL(window.location.href).searchParams.get('amount');
    const point = JSON.parse(window.sessionStorage.getItem('point'));
    // console.log(orderId);
    // console.log(paymentKey);
    // console.log(amount);
    // console.log(point);
    let data = {
      orderId: orderId,
      paymentKey: paymentKey,
      amount: amount,
      point: point,
    };
    submitPaymentData(data);
  }, []);

  return (
    <>
      <div>Success</div>
    </>
  );
}
