import '../css/productInfoSmall.scss';
import QuantityBtn from './QuantityBtn';
import { useSelector } from 'react-redux';
import { formatMoney } from '../../../util/function/formatData';
import ModalOk from '../../modal/js/ModalOk';
import ModalMove from '../../modal/js/ModalMove';
import { useState } from 'react';

export default function ProductInfoSmall({
  product,
  count,
  setCount,
  setModal,
  myPoint,
}) {
  const [modalOkOn, setModalOkOn] = useState(false);
  const [modalOkText, setModalOkText] = useState('');
  const [modalMoveOn, setModalMoveOn] = useState(false);
  const [modalMoveText, setModalMoveText] = useState('');
  // addToCart 버튼 누르면 장바구니에 상품담기
  const user = useSelector((state) => state.user);

  function addToCart() {
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
    setModalOkOn(true);
    setModalMoveText(
      '장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?'
    );
  }

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
    <div className="infoSmallContainer">
      <div className="infoSmallTitle">
        <h1>{formatMoney(product.price * count)} 원</h1>
        <QuantityBtn count={count} setCount={setCount} />
      </div>
      <div className="infoSmallBottom">
        <button onClick={addToCart} className="cart">
          CART
        </button>
        <button onClick={buyBtun}>ORDER</button>
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
    </div>
  );
}
