import '../css/productDetailReview.scss';
import ReviewShop from '../../review/js/ReviewShop';
import useFetch from '../../../util/useFetch';

export default function ProductDetailReview({ setClickBtn, id }) {
  const [items] = useFetch(`review/${id}`);

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
