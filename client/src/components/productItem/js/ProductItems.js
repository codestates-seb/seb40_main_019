import '../css/productItems.scss';
import ProductItem from './ProductItem';
import useFetch from '../../../util/useFetch';
import { useDispatch, useSelector } from 'react-redux';
import { useState } from 'react';
import ReactPaginate from 'react-paginate';
import { setCategory } from '../../../redux/reducers/productSlice';

function ProductItems() {
  const dispatch = useDispatch();
  const categoryState = useSelector((state) => state.product);

  const [pageInfo, setPageInfo] = useState();

  const [filterId, setFilter] = useState('1');

  const handlePageChange = (page) => {
    dispatch(
      setCategory({
        allProduct: categoryState.allProduct,
        category: categoryState.category,
        categoryPage: { selected: page.selected + 1 },
      })
    );
    window.scrollTo({
      top: 700,
    });
  };

  let [products] = [];
  if (categoryState.allProduct) {
    [products] = useFetch(
      `products/filter/${filterId}`,
      categoryState.categoryPage,
      setPageInfo,
      categoryState,
      filterId
    );
  } else {
    [products] = useFetch(
      `products/category/${categoryState.category}/${filterId}`,
      categoryState.categoryPage,
      setPageInfo,
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
      <ReactPaginate
        pageCount={pageInfo && pageInfo.totalPages}
        pageRangeDisplayed={5}
        marginPagesDisplayed={0}
        breakLabel={''}
        previousLabel={'<'}
        nextLabel={'>'}
        onPageChange={handlePageChange}
        containerClassName={'pagination-ul'}
        pageClassName={'pageButton'}
        activeClassName={'currentPage'}
        previousClassName={'switchPage'}
        nextClassName={'switchPage'}
      />
    </>
  );
}

export default ProductItems;
