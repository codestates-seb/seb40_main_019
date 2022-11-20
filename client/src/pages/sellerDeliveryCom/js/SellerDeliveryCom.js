import '../css/SellerDeliveryCom.scss';
import axios from 'axios';
import { useEffect, useState } from 'react';
import Delivery from '../../../components/seller/js/Delivery';

export default function SellerDeliveryCom() {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/orders/').then((res) => {
      setItems(res.data);
    });
  }, []);
  return (
    <div className="sellerProducts">
      <div className="productTitle">
        <h1>배송 완료</h1>
        <div className="productBtn"></div>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>발송 처리일</li>
        <li>상품명</li>
        <li>배송지 정보</li>
        <li>수량</li>
      </ul>

      {items &&
        items.map((item) => {
          return (
            <div key={item.productsId}>
              <Delivery item={item} />
            </div>
          );
        })}
    </div>
  );
}
