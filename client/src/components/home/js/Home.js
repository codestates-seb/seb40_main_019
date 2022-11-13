import '../css/home.scss';
import video1 from '../../../assets/video/main2.webm';
import video2 from '../../../assets/video/main3.webm';
import video3 from '../../../assets/video/main4.webm';
import video4 from '../../../assets/video/main5.webm';
import video5 from '../../../assets/video/main6.webm';

export default function Home() {
  return (
    <>
      <div className="mainWrap">
        <div className="videoWrap">
          <div className="videoLeftWrap">
            <video className="video2" autoPlay={true} muted loop="true">
              <source src={video2} type="video/webm" />
            </video>
            <video className="video4" autoPlay={true} muted loop="true">
              <source src={video4} type="video/webm" />
            </video>
          </div>
          <div className="videoCenterWrap">
            <video className="video1" autoPlay={true} muted loop="true">
              <source src={video1} type="video/webm" />
            </video>
          </div>
          <div className="videoRightWrap">
            <video className="video5" autoPlay={true} muted loop="true">
              <source src={video5} type="video/webm" />
            </video>
            <video className="video3" autoPlay={true} muted loop="true">
              <source src={video3} type="video/webm" />
            </video>
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
    </>
  );
}
