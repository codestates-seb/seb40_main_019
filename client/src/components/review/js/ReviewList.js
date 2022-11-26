import '../css/ReviewList.scss';
import { useNavigate } from 'react-router-dom';
import ReviewStar from '../../review/js/ReviewStar';

export default function ReviewList({ item }) {
  const navigate = useNavigate();
  return (
    <div className="reviewListContainer">
      <div className="reviewTitle">
        <img className="titleImg" src={item.titleImg} alt="productImg" />
        <div className="title">{item.productName}</div>
      </div>
      <div className="reviewBox">
        <div className="reviewStars">
          <ReviewStar clickStar={item.star} type={'small'} />
          <p>{item.star}</p>
        </div>
        <div className="reviewContent">{item.reviewContent}원</div>
      </div>
      <div className="reviewBtn">
        <button
          className="edit"
          onClick={() => navigate(`/mypage/reviewedit/${item.reviewId}`)}
        >
          <i className="fa-solid fa-pen-to-square"></i>
        </button>
        <h3>/</h3>
        <button className="delete">
          <i className="fa-solid fa-trash-can"></i>
        </button>
      </div>
    </div>
  );
}
