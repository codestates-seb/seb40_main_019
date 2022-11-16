import '../css/productItem.scss';

const ProductItem = ({ data }) => {
  //console.log(data);
  return (
    <div className="itemContainer">
      <div>
        <img src={data.img} alt="img" />
      </div>
      <span>{data.title}</span>
      <p>{data.price}</p>
      {data.new === true && (
        <button style={{ backgroundColor: '#FFB526' }}>New</button>
      )}
      {data.sale === true && (
        <button style={{ backgroundColor: '#1885AE' }}>{data.sale}Sale</button>
      )}
    </div>
  );
};

export default ProductItem;
