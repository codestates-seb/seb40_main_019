import { useEffect } from 'react';
import { addPoint } from '../../../util/api/payment';
import Loading from '../../../components/loading/js/Loading';
import { useNavigate } from 'react-router-dom';
import '../css/success.scss';

export default function Success() {
  const navigate = useNavigate();
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
    let res = addPoint(data);
    res.then((res) => {
      if (res.status === 200) {
        window.alert('결제완료');
        navigate('/mypage/point');
      }
    });
  }, []);

  return (
    <>
      <div className="successInner">
        <Loading />
      </div>
    </>
  );
}
