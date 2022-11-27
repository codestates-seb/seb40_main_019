import '../css/SellerReview.scss';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Review from '../../../components/seller/js/Review';
// import useFetch from '../../../util/useFetch';
// import { Link } from 'react-router-dom';

export default function SellerReview() {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/review/').then((res) => {
      setItems(res.data);
    });
  }, []);

  // const [items] = useFetch('리뷰전체조회api');

  return (
    <div className="sellerReview">
      <div className="productTitle">
        <h1>리뷰 목록</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>등록일</li>
        <li>상품명</li>
        <li>리뷰</li>
        <li>별점</li>
      </ul>

      {items &&
        items.map((item, idx) => {
          return (
            <div key={idx}>
              <Review item={item} />
            </div>
          );
        })}
    </div>
  );
}
