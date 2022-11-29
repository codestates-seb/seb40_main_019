import '../css/productItem.scss';
// import { useNavigate } from 'react-router-dom';

const ProductItem = ({ data }) => {
  return (
    <div className="itemContainer">
      <div className="imgBox">
        <img src={data.titleImg} alt="img" />
      </div>
      <div className="textContainer">
        <div className="titleBox">
          <span>{data.productName}</span>
        </div>
        <div className="priceBox">
          <p>{data.price}원</p>
          {data.new === true && (
            <button style={{ backgroundColor: '#FFB526' }}>New</button>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProductItem;
