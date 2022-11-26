import '../css/buyAddBtn.scss';

//BUY NOW 클릭하면, 단품으로 결제
//ADD CART 클릭하면, 장바구니 상품 갯수가 증가
export default function BuyAddBtn({ product, count }) {
  // addCart 버튼 누르면 장바구니에 상품담기
  console.log(product);
  console.log(product.productsId);
  console.log(count);

  function addToCart() {
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
      };
    }
    window.localStorage.setItem('cartItem', JSON.stringify(data));
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
