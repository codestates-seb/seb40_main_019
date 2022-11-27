import '../css/shopProductOrder.scss';
import { useState, useEffect } from 'react';
// import { useEffect } from 'react';

import CartList from '../../../components/shop/productOrder/js/CartList';
import OrderSummary from '../../../components/shop/productOrder/js/OrderSummary';
import OrderMobileButton from './OrderMobileButton';

export default function ShopProductOrder() {
  const [items, setItems] = useState(null);
  // const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    let data = JSON.parse(window.localStorage.getItem('cartItem'));
    // let price = 0;
    let arr = [];
    Object.keys(data).forEach((el) => {
      // price += Number(data[el].price) * data[el].count;
      arr.push(data[el]);
    });
    console.log(arr);
    // setTotalPrice(price);
    setItems(arr);
  }, []);

  //갯수 증가, 감소 , 물품 삭제 시
  // useState 값 바꾸기 -> 로컬 스토리지 값 바꾸기

  // 수량 감소 함수
  const decreaseQuantity = (item) => {
    if (item.count === 1) {
      window.alert('최소 수량입니다.');
      return;
    }
    let data = JSON.parse(window.localStorage.getItem('cartItem'));
    console.log(data[item.productsId]);
    data[item.productsId] = {
      ...data[item.productsId],
      count: data[item.productsId].count - 1,
    };

    let arr = [];
    Object.keys(data).forEach((el) => {
      arr.push(data[el]);
    });
    window.localStorage.setItem('cartItem', JSON.stringify(data));
    setItems(arr);
  };

  // 수량 증가 함수
  const increaseQuantity = (item) => {
    if (item.count === 9) {
      window.alert('최대 수량입니다.');
      return;
    }
    let data = JSON.parse(window.localStorage.getItem('cartItem'));
    console.log(data[item.productsId]);
    data[item.productsId] = {
      ...data[item.productsId],
      count: data[item.productsId].count + 1,
    };

    let arr = [];
    Object.keys(data).forEach((el) => {
      arr.push(data[el]);
    });
    window.localStorage.setItem('cartItem', JSON.stringify(data));
    setItems(arr);
  };

  // 물품 삭제 함수
  const deleteItem = (item) => {
    window.alert('물품 삭제');

    let data = JSON.parse(window.localStorage.getItem('cartItem'));

    delete data[item.productsId];
    console.log(data);

    let arr = [];
    Object.keys(data).forEach((el) => {
      arr.push(data[el]);
    });
    window.localStorage.setItem('cartItem', JSON.stringify(data));
    setItems(arr);
  };

  // 장바구니 선택 버튼
  const checkBuyItem = (item) => {
    console.log(item);

    let data = JSON.parse(window.localStorage.getItem('cartItem'));

    data[item.productsId] = {
      ...data[item.productsId],
      check: !data[item.productsId].check,
    };
    console.log(data);
    let arr = [];
    Object.keys(data).forEach((el) => {
      arr.push(data[el]);
    });
    window.localStorage.setItem('cartItem', JSON.stringify(data));
    setItems(arr);
  };
  // 장바구니 전체 선택 버튼

  return (
    <div className="productOrderContainer">
      <div className="orderCartListBox">
        <CartList
          items={items}
          decreaseQuantity={decreaseQuantity}
          increaseQuantity={increaseQuantity}
          deleteItem={deleteItem}
          checkBuyItem={checkBuyItem}
        />
      </div>
      <div className="orderSummaryBox">
        <OrderSummary />
      </div>
      <div className="MobileBtnContainer">
        <OrderMobileButton />
      </div>
    </div>
  );
}
