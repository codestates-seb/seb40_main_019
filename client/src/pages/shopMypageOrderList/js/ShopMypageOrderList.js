import '../css/shopMypageOrderList.scss';
import MypageOrderListItem from '../../../components/mypageOrderListItem/js/MypageOrderListItem';
import { useEffect, useState } from 'react';
import axios from 'axios';

export default function ShopMypageOrderList() {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/orders/').then((res) => {
      setItems(res.data);
    });
  }, []);
  return (
    <div className="MypageOrderContainer">
      <div className="orderListTitle">
        <h1>주문 목록</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>날짜</li>
        <li>상품명 / 옵션</li>
        <li>상품 금액 / 수량</li>
        <li>확인 / 리뷰</li>
      </ul>

      {items &&
        items.map((item) => {
          return (
            <div key={item.productsId}>
              <MypageOrderListItem item={item} />
            </div>
          );
        })}
    </div>
  );
}
