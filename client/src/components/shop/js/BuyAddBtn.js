import '../css/buyAddBtn.scss';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

//BUY NOW 클릭하면, 단품으로 결제
//ADD CART 클릭하면, 장바구니 상품 갯수가 증가
export default function BuyAddBtn({ product, count, setModal, myPoint }) {
  console.log(product);
  const navigate = useNavigate();
  const user = useSelector((state) => state.user);

  const addToCart = () => {
    if (user.userRole === 'ROLE_ADMIN_TEST' || user.userRole === 'ROLE_ADMIN') {
      window.alert('판매자는 이용할 수 없습니다.');
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
    let check = window.confirm(
      '장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?'
    );

    if (check) {
      navigate('/product/order');
    }
  };

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
    <div className="buttonContainer">
      <button onClick={buyBtun} className="buyBtn">
        BUY NOW
      </button>
      <button onClick={addToCart} className="addBtn">
        ADD CART
      </button>
    </div>
  );
}
