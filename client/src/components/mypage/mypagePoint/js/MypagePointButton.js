// import { useEffect } from 'react';
import { useEffect, useState } from 'react';
import '../css/mypagePointButton.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';
import { getPoint } from '../../../../util/api/point';
import { tossPay } from '../../../../util/api/payment';
import { useSelector } from 'react-redux';
import { formatMoney } from '../../../../util/function/formatData';

export default function MypagePointButton() {
  const [point, setPoint] = useState(0);
  const [value, setValue] = useState('');
  const user = useSelector((state) => state.user);

  useEffect(() => {
    getPoint().then((res) => {
      setPoint(res.data);
    });
  }, []);

  return (
    <>
      <div className="pointBtnArea">
        <div className="pointBtnFlex">
          <div className="pointBtn">
            {/* <p>포인트</p> */}
            <div className="pointCircle colorBlue">
              <FontAwesomeIcon className="pointIcon" icon={faCoins} />
            </div>
          </div>
          <div className="point">
            <p>{formatMoney(point)}</p>
          </div>
        </div>
        <div className="pointBtnFlex">
          <div className="pointBtn">
            {/* <p>포인트 충전</p> */}
            <div
              // onClick={() => tossPay(user.nickname, value)}
              className="pointCircle colorYellow"
            >
              <FontAwesomeIcon className="pointIcon" icon={faCoins} />
            </div>
          </div>
          <div className="point">
            <input
              value={value}
              onChange={(e) => setValue(e.target.value)}
              type="text"
              placeholder="포인트 입력"
            />
            <div className="chargeContainer">
              <button
                onClick={() => tossPay(user.nickname, value)}
                className="yellowBtn"
              >
                충전
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
