import '../css/aiComponent.scss';
import dogsImg from '../../../assets/img/aiRecommend1.svg';
import poppyImg from '../../../assets/img/aiRecommend2.svg';

export default function AiComponent() {
  return (
    <>
      <div className="beforeAiContainer">
        <div className="leftContainer">
          <h1>AI 맞춤 추천 사료</h1>
          <div>
            <img src={dogsImg} alt="dogImg" />
          </div>
          <div>
            <p>나이별, 건강 상태별, 라이프 스타일에 따라</p>
            <p>필요로 하는 영양 맞춤 사료를 만나보세요.</p>
          </div>
        </div>
        <div className="rightContainer">
          <div className="poppyImgBox">
            <img src={poppyImg} alt="dogImg" />
          </div>
          <div className="formContainer">
            <form className="ageForm" name="나이">
              나이
              <input type="text" maxLength="3"></input>
            </form>
            <form name="몸무게">
              몸무게
              <input type="text" maxLength="3"></input>
            </form>
            <button>입력</button>
          </div>
        </div>
        {/* <div className="afterAiContainer"></div> */}
      </div>
    </>
  );
}
