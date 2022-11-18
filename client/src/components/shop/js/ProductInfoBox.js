import '../css/productInfoBox.scss';
import QuantityBtn from './QuantityBtn';
import BuyAddBtn from './BuyAddBtn';
export default function ProductInfoBox() {
  return (
    <>
      <div className="productInfoContainer">
        <div className="stickyContainer">
          {/* header = 사진+별 */}
          <div className="itemHeaderContainer">
            <div className="imgStarBox">
              <div className="productImg"></div>
              <div className="reviewStar">⭐⭐⭐⭐⭐</div>
            </div>
            {/* body = new, 제품명, quantity */}
            <div className="itemBodyContainer">
              <button>NEW</button>
              <div className="titleBox">title</div>
              <QuantityBtn />
            </div>
          </div>
          {/* footer - total, buybtn+Nowbtn, addCartBtn */}
          <div className="itemFooterContainer">
            <div className="priceBox">
              <div className="totalbox">total</div>
              <div>6000원</div>
            </div>
            <div className="btnBox">
              <BuyAddBtn />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
