import '../css/ReviewForm.scss';
import ImgUploader from '../../../components/seller/js/ImgUploader';

export default function ReviewForm({
  reviewImg,
  setReviewImg,
  setReviewContent,
  reviewContent,
  pastImg,
}) {
  return (
    <div className="reviewForm">
      <div>
        <ImgUploader
          pictures={reviewImg}
          setPictures={setReviewImg}
          pastImg={pastImg}
        />
      </div>
      <div className="reviewContent">
        <div className="userInfo"></div>
        <input
          type="text"
          onChange={(e) => {
            setReviewContent(e.target.value);
          }}
          value={reviewContent}
        />
      </div>
    </div>
  );
}
