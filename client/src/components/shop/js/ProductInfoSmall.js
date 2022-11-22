import '../css/productInfoSmall.scss';
import QuantityBtn from './QuantityBtn';

export default function ProductInfoSmall({ product, count, setCount }) {
  return (
    <div className="infoSmallContainer">
      <div className="infoSmallTitle">
        <h1>{product.price * count} 원</h1>
        <QuantityBtn count={count} setCount={setCount} />
      </div>
      <div className="infoSmallBottom">
        <button className="cart">CART</button>
        <button>ORDER</button>
      </div>
    </div>
  );
}
