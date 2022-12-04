import MypageOrderListItem from '../../../components/mypageOrderListItem/js/MypageOrderListItem';
import ReviewList from '../../../components/review/js/ReviewList';
import '../css/MypageHome.scss';
import '../../review/css/Review.scss';
import useFetchOne from '../../../util/useFetchOne';

export default function MypageHome() {
  const [order] = useFetchOne('orders'); //page 0으로
  const [review] = useFetchOne('user/review'); // page 0으로

  let orderSummery = [0, 0, 0, 0];

  for (let i = 0; i < (order && order.length); i++) {
    if (order[i].orderStatus === 'PROCESS') orderSummery[0]++;
    else if (order[i].orderStatus === 'SHIPPING') orderSummery[1]++;
    else if (order[i].orderStatus === 'SHIPPED') orderSummery[2]++;
    else if (order[i].orderStatus === 'CANCEL') orderSummery[3]++;
  }

  return (
    <div className="mypageHome">
      <div className="orderSummery">
        <ul>
          <li>
            <div className="orderIcon">
              <p>상품준비중</p>
              <i className="fa-solid fa-cart-flatbed"></i>
            </div>
            <h1>{orderSummery[0]}</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>배송중</p>
              <i className="fa-solid fa-truck"></i>
            </div>
            <h1>{orderSummery[1]}</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>배송완료</p>
              <i className="fa-solid fa-gift"></i>
            </div>
            <h1>{orderSummery[2]}</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>취소/교환/반품</p>
              <i className="fa-solid fa-arrow-rotate-left"></i>
            </div>
            <h1>{orderSummery[3]}</h1>
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
