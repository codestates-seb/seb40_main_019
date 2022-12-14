import '../css/ReviewShop.scss';
import ReviewStar from '../../review/js/ReviewStar';
import { formatDate } from '../../../util/function/formatData';

export default function ReviewShop({ item }) {
  return (
    <div className="reviewShop">
      <div className="reviewTitle">
        <h2>{item.reviewWriter}</h2>
        <p>{formatDate(item.createdAt)}</p>
        <ReviewStar clickStar={item.star} type={'small'} />
      </div>

      {item.reviewImg && (
        <div className="reviewImg">
          <img src={item.reviewImg} alt="reviewImg" />
        </div>
      )}

      <h2>{item.reviewContent}</h2>
    </div>
  );
}
