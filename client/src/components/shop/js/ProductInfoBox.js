import '../css/productInfoBox.scss';
// import { useEffect } from 'react';
import QuantityBtn from './QuantityBtn';
import BuyAddBtn from './BuyAddBtn';

import ReviewStar from '../../review/js/ReviewStar';

export default function ProductInfoBox({
  product,
  count,
  setCount,
  setModal,
  myPoint,
}) {
  return (
    <>
      <div className="productInfoContainer">
        <div className="stickyContainer">
          {/* header = 사진+별 */}
          <div className="itemHeaderContainer">
            <div className="imgStarBox">
              <img src={product.titleImg} alt="productImg" />
            </div>
            {/* body = new, 제품명, -quantity */}
            <div className="itemBodyContainer">
              {product.new === true && <button>NEW</button>}
              <div className="titleBox">{product.productName}</div>
              <div className="reviewStar">
                <p>평점 {product.average}</p>
                <ReviewStar clickStar={product.average} type={'small'} />
              </div>
            </div>
          </div>
          {/* footer - +Quantity total, buybtn+Nowbtn, addCartBtn */}
          <div className="itemFooterContainer">
            <div className="QuantityBox">
              <div className="totalbox">Quantity</div>
              <div>
                <QuantityBtn count={count} setCount={setCount} />
              </div>
            </div>
            <div className="priceBox">
              <div className="totalBox">Total</div>
              <p>{product.price * count}원</p>
            </div>
            <div className="btnBox">
              <BuyAddBtn
                product={product}
                count={count}
                setModal={setModal}
                myPoint={myPoint}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
