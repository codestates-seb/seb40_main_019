import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import ReviewForm from '../../../components/review/js/ReviewForm';
import ReviewStar from '../../../components/review/js/ReviewStar';
import { handleEditReview } from '../../../util/api/review';
import '../../reviewAdd/css/ReviewAdd.scss';

export default function ReviewEdit() {
  const navigate = useNavigate();
  const location = useLocation();
  const { item } = location.state;

  const [reviewImg, setReviewImg] = useState([item.reviewImg]);
  const [reviewContent, setReviewContent] = useState(item.reviewContent);
  const [clickStar, setClickStar] = useState(item.star);

  const data = { reviewImg, reviewContent, star: clickStar };

  const handleEdit = () => {
    handleEditReview(data, item);
  };

  return (
    <div className="reviewAdd">
      <h1>리뷰 수정</h1>
      <div className="reviewStarSize">
        <ReviewStar clickStar={clickStar} setClickStar={setClickStar} />
      </div>
      <ReviewForm
        reviewImg={reviewImg}
        setReviewImg={setReviewImg}
        setReviewContent={setReviewContent}
        reviewContent={reviewContent}
      />
      <div className="reviewAddBtn">
        <button className="close" onClick={() => navigate('/mypage/review')}>
          닫기
        </button>
        <button onClick={handleEdit}>리뷰 수정</button>
      </div>
    </div>
  );
}
