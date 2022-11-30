import '../css/home.scss';
import video1 from '../../../assets/video/main2.webm';
import video2 from '../../../assets/video/main3.webm';
import video3 from '../../../assets/video/main4.webm';
import video4 from '../../../assets/video/main5.webm';
import video5 from '../../../assets/video/main6.webm';
import aiDog from '../../../assets/img/aiDog.svg';
import reviewBar from '../../../assets/img/reviewBar.svg';
import MainVideo from '../../../components/home/js/MainVideo';
import MainBox from '../../../components/home/js/MainBox';
import MainReview from '../../../components/home/js/MainReview';
import mainCircle3 from '../../../assets/img/mainCircle3.svg';
import 'animate.css';

export default function Home() {
  return (
    <>
      <div className="home">
        <div className="main1Wrap">
          <div className="videoWrap">
            <div className="videoLeftWrap">
              <MainVideo name={'video2'} src={video2} />
              <MainVideo name={'video4'} src={video4} />
            </div>
            <div className="videoCenterWrap">
              <MainVideo name={'video1'} src={video1} />
            </div>
            <div className="videoRightWrap">
              <MainVideo name={'video5'} src={video5} />
              <MainVideo name={'video3'} src={video3} />
            </div>
          </div>
          <div className="mainText">
            <div className="mainTextTop">
              <h2>유기농 고급재료로 신선하게!</h2>
            </div>
            <div className="mainTextBottom">
              <h1>DOG FOOD WITH LOVE</h1>
            </div>
          </div>
        </div>
        <div className="main2Wrap">
          <div className="main2Text">
            <h1>건강에 대한 바른 집념</h1>
            <p>
              LUXMEAL은 영양을 통해 반려견의 건강을 향상시키겠다는 포부로
              설립되었습니다. 어느 곳에서 볼수없었던 최고급 사료를 취급하는
              쇼핑몰로서 품질을 유지하기위해 항상 노력하고있습니다. 항상
              반려견의 건강을 최우선으로 하자는 그 사명은 LUXMEAL이 하는 모든
              일에 원동력이 되고 있습니다
            </p>
          </div>
          <div className="main2Img">
            <img className="main1" src={mainCircle3} alt="mainImg" />
          </div>
        </div>
        <div className="main3Wrap">
          <div className="main3WrapParent">
            <img className="aiDog" src={aiDog} alt="aiDog" />
            <div className="aiText">
              <div className="aiTextTop">
                <h1>AI를 이용한 맞춤 추천</h1>
              </div>
              <div className="aiTextBottom">
                <h3>나이별, 건강 상태별, 라이프 스타일에 따라</h3>
                <h3>필요로 하는 영양 맞춤 사료를 만나보세요.</h3>
              </div>
              <button>AI 추천 바로가기</button>
            </div>
          </div>
        </div>
        <div className="main4Wrap">
          <div className="mainBoxWrap">
            <div className="boxLeft">
              <div className="firstBox">
                <MainBox name={'boxOne'} idx={0} />
              </div>
              <MainBox name={'boxTwo'} idx={1} />
            </div>
            <div className="boxRight">
              <div className="firstBox">
                <MainBox name={'boxThree'} idx={2} />
              </div>
              <MainBox name={'boxFour'} idx={3} />
            </div>
            <div className="mainBoxBack"></div>
          </div>
        </div>
        <div className="main5Wrap">
          <img src={reviewBar} alt="reviewBar" className="reviewBar" />
          <div className="reviewWrap"></div>
          <h3>REVIEW</h3>
          <MainReview />
        </div>
      </div>
    </>
  );
}
