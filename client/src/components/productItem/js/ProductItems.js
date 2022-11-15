import '../css/productItems.scss';
import ProductItem from './ProductItem';
import axios from 'axios';
import { useState, useEffect } from 'react';

function ProductItems() {
  const [data, setData] = useState();

  useEffect(() => {
    axios.get('http://localhost:3001/products/').then((res) => {
      setData(res.data);
      // console.log(res.data);
    });
  }, []);
  //console.log(data);
  return (
    <div className="itemsContainer">
      {data &&
        data.map((data, i) => {
          return <ProductItem data={data} key={i} />;
        })}
    </div>
  );
}

export default ProductItems;
