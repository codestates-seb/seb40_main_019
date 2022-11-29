import '../css/productItems.scss';
import ProductItem from './ProductItem';
import useFetch from '../../../util/useFetch';
import { useSelector } from 'react-redux';
import { useState } from 'react';

function ProductItems() {
  const [filterId, setFilter] = useState('1');
  const categoryState = useSelector((state) => state.product);
  let [products] = [];
  if (categoryState.allProduct) {
    [products] = useFetch(
      `products/filter/${filterId}`,
      categoryState,
      filterId
    );
  } else {
    [products] = useFetch(
      `products/category/${categoryState.category}/${filterId}`,
      categoryState,
      filterId
    );
  }

  return (
    <>
      <div className="filterContainer">
        <button onClick={() => setFilter('1')}>최신순</button>
        <button onClick={() => setFilter('2')}>인기순</button>
        <button onClick={() => setFilter('3')}>높은 가격순</button>
        <button onClick={() => setFilter('4')}>낮은 가격순</button>
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
