import '../css/buyAddBtn.scss';

//BUY NOW 클릭하면, 단품으로 결제
//ADD CART 클릭하면, 장바구니 상품 갯수가 증가
export default function BuyAddBtn({ product, count, setModal }) {
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
        check: true,
      };
    }
    window.localStorage.setItem('cartItem', JSON.stringify(data));
  }

  return (
    <div className="buttonContainer">
      <button onClick={() => setModal(true)} className="buyBtn">
        BUY NOW
      </button>
      <button onClick={addToCart} className="addBtn">
        ADD CART
      </button>
    </div>
  );
}
