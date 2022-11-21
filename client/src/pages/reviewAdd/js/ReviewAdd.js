import { useState } from 'react';
import ReviewForm from '../../../components/review/js/ReviewForm';
import ReviewStar from '../../../components/review/js/ReviewStar';
import '../css/ReviewAdd.scss';

export default function ReviewAdd() {
  const [reviewImg, setReviewImg] = useState();
  const [reviewContent, setReviewContent] = useState('');
  const [clickStar, setClickStar] = useState(0);

  const data = { reviewImg, reviewContent, clickStar };
  console.log(data);

  return (
    <div className="reviewAdd">
      <h1>리뷰 작성</h1>
      <div className="reviewStarSize">
        <ReviewStar clickStar={clickStar} setClickStar={setClickStar} />
      </div>
      <ReviewForm
        reviewImg={reviewImg}
        setReviewImg={setReviewImg}
        setReviewContent={setReviewContent}
      />
      <div className="reviewAddBtn">
        <button className="close">닫기</button>
        <button>리뷰 작성</button>
      </div>
    </div>
  );
}
