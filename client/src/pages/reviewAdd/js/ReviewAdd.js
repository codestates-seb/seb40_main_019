import { useState } from 'react';
import ImgUploader from '../../../components/seller/js/ImgUploader';
import '../css/ReviewAdd.scss';

export default function ReviewAdd() {
  const [reviewImg, setReviewImg] = useState();
  // const [reviewCont ent, setReviewContent] = useState('');

  return (
    <div className="reviewAdd">
      <h1>리뷰 작성</h1>
      <div className="reviewForm">
        <div>
          <ImgUploader pictures={reviewImg} setPictures={setReviewImg} />
        </div>
        <div className="reviewContent">
          <div className="userInfo"></div>
          <input type="text"></input>
          <button>리뷰 작성</button>
        </div>
      </div>
    </div>
  );
}
