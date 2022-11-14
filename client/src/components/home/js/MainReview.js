import '../css/MainReview.scss';
import MainReviewContent from './MainReviewContent';
import review1 from '../../../assets/img/review1.svg';
import review2 from '../../../assets/img/review2.svg';
import review3 from '../../../assets/img/review3.svg';
import review4 from '../../../assets/img/review4.svg';
import review5 from '../../../assets/img/review5.svg';
import review6 from '../../../assets/img/review6.svg';
import reviewStar from '../../../assets/img/reviewStar.svg';
import { useState } from 'react';

export default function MainReview() {
  const data = [
    {
      src: review1,
    },
    {
      src: review2,
    },
    {
      src: review3,
    },
    {
      src: review4,
    },
    {
      src: review5,
    },
    {
      src: review6,
    },
  ];
  const [translate, setTranslate] = useState(0);
  const handleRight = () => {
    if (translate > -240) setTranslate(translate - 80);
  };
  const handleLeft = () => {
    if (translate < 0) setTranslate(translate + 80);
  };
  return (
    <>
      <div className="mainReview">
        <div className="reviewBtn">
          <button onClick={handleLeft}>
            <i className="fa-solid fa-arrow-left"></i>
          </button>
          <button onClick={handleRight}>
            <i className="fa-solid fa-arrow-right"></i>
          </button>
        </div>
        <div className="mainReviewWrap">
          {data &&
            data.map((data, idx) => {
              return (
                <div
                  key={idx}
                  className="reviewWrap"
                  style={{ transform: `translate(${translate}%, 0px)` }}
                >
                  <div className="reviewImg">
                    <MainReviewContent data={data} />
                  </div>
                  <img
                    src={reviewStar}
                    alt="reviewStar"
                    className="reviewStar"
                  />
                  <p>asdfasdfasdfasdf</p>
                </div>
              );
            })}
        </div>
      </div>
    </>
  );
}
