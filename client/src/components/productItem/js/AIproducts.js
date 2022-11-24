import '../css/aiProducts.scss';
import axios from 'axios';
import { useState } from 'react';
import ProductItem from './ProductItem';

export default function AIproducts() {
  const [randomItems, setRandomItems] = useState([]);

  function handleClick() {
    axios.get('http://localhost:3001/randomproducts/').then((res) => {
      setRandomItems(res.data);
    });
  }

  return (
    <>
      <div>
        <div className="aiTitle">
          <h1>AI 맞춤 추천 사료</h1>
        </div>
        <section className="yellowBoxRow">
          <div className="yellowBox">
            <div className="whiteBox">
              {/* input박스 */}

              {randomItems.length === 0 ? (
                <div className="formBox">
                  <div className="ageBox">
                    <div>나이</div>
                    <input type="text"></input>
                  </div>
                  <div className="weightBox">
                    <div>몸무게</div>
                    <input type="text"></input>
                  </div>
                  <div className="submitBox">
                    <button onClick={handleClick}>입력</button>
                  </div>
                </div>
              ) : (
                <div className="recommendBox">
                  {randomItems.map((randomItem) => {
                    return (
                      <ProductItem
                        data={randomItem}
                        key={randomItem.productsId}
                      />
                    );
                  })}
                </div>
              )}
            </div>
          </div>
        </section>
      </div>
    </>
  );
}
