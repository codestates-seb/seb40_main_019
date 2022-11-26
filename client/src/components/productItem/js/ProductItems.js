import '../css/productItems.scss';
import ProductItem from './ProductItem';
import axios from 'axios';
import { useState, useEffect } from 'react';

function ProductItems() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:3001/products/').then((res) => {
      setProducts(res.data);
      // console.log(res.data);
    });
  }, []);
  //console.log(data);

  // const navigate = useNavigate();
  // const clickItem = () => {
  //   navigate(`products/${productsId}`);
  // };

  return (
    <>
      <div className="filterContainer">
        <button>인기순</button>
        <button>낮은 가격순</button>
        <button>높은 가격순</button>
        <button>최신순</button>
      </div>
      <div className="itemsContainer">
        {products &&
          products.map((product) => {
            return <ProductItem data={product} key={product.productsId} />;
          })}
      </div>
    </>
  );
}

export default ProductItems;
