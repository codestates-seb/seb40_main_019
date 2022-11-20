import '../css/SellerOrder.scss';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Order from '../../../components/seller/js/Order';

export default function SellerOrder() {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/orders/').then((res) => {
      setItems(res.data);
    });
  }, []);
  return (
    <div className="sellerProducts">
      <div className="productTitle">
        <h1>주문 내역</h1>
        <div className="productBtn"></div>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>결제일</li>
        <li>상품명</li>
        <li>주문자 정보</li>
        <li>수량</li>
        <li>발송 처리</li>
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
