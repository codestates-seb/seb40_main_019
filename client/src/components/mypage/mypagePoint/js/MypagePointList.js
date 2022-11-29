import '../css/mypagePointList.scss';
// import MypagePointListItem from './MypagePointListItem';
// import axios from 'axios';
// import { useState, useEffect } from 'react';
import { useEffect } from 'react';
import { getPointList } from '../../../../util/api/point';

export default function MypagePointList() {
  //임시 더미데이터
  // const [points, setPoints] = useState();
  useEffect(() => {
    // axios.get('http://localhost:3001/points/').then((res) => {
    //   setPoints(res.data);
    // });
    getPointList();
    // data.then((res) => {
    //   setPoints(res);
    // });
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

      {/* {points &&
        points.map((point) => {
          return (
            <div key={point.productsId}>
              <MypagePointListItem point={point} />
            </div>
          );
        })} */}
    </div>
  );
}
