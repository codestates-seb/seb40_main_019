import '../css/productInfoBox.scss';
import QuantityBtn from './QuantityBtn';
import BuyAddBtn from './BuyAddBtn';
import ReviewStar from '../../review/js/ReviewStar';

export default function ProductInfoBox({ product, count, setCount }) {
  return (
    <>
      <div className="productInfoContainer">
        <div className="stickyContainer">
          {/* header = 사진+별 */}
          <div className="itemHeaderContainer">
            <div className="imgStarBox">
              <img src={product.titleImg} alt="productImg" />
              <div className="reviewStar">
                <ReviewStar clickStar={product.average} type={'small'} />
              </div>
            </div>
            {/* body = new, 제품명, quantity */}
            <div className="itemBodyContainer">
              {product.new === true && <button>NEW</button>}

              <div className="titleBox">{product.title}</div>
              <div className="QuantityBox">
                <span>Quantity</span>
                <QuantityBtn count={count} setCount={setCount} />
              </div>
            </div>
          </div>
          {/* footer - total, buybtn+Nowbtn, addCartBtn */}
          <div className="itemFooterContainer">
            <div className="priceBox">
              <div className="totalbox">total</div>
              <div>{product.price * count}원</div>
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
