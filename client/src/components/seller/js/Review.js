import { Link } from 'react-router-dom';
import { formatDate } from '../../../util/function/formatData';
import ReviewStar from '../../review/js/ReviewStar';
import '../css/Review.scss';

export default function Review({ item }) {
  console.log(item);
  return (
    <div className="sellerproduct review">
      <div className="createdAt">
        <p>{formatDate(item.createdAt)}</p>
      </div>
      <Link to={`/product/detail/${item.proId}`}>
        <img className="titleImg" src={item.titleImg} alt="productImg" />
      </Link>
      <Link to={`/product/detail/${item.proId}`}>
        <div className="title">{item.productName}</div>
      </Link>
      <div className="reviewcontent">{item.reviewContent}</div>
      <div className="star">
        <ReviewStar clickStar={item.star} type={'small'} />
      </div>
    </div>
  );
}
