// import React from 'react';
import '../css/mypagePoint.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';

export default function MypagePoint() {
  return (
    <>
      <div className="pointInner">
        <div className="pointBtnArea">
          <div className="pointBtnFlex">
            <div className="pointBtn">
              <p>포인트</p>
              <div className="pointCircle">
                <FontAwesomeIcon className="pointIcon" icon={faCoins} />
              </div>
            </div>
            <div className="point"></div>
          </div>
          <div className="pointBtnFlex">
            <div className="pointBtn">
              <p>포인트</p>
              <div className="pointCircle">
                <FontAwesomeIcon className="pointIcon" icon={faCoins} />
              </div>
            </div>
            <div className="point"></div>
          </div>
        </div>
      </div>
    </>
  );
}
