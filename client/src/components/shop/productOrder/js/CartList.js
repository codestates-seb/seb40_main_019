import '../css/cartList.scss';
// import axios from 'axios';
// import { useState } from 'react';

import CartListItem from './CartListItem';

export default function CartList({
  items,
  decreaseQuantity,
  increaseQuantity,
  deleteItem,
  deleteOk,
  checkBuyItem,
  checkBuyAllItem,
  allSelect,
  setModalOn,
  modalOn,
  modalText,
  api,
}) {
  return (
    <div className="CartListContatner">
      <div className="productTitle">
        <h1>장바구니</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>
          <input
            onChange={() => checkBuyAllItem(allSelect)}
            type="checkbox"
            checked={allSelect}
          />
        </li>
        <li>상품</li>
        <li>수량</li>
        <li>가격</li>
        <li>X</li>
      </ul>

      {items &&
        items.map((item) => {
          return (
            <div key={item.productId}>
              {/* <CartListItem item={item} count={count} setCount={setCount} /> */}
              <CartListItem
                item={item}
                decreaseQuantity={decreaseQuantity}
                increaseQuantity={increaseQuantity}
                deleteItem={deleteItem}
                deleteOk={deleteOk}
                checkBuyItem={checkBuyItem}
                setModalOn={setModalOn}
                modalOn={modalOn}
                modalText={modalText}
                api={api}
              />
            </div>
          );
        })}
    </div>
  );
}
