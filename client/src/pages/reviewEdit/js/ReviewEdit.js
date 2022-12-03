import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import ModalOk from '../../../components/modal/js/ModalOk';
import ReviewForm from '../../../components/review/js/ReviewForm';
import ReviewStar from '../../../components/review/js/ReviewStar';
import { handleEditReview } from '../../../util/api/review';
import '../../reviewAdd/css/ReviewAdd.scss';

export default function ReviewEdit() {
  const navigate = useNavigate();
  const location = useLocation();
  const { item } = location.state;

  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const [reviewImg, setReviewImg] = useState([item[0].reviewImg]);
  const [reviewContent, setReviewContent] = useState(item[0].reviewContent);
  const [clickStar, setClickStar] = useState(item[0].star);

  const data = { reviewImg, reviewContent, star: clickStar };

  const handleEdit = () => {
    handleEditReview(data, item[0], setModalOn, setModalText);
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
        pastImg={item[0].reviewImg}
      />
      <div className="reviewAddBtn">
        <button className="close" onClick={() => navigate('/mypage/review')}>
          닫기
        </button>
        <button onClick={handleEdit}>리뷰 수정</button>
      </div>
      <ModalOk
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
      />
    </div>
  );
}
