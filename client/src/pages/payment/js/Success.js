import { useEffect } from 'react';
import { addPoint } from '../../../util/api/payment';
export default function Success() {
  useEffect(() => {
    const orderId = new URL(window.location.href).searchParams.get('orderId');
    const paymentKey = new URL(window.location.href).searchParams.get(
      'paymentKey'
    );
    const amount = new URL(window.location.href).searchParams.get('amount');
    // console.log(orderId);
    // console.log(paymentKey);
    // console.log(amount);
    // console.log(point);
    let data = {
      orderId: orderId,
      paymentKey: paymentKey,
      amount: amount,
    };
    addPoint(data);
  }, []);

  return (
    <>
      <div>Success</div>
    </>
  );
}
