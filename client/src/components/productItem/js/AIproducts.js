import '../css/aiProducts.scss';
// import ProductItem from './ProductItem';

export default function AIproducts() {
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
              <div className="formBox">
                <div className="ageBox">
                  <div>나이</div>
                  <input></input>
                </div>
                <div className="weightBox">
                  <div>몸무게</div>
                  <input></input>
                </div>
                <div className="submitBox">
                  <button>입력</button>
                </div>
              </div>

              {/* item 추천 박스 */}
              <div></div>
            </div>
          </div>
        </section>
      </div>
    </>
  );
}
