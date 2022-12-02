import '../css/productItem.scss';
import { useNavigate } from 'react-router-dom';
import { formatMoney } from '../../../util/function/formatData';

const ProductItem = ({ data, key }) => {
  const navigate = useNavigate();
  const clickProduct = () => {
    navigate(`/product/detail/${data.productId}`);
  };
  return (
    <div key={key} className="flexInner">
      <div
        role="button"
        className="itemContainer"
        onClick={clickProduct}
        onKeyDown={clickProduct}
        tabIndex={0}
      >
        <div className="imgBox">
          <img src={data.titleImg} alt="img" />
        </div>
        <div className="textContainer">
          <div className="titleBox">
            <span>{data.productName}</span>
          </div>
          <div className="priceBox">
            <p>{formatMoney(data.price)}원</p>
            {data.new === true && (
              <button style={{ backgroundColor: '#FFB526' }}>New</button>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductItem;
