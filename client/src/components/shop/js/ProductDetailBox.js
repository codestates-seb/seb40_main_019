import '../css/productDetailBox.scss';

export default function ProductDetailBox({ product, setClickBtn }) {
  return (
    <div className="detailContainer">
      {product && (
        <>
          <div className="detailProductBtn">
            <button className="detailBtn" onClick={() => setClickBtn('detail')}>
              DETAIL
            </button>
            <button className="reviewBtn" onClick={() => setClickBtn('review')}>
              REVIEW
            </button>
          </div>
          <div className="detailImg">
            <img src={product.detailImg} alt="datailImg" />
          </div>
        </>
      )}
    </div>
  );
}
