// import axios from 'axios';
import { useState } from 'react';
import ReviewForm from '../../../components/review/js/ReviewForm';
import ReviewStar from '../../../components/review/js/ReviewStar';
import { useNavigate, useParams } from 'react-router-dom';
import '../css/ReviewAdd.scss';
import { handleAddReview } from '../../../util/api/review';

export default function ReviewAdd() {
  const navigate = useNavigate();
  const params = useParams();
  console.log(params.id);

  const [reviewImg, setReviewImg] = useState([]);
  const [reviewContent, setReviewContent] = useState('');
  const [clickStar, setClickStar] = useState(0);

  const data = { reviewImg, reviewContent, star: clickStar };
  console.log(data);

  // const handleSubmit = () => {
  //   if (reviewContent.length < 10 || reviewContent.length >= 200) {
  //     alert('리뷰가 10~200자여야 합니다');
  //   } else if (clickStar === 0) {
  //     alert('리뷰평점을 선택해셔야 합니다');
  //   } else {
  //     axios.post(`${REACT_APP_API_URL}review/{productId}`, data).then((res) => {
  //       console.log(res.data);
  //       // navigate(`/seller/product`)
  //     });
  //     // .catch((error) => {
  //     //   console.log(error.response);
  //     // });
  //   }
  // };.
  const handleSubmit = () => {
    handleAddReview(data, params.id);
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
    </div>
  );
}
