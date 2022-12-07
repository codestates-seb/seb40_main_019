import '../css/home.scss';
import video1 from '../../../assets/video/main2.webm';
import video2 from '../../../assets/video/main3.webm';
import video3 from '../../../assets/video/main4.webm';
import video4 from '../../../assets/video/main5.webm';
import video5 from '../../../assets/video/main6.webm';
// import mainPanding from '../../../assets/video/mainPanding1080p.webm';
import aiDog from '../../../assets/img/aiDog.svg';
import reviewBar from '../../../assets/img/reviewBar.svg';
import MainVideo from '../../../components/home/js/MainVideo';
import MainBox from '../../../components/home/js/MainBox';
import MainReview from '../../../components/home/js/MainReview';
import mainCircle3 from '../../../assets/img/mainCircle3.svg';
import 'animate.css';
import { useEffect, useState } from 'react';

export default function Home() {
  const [scroll, setScroll] = useState('');

  const [aniTime, setAniTime] = useState([400, 900, 1700, 2500]);

  const handleResize = () => {
    setAniTime([
      400 - Math.floor((1920 - window.innerWidth) / 5),
      900 - Math.floor((1920 - window.innerWidth) / 5),
      1700 - Math.floor((1920 - window.innerWidth) / 2),
      2500 - Math.floor((1920 - window.innerWidth) / 1.1),
    ]);
  };

  const handleScroll = () => {
    setScroll(scrollY);
  };

  useEffect(() => {
    window.addEventListener('scroll', handleScroll, { capture: true }); // 스크롤 이벤트 등록
    window.addEventListener('resize', handleResize);
    setAniTime([
      400 - Math.floor((1920 - window.innerWidth) / 5),
      900 - Math.floor((1920 - window.innerWidth) / 5),
      1700 - Math.floor((1920 - window.innerWidth) / 2),
      2500 - Math.floor((1920 - window.innerWidth) / 1.1),
    ]);

    return () => {
      window.removeEventListener('scroll', handleScroll); // 스크롤 이벤트 등록 제거(성능저하방지)
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return (
    <>
      <div className="home ">
        {/* <div className="pandingPosition">
          <div className="pandingBox">
            <video className="mainPanding" autoPlay={true} muted loop={false}>
              <source src={mainPanding} type="video/webm" />
            </video>
          </div>
        </div> */}
        <div className="main1Wrap">
          <div className="videoWrap">
            <div className="videoLeftWrap">
              <div className="wrap2">
                <MainVideo name={'video2'} src={video2} />
              </div>
              <div className="wrap4">
                <MainVideo name={'video4'} src={video4} />
              </div>
            </div>
            <div className="videoCenterWrap">
              <MainVideo name={'video1'} src={video1} />
            </div>
            <div className="videoRightWrap">
              <div className="wrap5">
                <MainVideo name={'video5'} src={video5} />
              </div>
              <div className="wrap3">
                <MainVideo name={'video3'} src={video3} />
              </div>
            </div>
          </div>
          <div className="mainText">
            <div className="mainTextTopOverflow">
              <div className="mainTextTop">
                <h2>유기농 고급재료로 신선하게!</h2>
              </div>
            </div>
            <div className="mainTextBottomOverflow">
              <div className="mainTextBottom">
                <h1>DOG FOOD WITH LOVE</h1>
              </div>
            </div>
          </div>
        </div>
        <div className="main2Wrap">
          <div
            className={
              scroll > aniTime[0]
                ? 'main2Text main2TextAni'
                : 'main2Text hidden'
            }
          >
            <h1>건강에 대한 바른 집념</h1>
            <p>
              LUXMEAL은 영양을 통해 반려견의 건강을 향상시키겠다는 포부로
              설립되었습니다. 어느 곳에서 볼수없었던 최고급 사료를 취급하는
              쇼핑몰로서 품질을 유지하기위해 항상 노력하고있습니다. 항상
              반려견의 건강을 최우선으로 하자는 그 사명은 LUXMEAL이 하는 모든
              일에 원동력이 되고 있습니다
            </p>
          </div>
          <div
            className={
              scroll > aniTime[0] ? 'main2Img main2ImgAni' : 'main2Img hidden'
            }
          >
            <img className="main1" src={mainCircle3} alt="mainImg" />
          </div>
        </div>
        <div className="main3Wrap">
          <div className="main3WrapParent">
            <img
              className={
                scroll > aniTime[1] ? 'aiDog aiDogAni' : 'aiDog hidden'
              }
              src={aiDog}
              alt="aiDog"
            />
            <div className="aiText">
              <div
                className={
                  scroll > aniTime[1]
                    ? 'aiTextTop aiTextTopAni'
                    : 'aiTextTop hidden'
                }
              >
                <h1>AI를 이용한 맞춤 추천</h1>
              </div>
              <div
                className={
                  scroll > aniTime[1]
                    ? 'aiTextBottom aiTextBottomAni'
                    : 'aiTextBottom hidden'
                }
              >
                <h3>나이별, 건강 상태별, 라이프 스타일에 따라</h3>
                <h3>필요로 하는 영양 맞춤 사료를 만나보세요.</h3>
              </div>
              <button className={scroll > aniTime[1] ? 'aiBtnAni' : 'hidden'}>
                AI 추천 바로가기
              </button>
            </div>
          </div>
        </div>
        <div className="main4Wrap">
          <div className="mainBoxWrap">
            <div
              className={
                scroll > aniTime[2] ? 'boxLeft boxLeftAni' : 'boxLeft hidden'
              }
            >
              <div className="firstBox">
                <MainBox name={'boxOne'} idx={0} />
              </div>
              <MainBox name={'boxTwo'} idx={1} />
            </div>
            <div
              className={
                scroll > aniTime[2] ? 'boxRight boxRightAni' : 'boxRight hidden'
              }
            >
              <div className="firstBox">
                <MainBox name={'boxThree'} idx={2} />
              </div>
              <MainBox name={'boxFour'} idx={3} />
            </div>
            <div
              className={
                scroll > aniTime[2]
                  ? 'mainBoxBack mainBoxBackAni'
                  : 'mainBoxBack hidden'
              }
            ></div>
          </div>
        </div>
        <div className="main5Wrap">
          <img
            src={reviewBar}
            alt="reviewBar"
            className={
              scroll > aniTime[3]
                ? 'reviewBar reviewBarAni'
                : 'reviewBar hidden'
            }
          />
          <MainReview scroll={scroll} aniTime={aniTime} />
        </div>
      </div>
    </>
  );
}
