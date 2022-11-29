import '../css/aiComponent.scss';
// import dogsImg from '../../../assets/img/aiRecommend1.svg';
import poppyImg from '../../../assets/img/aiRecommend2.svg';
import ProductItem from './ProductItem';
import axios from 'axios';
import { useState } from 'react';

export default function AiComponent() {
  //임시 데이터
  const [randomItems, setRandomItems] = useState([]);

  function handleClick() {
    axios.get('http://localhost:3001/randomproducts/').then((res) => {
      setRandomItems(res.data);
    });
  }

  return (
    <>
      <div className="aiContainer">
        {/* input 폼 보여주는 창 */}
        {randomItems.length === 0 ? (
          <div className="beforeAiContainer">
            <div className="leftContainer">
              <h1>AI 맞춤 추천 사료</h1>
              <div>{/* <img src={dogsImg} alt="dogImg" /> */}</div>
              <div>
                <p>나이별, 건강 상태별, 라이프 스타일에 따라</p>
                <p>필요로 하는 영양 맞춤 사료를 만나보세요.</p>
              </div>
            </div>
            <div className="rightContainer">
              <div className="poppyImgBox">
                <img src={poppyImg} alt="dogImg" />
              </div>
              <div className="formContainer">
                <form className="ageForm" name="나이">
                  나이
                  <input type="text" maxLength="3"></input>
                </form>
                <form name="몸무게">
                  몸무게
                  <input type="text" maxLength="3"></input>
                </form>
                <button onClick={handleClick}>입력</button>
              </div>
            </div>
          </div>
        ) : (
          // 3가지 추천 아이템 보여주는 창
          <div className="afterAiContainer">
            <div className="poppyImgRowBox">
              <img src={poppyImg} alt="dogImg" />
            </div>
            <div className="itemListContainer">
              <div className="listBox">
                <div className="rowBoxTitle">
                  <h1>AI 맞춤</h1>
                  <h1>추천 사료</h1>
                </div>
                <div className="gridBox">
                  {randomItems.map((randomItem) => {
                    return (
                      <ProductItem
                        data={randomItem}
                        key={randomItem.productsId}
                      />
                    );
                  })}
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </>
  );
}
