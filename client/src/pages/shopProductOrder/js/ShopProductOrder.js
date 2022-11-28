import '../css/shopProductOrder.scss';
import { useState, useEffect } from 'react';
// import { useEffect } from 'react';
import PaymentModal from '../../../components/payment/js/PaymentModal';
import CartList from '../../../components/shop/productOrder/js/CartList';
import OrderSummary from '../../../components/shop/productOrder/js/OrderSummary';
import OrderMobileButton from './OrderMobileButton';
import { getPoint } from '../../../util/api/point';

export default function ShopProductOrder() {
  const [items, setItems] = useState(null);
  const [totalPrice, setTotalPrice] = useState(0);
  const [allSelect, setAllSelect] = useState(true);
  const [modal, setModal] = useState(false);

  const [myPoint, setMyPoint] = useState(0);

  useEffect(() => {
    getPoint().then((res) => {
      setMyPoint(res.data);
    });
    let data = JSON.parse(window.localStorage.getItem('cartItem'));

    let price = 0;
    let arr = [];
    let checked = true;
    Object.keys(data).forEach((el) => {
      if (data[el].check) {
        price += Number(data[el].price) * data[el].count;
      }
      arr.push(data[el]);
      if (!data[el].check) {
        checked = false;
      }
    });
    setTotalPrice(price);
    setAllSelect(checked);
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
    data[item.productsId] = {
      ...data[item.productsId],
      count: data[item.productsId].count - 1,
    };

    let arr = [];
    let price = 0;
    Object.keys(data).forEach((el) => {
      arr.push(data[el]);

      if (data[el].check) {
        price += Number(data[el].price) * data[el].count;
      }
    });
    setTotalPrice(price);
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
    data[item.productsId] = {
      ...data[item.productsId],
      count: data[item.productsId].count + 1,
    };

    let arr = [];
    let price = 0;

    Object.keys(data).forEach((el) => {
      arr.push(data[el]);
      if (data[el].check) {
        price += Number(data[el].price) * data[el].count;
      }
    });
    setTotalPrice(price);
    window.localStorage.setItem('cartItem', JSON.stringify(data));
    setItems(arr);
  };

  // 물품 삭제 함수
  const deleteItem = (item) => {
    window.alert('물품 삭제');

    let data = JSON.parse(window.localStorage.getItem('cartItem'));
    delete data[item.productsId];

    let arr = [];
    let checked = true;
    let price = 0;
    Object.keys(data).forEach((el) => {
      arr.push(data[el]);
      if (!data[el].check) {
        checked = false;
      }
      if (data[el].check) {
        price += Number(data[el].price) * data[el].count;
      }
    });
    setTotalPrice(price);
    setAllSelect(checked);
    setItems(arr);
    window.localStorage.setItem('cartItem', JSON.stringify(data));
  };

  // 장바구니 선택 버튼
  const checkBuyItem = (item) => {
    let data = JSON.parse(window.localStorage.getItem('cartItem'));

    data[item.productsId] = {
      ...data[item.productsId],
      check: !data[item.productsId].check,
    };
    let arr = [];
    let price = 0;
    let checked = true;
    Object.keys(data).forEach((el) => {
      arr.push(data[el]);
      if (!data[el].check) {
        checked = false;
      }
      if (data[el].check) {
        price += Number(data[el].price) * data[el].count;
      }
    });
    setTotalPrice(price);
    setAllSelect(checked);
    setItems(arr);
    window.localStorage.setItem('cartItem', JSON.stringify(data));
  };
  // 장바구니 전체 선택 버튼
  const checkBuyAllItem = (allSelect) => {
    let data = JSON.parse(window.localStorage.getItem('cartItem'));

    let arr = [];
    let price = 0;
    Object.keys(data).forEach((el) => {
      data[el].check = !allSelect;
      arr.push(data[el]);
      if (data[el].check) {
        price += Number(data[el].price) * data[el].count;
      }
    });
    setTotalPrice(price);
    window.localStorage.setItem('cartItem', JSON.stringify(data));
    setItems(arr);
    setAllSelect(!allSelect);
  };
  return (
    <div className="productOrderContainer">
      <div className="orderCartListBox">
        <CartList
          items={items}
          decreaseQuantity={decreaseQuantity}
          increaseQuantity={increaseQuantity}
          deleteItem={deleteItem}
          checkBuyItem={checkBuyItem}
          checkBuyAllItem={checkBuyAllItem}
          allSelect={allSelect}
        />
      </div>
      <div className="orderSummaryBox">
        <OrderSummary
          totalPrice={totalPrice}
          myPoint={myPoint}
          setModal={setModal}
        />
      </div>
      <div className="MobileBtnContainer">
        <OrderMobileButton
          totalPrice={totalPrice}
          myPoint={myPoint}
          setModal={setModal}
        />
      </div>
      {modal && (
        <>
          <PaymentModal
            setModal={setModal}
            totalPrice={totalPrice}
            type="multi"
          />
        </>
      )}
    </div>
  );
}
