import '../css/productInfoSmall.scss';
import QuantityBtn from './QuantityBtn';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

export default function ProductInfoSmall({
  product,
  count,
  setCount,
  setModal,
  myPoint,
}) {
  // addToCart 버튼 누르면 장바구니에 상품담기
  const navigate = useNavigate();
  const user = useSelector((state) => state.user);

  function addToCart() {
    if (user.userRole === 'ROLE_ADMIN_TEST' || user.userRole === 'ROLE_ADMIN') {
      window.alert('판매자는 이용할 수 없습니다.');
      return;
    }

    let data = JSON.parse(window.localStorage.getItem('cartItem'));

    if (data[product.productsId]) {
      data[product.productsId] = {
        ...data[product.productsId],
        count: data[product.productsId].count + count,
      };
    } else {
      data[product.productsId] = {
        titleImg: product.titleImg,
        title: product.title,
        price: product.price,
        productsId: product.productsId,
        count: count,
        check: true,
      };
    }

    window.localStorage.setItem('cartItem', JSON.stringify(data));
    let check = window.confirm(
      '장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?'
    );

    if (check) {
      navigate('/product/order');
    }
  }

  const buyBtun = () => {
    if (user.userRole === '') {
      window.alert('로그인이 필요한 서비스입니다.');
      return;
    }
    if (user.userRole === 'ROLE_ADMIN_TEST' || user.userRole === 'ROLE_ADMIN') {
      window.alert('판매자는 이용할 수 없습니다.');
      return;
    }
    if (myPoint < product.price * count) {
      window.alert('포인트가 부족합니다');
      return;
    }
    setModal(true);
  };

  return (
    <div className="infoSmallContainer">
      <div className="infoSmallTitle">
        <h1>{product.price * count} 원</h1>
        <QuantityBtn count={count} setCount={setCount} />
      </div>
      <div className="infoSmallBottom">
        <button onClick={addToCart} className="cart">
          CART
        </button>
        <button onClick={buyBtun}>ORDER</button>
      </div>
    </div>
  );
}
