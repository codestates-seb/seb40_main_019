import '../css/productInfo.scss';
import QuantityBtn from './../../../components/shop/js/QuantityBtn';
import BuyAddBtn from './../../../components/shop/js/BuyAddBtn';

export default function ProductInfo() {
  return (
    <>
      <div className="imgContainer">
        <div className="bigImgBox"></div>
      </div>
      {/* 정보 : 이미지+별, new logo, 상품명, 갯수, 토탈, 버튼 */}
      <div className="infoContainer">
        {/* header (item-header-box) */}
        <div className="itemHeaderBox">
          {/* star-box */}
          <div className="starBox">
            <div className="imgBox"></div>
            <span>⭐⭐⭐⭐⭐</span>
          </div>
          {/* product-desc */}
          <div className="productDescBox">
            <div>
              <button style={{ backgroundColor: '#FFB526' }}>New</button>
              <button style={{ backgroundColor: '#1885AE' }}>Sale</button>
            </div>
            <div>상품명</div>
            <QuantityBtn />
          </div>
        </div>
        <hr className="line" />
        {/* body (item-body-box) */}
        <div className="itemBodyBox">
          <span className="price"> 100,000 ₩</span>
          <div className="totalBox">
            <span className="discountP">Total</span>
            <span>80,000 ₩</span>
          </div>
        </div>
        {/* footer (item-footer-box) */}
        <div className="itemFooterBox">
          <BuyAddBtn />
        </div>
      </div>
    </>
  );
}
