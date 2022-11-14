import '../css/MainReview.scss';
import MainReviewContent from './MainReviewContent';
import review1 from '../../../assets/img/review1.svg';

export default function MainReview() {
  const data = [
    {
      src: review1,
    },
  ];
  return (
    <>
      <div className="mainReview">
        <div className="reviewBtn">
          <button>
            <i className="fa-solid fa-arrow-left"></i>
          </button>
          <button>
            <i className="fa-solid fa-arrow-right"></i>
          </button>
        </div>
        <ul className="mainReviewWrap">
          {data &&
            data.map((data, idx) => {
              return (
                <div key={idx} className="reviewImg">
                  <MainReviewContent data={data} />
                </div>
              );
            })}
        </ul>
      </div>
    </>
  );
}
