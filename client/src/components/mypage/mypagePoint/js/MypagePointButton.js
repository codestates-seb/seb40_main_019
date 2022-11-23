// import React from 'react';
import '../css/mypagePointButton.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';

export default function MypagePointButton() {
  return (
    <>
      <div className="pointBtnArea">
        <div className="pointBtnFlex">
          <div className="pointBtn">
            <p>포인트</p>
            <div className="pointCircle colorBlue">
              <FontAwesomeIcon className="pointIcon" icon={faCoins} />
            </div>
          </div>
          <div className="point"></div>
        </div>
        <div className="pointBtnFlex">
          <div className="pointBtn">
            <p>포인트 충전</p>
            <div className="pointCircle colorYellow">
              <FontAwesomeIcon className="pointIcon" icon={faCoins} />
            </div>
          </div>
          <div className="point"></div>
        </div>
      </div>
    </>
  );
}
