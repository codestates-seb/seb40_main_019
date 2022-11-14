import '../css/home.scss';
import video1 from '../../../assets/video/main2.webm';
import video2 from '../../../assets/video/main3.webm';
import video3 from '../../../assets/video/main4.webm';
import video4 from '../../../assets/video/main5.webm';
import video5 from '../../../assets/video/main6.webm';
import mainImg1 from '../../../assets/img/mainImg1.png';
import mainImg2 from '../../../assets/img/mainImg2.png';
import mainImg3 from '../../../assets/img/mainImg3.jpg';
import aiDog from '../../../assets/img/aiDog.svg';
import MainImg from '../../../components/home/js/MainImg';
import MainVideo from '../../../components/home/js/MainVideo';
import MainBox from '../../../components/home/js/MainBox';

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
              <h2>LUXURY, FRESH, ORGANIC ON!</h2>
            </div>
            <div className="mainTextBottom">
              <h1>GO LOVE YOURDOG</h1>
            </div>
          </div>
        </div>
        <div className="main2Wrap">
          <h3>BEST PRODUCTS</h3>
          <div className="bestProducts">
            <MainImg src={mainImg1} idx={0} />
            <MainImg src={mainImg2} idx={1} />
            <MainImg src={mainImg3} idx={2} />
          </div>
        </div>
        <div className="main3Wrap">
          <div className="main3WrapParent">
            <img className="aiDog" src={aiDog} alt="aiDog" />
            <div className="aiText">
              <div className="aiTextTop">
                <p>세계 최초 도입</p>
                <h1>AI를 이용한 맞춤 추천</h1>
              </div>
              <div className="aiTextBottom">
                <h3>나이와 몸무게만 입력하면</h3>
                <h3>우리 아이에게 맞는 사료 추천!</h3>
              </div>
              <button>AI 추천 바로가기</button>
            </div>
          </div>
        </div>
        <div className="main4Wrap">
          <div className="mainBoxWrap">
            <div className="boxLeft">
              <MainBox name={'boxOne'} idx={0} />
              <MainBox name={'boxTwo'} idx={1} />
            </div>
            <div className="boxRight">
              <MainBox name={'boxThree'} idx={2} />
              <MainBox name={'boxFour'} idx={3} />
            </div>
            <div className="mainBoxBack"></div>
          </div>
        </div>
      </div>
    </>
  );
}
