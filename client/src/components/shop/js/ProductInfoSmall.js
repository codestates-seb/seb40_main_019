import '../css/productInfoSmall.scss';
import QuantityBtn from './QuantityBtn';

export default function ProductInfoSmall({
  product,
  count,
  setCount,
  setModal,
}) {
  // addToCart 버튼 누르면 장바구니에 상품담기

  function addToCart() {
    let data = JSON.parse(window.localStorage.getItem('cartItem'));
    //console.log(data) :null
    //장바구니 상품 같으면 count+1, 없으면 새로운 상품 로컬스토리지에 추가해줌
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
  }

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
        <button onClick={() => setModal(true)}>ORDER</button>
      </div>
    </div>
  );
}
