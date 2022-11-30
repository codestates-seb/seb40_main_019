// import axios from 'axios';
// import { useEffect, useState } from 'react';
import MypageOrderListItem from '../../../components/mypageOrderListItem/js/MypageOrderListItem';
import ReviewList from '../../../components/review/js/ReviewList';
import '../css/MypageHome.scss';
import useFetch from '../../../util/useFetch';
import '../../review/css/Review.scss';

export default function MypageHome() {
  //임시
  // const [order, setOrder] = useState();
  // useEffect(() => {
  //   axios.get('http://localhost:3001/ordersMypage/').then((res) => {
  //     setOrder(res.data[0]);
  //   });
  // }, []);

  // const [review, setReview] = useState();
  // useEffect(() => {
  //   axios.get('http://localhost:3001/review/').then((res) => {
  //     setReview(res.data[0]);
  //   });
  // }, []);
  const [order] = useFetch('orders'); //page 0으로
  const [review] = useFetch('user/review'); // page 0으로

  console.log(review);
  return (
    <div className="mypageHome">
      <div className="orderSummery">
        <ul>
          <li>
            <div className="orderIcon">
              <p>상품준비중</p>
              <i className="fa-solid fa-cart-flatbed"></i>
            </div>
            <h1>0</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>배송중</p>
              <i className="fa-solid fa-truck"></i>
            </div>
            <h1>11</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>배송완료</p>
              <i className="fa-solid fa-gift"></i>
            </div>
            <h1>0</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>취소/교환/반품</p>
              <i className="fa-solid fa-arrow-rotate-left"></i>
            </div>
            <h1>0</h1>
          </li>
        </ul>
      </div>

      <div className="MypageOrderContainer">
        <div className="orderListTitle">
          <h1>최근 주문 목록</h1>
        </div>

        <div className="lineBold"></div>
        <ul className="lineTitle">
          <li>날짜</li>
          <li>이미지</li>
          <li>상품명</li>
          <li>상품 금액 / 수량</li>
          <li>확인 / 리뷰</li>
        </ul>
        {order && (
          <div>
            <MypageOrderListItem item={order[0]} />
          </div>
        )}
      </div>

      <div className="reviewContainer">
        <div className="orderListTitle">
          <h1>최근 작성한 리뷰</h1>
        </div>
        <div className="lineBold"></div>
        <ul className="lineTitle">
          <li>상품정보</li>
          <li>리뷰</li>
          <li>수정 / 삭제</li>
        </ul>
        {review && (
          <div>
            <ReviewList item={review[0]} />
          </div>
        )}
      </div>
    </div>
  );
}
