import '../css/buyAddBtn.scss';

//BUY NOW 클릭하면, 장바구니&결제 페이지로 이동
//ADD CART 클릭하면, 장바구니 상품 갯수가 증가
export default function BuyAddBtn() {
  return (
    <div className="buttonContainer">
      <button className="buyBtn">BUY NOW</button>
      <button className="addBtn">ADD CART</button>
    </div>
  );
}
