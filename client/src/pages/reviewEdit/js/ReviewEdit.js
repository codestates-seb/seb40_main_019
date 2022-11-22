import axios from 'axios';
import { useEffect, useState } from 'react';
import ReviewForm from '../../../components/review/js/ReviewForm';
import ReviewStar from '../../../components/review/js/ReviewStar';
import '../../reviewAdd/css/ReviewAdd.scss';

export default function ReviewEdit() {
  const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

  const [pastData, setPastData] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/sellerproducts/').then((res) => {
      setPastData(res.data);
    });
  }, []);
  // const [reviewImg, setReviewImg] = useState(pastData.img);
  // const [reviewContent, setReviewContent] = useState(pastData.reviewContent);
  // const [clickStar, setClickStar] = useState(pastData.star);

  //오류방지 임시 state
  const [reviewImg, setReviewImg] = useState([]);
  const [reviewContent, setReviewContent] = useState('');
  const [clickStar, setClickStar] = useState(0);
  const data = { reviewImg, reviewContent, clickStar };
  console.log(pastData);

  const handleSubmit = () => {
    if (reviewContent.length < 10 || reviewContent.length >= 200) {
      alert('리뷰가 10~200자여야 합니다');
    } else if (clickStar === 0) {
      alert('리뷰평점을 선택해셔야 합니다');
    } else {
      axios.patch(`${REACT_APP_API_URL}review/{reviewId}`, data).then((res) => {
        console.log(res.data);
        // navigate(`/seller/product`)
      });
      // .catch((error) => {
      //   console.log(error.response);
      // });
    }
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
      />
      <div className="reviewAddBtn">
        <button className="close">닫기</button>
        <button onClick={handleSubmit}>리뷰 수정</button>
      </div>
    </div>
  );
}
