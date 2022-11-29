import '../css/mypagePointList.scss';
import MypagePointListItem from './MypagePointListItem';
import { useState, useEffect } from 'react';
import { getPointList } from '../../../../util/api/point';

export default function MypagePointList() {
  const [pointList, setPointList] = useState([]);

  useEffect(() => {
    let data = getPointList();
    data.then((res) => {
      setPointList(res.data.data);
    });
  }, []);

  return (
    <div className="mypagePointListContainer">
      <div className="pointListTitle">
        <h1>포인트 내역</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="pointTitle">
        <li>거래일</li>
        <li>충전 포인트</li>
        <li>사용 포인트</li>
        <li>잔여 포인트</li>
      </ul>

      {pointList &&
        pointList.map((point) => {
          return (
            <div key={point.pointHistoryId}>
              <MypagePointListItem point={point} />
            </div>
          );
        })}
    </div>
  );
}
