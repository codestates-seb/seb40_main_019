import '../css/shopProductList.scss';
import ProductItems from '../../../components/productItem/js/ProductItems';
import AiComponent from './../../../components/productItem/js/AiComponent';

const shopProductList = () => {
  return (
    <>
      <div className="productsPageContainer">
        <AiComponent />
        <ProductItems />
      </div>
    </>
  );
};

export default shopProductList;
