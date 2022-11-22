import ReviewStar from '../../review/js/ReviewStar';
import '../css/Review.scss';

export default function Review({ item }) {
  return (
    <div className="sellerproduct review">
      <div className="createdAt">
        <p>{item.createdAt}</p>
      </div>
      <img className="titleImg" src={item.img} alt="productImg" />
      <div className="title">{item.productName}</div>
      <div className="reviewcontent">{item.reviewContent}</div>
      <div className="star">
        <ReviewStar clickStar={item.star} type={'small'} />
      </div>
    </div>
  );
}
