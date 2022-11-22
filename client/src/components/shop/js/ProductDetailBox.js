import '../css/productDetailBox.scss';

export default function ProductDetailBox({ product }) {
  return (
    <div className="detailContainer">
      {product && (
        <>
          {/* <div className="titleImg">
            <img src={product.titleImg} alt="titleImg" />
          </div> */}
          {/* <div className="detailProductBtn">
            <button className="detailBtn">DETAIL</button>
            <button className="reviewBtn">REVIEW</button>
          </div> */}
          <div className="detailText">DETAIL</div>
          <div className="detailImg">
            <img src={product.detailImg} alt="datailImg" />
          </div>
        </>
      )}
    </div>
  );
}
