import '../css/ReviewForm.scss';
import ImgUploader from '../../../components/seller/js/ImgUploader';

export default function ReviewForm({ reviewImg, setReviewImg }) {
  return (
    <div className="reviewForm">
      <div>
        <ImgUploader pictures={reviewImg} setPictures={setReviewImg} />
      </div>
      <div className="reviewContent">
        <div className="userInfo"></div>
        <input type="text"></input>
      </div>
    </div>
  );
}
