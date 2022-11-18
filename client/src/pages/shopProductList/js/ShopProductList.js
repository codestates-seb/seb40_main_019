import '../css/shopProductList.scss';
import ProductItems from '../../../components/productItem/js/ProductItems';

const shopProductList = () => {
  return (
    <>
      <div className="productsPageContainer">
        <ProductItems />
      </div>
    </>
  );
};

export default shopProductList;
