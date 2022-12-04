import '../css/orderMobileButton.scss';
import { useSelector } from 'react-redux';
import ModalOk from '../../../components/modal/js/ModalOk';
import { useState } from 'react';

export default function OrderMobileButton({ totalPrice, myPoint, setModal }) {
  const user = useSelector((state) => state.user);
  const [modalOkOn, setModalOkOn] = useState(false);
  const [modalOkText, setModalOkText] = useState('');

  const payment = () => {
    if (user.userRole === '') {
      setModalOkOn(true);
      setModalOkText('로그인이 필요한 서비스입니다');
      return;
    }
    if (user.userRole === 'ROLE_ADMIN_TEST' || user.userRole === 'ROLE_ADMIN') {
      setModalOkOn(true);
      setModalOkText('판매자는 이용할 수 없습니다.');
      return;
    }
    if (totalPrice === 0) {
      setModalOkOn(true);
      setModalOkText('선택한 상품이 없습니다.');
      return;
    }
    if (myPoint < totalPrice) {
      setModalOkOn(true);
      setModalOkText('포인트가 부족합니다');
      return;
    }
    setModal(true);
  };

  return (
    <div className="mobileButtonContainer">
      <div className="buttonBox">
        <button className="priceBtn">{totalPrice}원</button>
        <button onClick={payment}>포인트 결제</button>
      </div>
      <ModalOk
        setModalOn={setModalOkOn}
        modalOn={modalOkOn}
        modalText={modalOkText}
      />
    </div>
  );
}
