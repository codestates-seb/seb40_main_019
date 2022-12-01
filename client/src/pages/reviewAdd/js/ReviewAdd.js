import { useState } from 'react';
import ReviewForm from '../../../components/review/js/ReviewForm';
import ReviewStar from '../../../components/review/js/ReviewStar';
import { useNavigate, useParams } from 'react-router-dom';
import '../css/ReviewAdd.scss';
import { handleAddReview } from '../../../util/api/review';
import ModalOk from '../../../components/modal/js/ModalOk';

export default function ReviewAdd() {
  const navigate = useNavigate();
  const params = useParams();

  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const [reviewImg, setReviewImg] = useState([]);
  const [reviewContent, setReviewContent] = useState('');
  const [clickStar, setClickStar] = useState(0);

  const data = { reviewImg, reviewContent, star: clickStar };

  const handleSubmit = () => {
    handleAddReview(data, params.id, setModalOn, setModalText);
  };

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
        <button className="close" onClick={() => navigate('/mypage/order')}>
          닫기
        </button>
        <button onClick={handleSubmit}>리뷰 작성</button>
      </div>
      <ModalOk
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
      />
    </div>
  );
}
