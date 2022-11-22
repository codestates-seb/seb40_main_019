import '../css/productDetailReview.scss';
import axios from 'axios';
import { useState, useEffect } from 'react';
import ReviewShop from '../../review/js/ReviewShop';

export default function ProductDetailReview({ setClickBtn }) {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/review/').then((res) => {
      setItems(res.data);
    });
  }, []);
  return (
    <div className="reviewContainer">
      <div className="detailProductBtn">
        <button className="detailBtn" onClick={() => setClickBtn('detail')}>
          DETAIL
        </button>
        <button className="reviewBtn" onClick={() => setClickBtn('review')}>
          REVIEW
        </button>
      </div>
      <div className="reviewTextContainer">
        {items &&
          items.map((item) => {
            return (
              <div key={item.reviewId}>
                <ReviewShop item={item} />
              </div>
            );
          })}
      </div>
    </div>
  );
}
