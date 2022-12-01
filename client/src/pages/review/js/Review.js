import '../css/Review.scss';
// import { useEffect, useState } from 'react';
// import axios from 'axios';
import ReviewList from '../../../components/review/js/ReviewList';
import useFetch from '../../../util/useFetch';
import Empty from '../../../components/empty/js/Empty';

export default function Review() {
  // const [items, setItems] = useState();
  // useEffect(() => {
  //   axios.get('http://localhost:3001/review/').then((res) => {
  //     setItems(res.data);
  //   });
  // }, []);
  const [items] = useFetch('user/review'); // 페이지도 있음
  return (
    <div className="reviewContainer">
      <div className="orderListTitle">
        <h1>내가 작성한 리뷰</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>상품정보</li>
        <li>리뷰</li>
        <li>수정 / 삭제</li>
      </ul>
      {items && items.length !== 0 ? (
        items.map((item) => {
          return (
            <div key={item.orederId}>
              <ReviewList item={item} />
            </div>
          );
        })
      ) : (
        <Empty text={'작성한 리뷰가 없습니다'} />
      )}
    </div>
  );
}
