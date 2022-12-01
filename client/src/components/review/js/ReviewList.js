import '../css/ReviewList.scss';
import { useNavigate } from 'react-router-dom';
import ReviewStar from '../../review/js/ReviewStar';
import useFetch from '../../../util/useFetch';
import {
  handleDltReview,
  handleDltReviewAlert,
} from '../../../util/api/review';
import Empty from '../../empty/js/Empty';
import ModalYesorNo from '../../modal/js/ModalYesorNo';
import { useState } from 'react';

export default function ReviewList({ item }) {
  const navigate = useNavigate();

  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const clickDlt = () => {
    handleDltReviewAlert(setModalText, setModalOn);
  };

  const clickDltOk = () => {
    handleDltReview(item.reviewId);
  };

  let [pastData] = '';

  if (item) {
    pastData = useFetch(`review/read/${item.reviewId}`);
  }

  const clickEdit = () => {
    navigate(`/mypage/reviewedit/${item.reviewId}`, {
      state: { item: pastData },
    });
  };
  return (
    <>
      {item ? (
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
            <div className="reviewContent">{item.reviewContent}</div>
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
      ) : (
        <Empty text={'최근 작성된 리뷰가 없습니다'} />
      )}
      <ModalYesorNo
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
        api={clickDltOk}
      />
    </>
  );
}
