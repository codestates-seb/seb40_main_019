import '../css/cartList.scss';
import axios from 'axios';
import { useState, useEffect } from 'react';

import CartListItem from './CartListItem';

export default function CartList() {
  const [items, setItems] = useState();

  useEffect(() => {
    axios.get('http://localhost:3001/orders/').then((res) => {
      setItems(res.data);
      // console.log(res.data);
    });
  }, []);

  //quantity
  const [count, setCount] = useState(1);

  return (
    <div className="CartListContatner">
      <div className="productTitle">
        <h1>장바구니</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>
          <input type="checkbox"></input>
        </li>
        <li>상품</li>
        <li>수량</li>
        <li>가격</li>
        <li>X</li>
      </ul>

      {items &&
        items.map((item) => {
          return (
            <div key={item.productsId}>
              <CartListItem item={item} count={count} setCount={setCount} />
            </div>
          );
        })}
    </div>
  );
}
