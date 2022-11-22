import '../css/aiProducts.scss';
import { useState } from 'react';
//import ProductItem from './ProductItem';
// import axios from 'axios';
// import { useState } from 'react';

export default function AIproducts() {
  const [isInput] = useState(false);
  // useEffect(() => {
  //   axios.get('http://localhost:3001/products/').then((res) => {
  //     setData(res.data);
  //     // console.log(res.data);
  //   });
  // }, []);
  // //console.log(data);

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

              {!isInput ? (
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
                    <button>입력</button>
                  </div>
                </div>
              ) : (
                <>
                  <div className="recommendBox">아이템 3개 추천</div>
                </>
              )}
            </div>
          </div>
        </section>
      </div>
    </>
  );
}
