import '../css/orderMobileButton.scss';
import { useSelector } from 'react-redux';

export default function OrderMobileButton({ totalPrice, myPoint, setModal }) {
  const user = useSelector((state) => state.user);

  const payment = () => {
    if (user.userRole === '') {
      window.alert('로그인이 필요한 서비스입니다');
      return;
    }
    if (user.userRole === 'ROLE_ADMIN_TEST' || user.userRole === 'ROLE_ADMIN') {
      window.alert('판매자는 이용할 수 없습니다.');
      return;
    }
    if (myPoint < totalPrice + 3000) {
      window.alert('포인트가 부족합니다');
      return;
    }
    setModal(true);
  };

  return (
    <div className="mobileButtonContainer">
      <div className="buttonBox">
        <button className="priceBtn">{totalPrice + 3000}원</button>
        <button onClick={payment}>포인트 결제</button>
      </div>
    </div>
  );
}
