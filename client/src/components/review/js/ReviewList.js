import '../css/ReviewList.scss';
import { useNavigate } from 'react-router-dom';
import ReviewStar from '../../review/js/ReviewStar';
import useFetch from '../../../util/useFetch';
// import { useEffect, useState } from 'react';
// import axios from 'axios';
import { handleDltReview } from '../../../util/api/review';

export default function ReviewList({ item }) {
  const navigate = useNavigate();

  //임시
  // const [pastData, setPastData] = useState();
  // useEffect(() => {
  //   axios.get('http://localhost:3001/review/').then((res) => {
  //     setPastData(res.data[1]);
  //   });
  // }, []);

  const clickDlt = () => {
    handleDltReview(item.reviewId);
  };

  const [pastData] = useFetch(`review/read/${item.reviewId}`);

  const clickEdit = () => {
    navigate(`/mypage/reviewedit/${item.reviewId}`, {
      state: { item: pastData },
    });
  };
  console.log(item);
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
        <button className="edit" onClick={clickEdit}>
          <i className="fa-solid fa-pen-to-square"></i>
        </button>
        <h3>/</h3>
        <button className="delete" onClick={clickDlt}>
          <i className="fa-solid fa-trash-can"></i>
        </button>
      </div>
    </div>
  );
}
