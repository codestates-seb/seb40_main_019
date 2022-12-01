import '../css/SellerOrder.scss';
import Order from '../../../components/seller/js/Order';
import useFetch from '../../../util/useFetch';

export default function SellerOrder() {
  const [items] = useFetch('orders/all');

  return (
    <div className="sellerProducts orderTop">
      <div className="productTitle">
        <h1>판매 관리</h1>
        <div className="productBtn"></div>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>발송현황</li>
        <li>주문자 정보</li>
        <li>상품정보</li>
        <li>수량</li>
      </ul>

      {items &&
        items.map((item) => {
          return (
            <div key={item.productsId}>
              <Order item={item} />
            </div>
          );
        })}
    </div>
  );
}
