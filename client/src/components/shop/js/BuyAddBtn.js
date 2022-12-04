import '../css/buyAddBtn.scss';
import { useSelector } from 'react-redux';
import ModalOk from '../../modal/js/ModalOk';
import ModalMove from '../../modal/js/ModalMove';
import { useState } from 'react';

//BUY NOW 클릭하면, 단품으로 결제
//ADD CART 클릭하면, 장바구니 상품 갯수가 증가
export default function BuyAddBtn({ product, count, setModal, myPoint }) {
  const [modalOkOn, setModalOkOn] = useState(false);
  const [modalOkText, setModalOkText] = useState('');
  const [modalMoveOn, setModalMoveOn] = useState(false);
  const [modalMoveText, setModalMoveText] = useState('');

  const user = useSelector((state) => state.user);

  const addToCart = () => {
    if (user.userRole === 'ROLE_ADMIN_TEST' || user.userRole === 'ROLE_ADMIN') {
      setModalOkOn(true);
      setModalOkText('판매자는 이용할 수 없습니다.');
      return;
    }

    let data = JSON.parse(window.localStorage.getItem('cartItem'));

    if (data[product.productId]) {
      data[product.productId] = {
        ...data[product.productId],
        count: data[product.productId].count + count,
      };
    } else {
      data[product.productId] = {
        titleImg: product.titleImg,
        productName: product.productName,
        price: product.price,
        productId: product.productId,
        count: count,
        check: true,
      };
    }

    window.localStorage.setItem('cartItem', JSON.stringify(data));
    setModalMoveOn(true);
    setModalMoveText(
      '장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?'
    );
  };

  const buyBtun = () => {
    if (user.userRole === '') {
      setModalOkOn(true);
      setModalOkText('로그인이 필요한 서비스입니다.');
      return;
    }
    if (user.userRole === 'ROLE_ADMIN_TEST' || user.userRole === 'ROLE_ADMIN') {
      setModalOkOn(true);
      setModalOkText('판매자는 이용할 수 없습니다.');
      return;
    }
    if (myPoint < product.price * count) {
      setModalOkOn(true);
      setModalOkText('포인트가 부족합니다');
      return;
    }
    setModal(true);
  };

  return (
    <>
      <div className="buttonContainer">
        <button onClick={buyBtun} className="buyBtn">
          BUY NOW
        </button>
        <button onClick={addToCart} className="addBtn">
          ADD CART
        </button>
      </div>
      <ModalOk
        setModalOn={setModalOkOn}
        modalOn={modalOkOn}
        modalText={modalOkText}
      />
      <ModalMove
        setModalOn={setModalMoveOn}
        modalOn={modalMoveOn}
        modalText={modalMoveText}
        link={'/product/order'}
      />
    </>
  );
}
