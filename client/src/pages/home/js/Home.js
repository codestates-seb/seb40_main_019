import '../css/home.scss';
import video1 from '../../../assets/video/main2.webm';
import video2 from '../../../assets/video/main3.webm';
import video3 from '../../../assets/video/main4.webm';
import video4 from '../../../assets/video/main5.webm';
import video5 from '../../../assets/video/main6.webm';
import mainImg1 from '../../../assets/img/mainImg1.png';
import mainImg2 from '../../../assets/img/mainImg2.png';
import mainImg3 from '../../../assets/img/mainImg3.jpg';
import MainImg from '../../../components/home/js/MainImg';
import MainVideo from '../../../components/home/js/MainVideo';

export default function Home() {
  return (
    <>
      <div className="home">
        <div className="mainWrap">
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
            <MainImg src={mainImg1} />
            <MainImg src={mainImg2} />
            <MainImg src={mainImg3} />
          </div>
        </div>
      </div>
    </>
  );
}
