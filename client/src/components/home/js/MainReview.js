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

export default function MainReview({ scroll }) {
  const data = [
    {
      src: review1,
      content: `몸에 좋아서 그런지 보리가 잘먹어요~!`,
    },
    {
      src: review2,
      content: `울아가 이유식처럼 먹이는데 여기꺼 너무...`,
    },
    {
      src: review3,
      content: `사료도 정말 잘먹고 습식사료도 정말 잘...`,
    },
    {
      src: review4,
      content: `후기 잘 안쓰는데 매우 만족스러워서 적어...`,
    },
    {
      src: review5,
      content: `향은 약간 두부랑 채소향이 나요! 처음엔 강...`,
    },
    {
      src: review6,
      content: `나이가 많아서 칼로리도 걱정되었는데 저칼로리...`,
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
        <div
          className={
            scroll > 2600 ? 'reviewBtn reviewTextAni' : 'reviewBtn hidden'
          }
        >
          <button onClick={handleLeft}>
            <i className="fa-solid fa-arrow-left"></i>
          </button>
          <button onClick={handleRight}>
            <i className="fa-solid fa-arrow-right"></i>
          </button>
        </div>
        <div
          className={
            scroll > 2600 ? 'mainReviewWrap reviewAni' : 'mainReviewWrap hidden'
          }
        >
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
                  <p>{data.content}</p>
                </div>
              );
            })}
        </div>
      </div>
    </>
  );
}
