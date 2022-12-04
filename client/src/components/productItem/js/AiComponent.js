import '../css/aiComponent.scss';
import dogsImg from '../../../assets/img/aiRecommend1.svg';
import poppyImg from '../../../assets/img/aiRecommend2.svg';
import ProductItem from './ProductItem';
import { useState } from 'react';
import { recommendProduct } from '../../../util/api/aiRecommend';

export default function AiComponent() {
  //임시 데이터
  const [randomItems, setRandomItems] = useState([]);

  const [data, setData] = useState({
    age: '',
    weight: '',
  });

  const onChangeInput = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleClick = () => {
    let res = recommendProduct(data);
    res.then((data) => {
      setRandomItems(data.data);
    });
  };

  return (
    <>
      <div className="aiContainer">
        {randomItems.length === 0 ? (
          <div className="beforeAiContainer">
            <div className="leftContainer">
              <h1>AI 맞춤 추천 사료</h1>
              <div>
                <img className="aiImg" src={dogsImg} alt="dogImg" />
              </div>
              <div className="pTagBox">
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
                  <input
                    onChange={onChangeInput}
                    id="age"
                    name="age"
                    value={data.age}
                    type="text"
                  />
                </form>
                <form className="weightForm" name="몸무게">
                  몸무게
                  <input
                    onChange={onChangeInput}
                    id="weight"
                    name="weight"
                    value={data.weight}
                    type="text"
                    placeholder="               kg"
                  />
                </form>
                <button onClick={handleClick}>입력</button>
              </div>
            </div>
          </div>
        ) : (
          <div className="afterAiContainer">
            <div className="poppyImgRowBox">
              <img src={poppyImg} alt="dogImg" />
            </div>
            <div className="itemListContainer">
              <div className="listBox">
                <div className="rowBoxTitle">
                  <h1>AI 맞춤 추천 사료</h1>
                </div>
                <div className="flexBox">
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
              <div className="resetBtn">
                <button onClick={() => setRandomItems([])}>재입력</button>
              </div>
            </div>
          </div>
        )}
      </div>
    </>
  );
}
