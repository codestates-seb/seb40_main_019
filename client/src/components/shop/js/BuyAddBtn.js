import '../css/buyAddBtn.scss';
import { useEffect } from 'react';

//BUY NOW 클릭하면, 단품으로 결제
//ADD CART 클릭하면, 장바구니 상품 갯수가 증가
export default function BuyAddBtn() {
  // addCart 버튼 누르면 장바구니에 상품담기
  useEffect(() => {
    'CartItem', JSON.stringify([]);
  }, []);
  function addToCart() {
    localStorage.setItem('CartItem', JSON.stringify([]));
  }

  return (
    <div className="buttonContainer">
      <button className="buyBtn">BUY NOW</button>
      <button onClick={addToCart} className="addBtn">
        ADD CART
      </button>
    </div>
  );
}
