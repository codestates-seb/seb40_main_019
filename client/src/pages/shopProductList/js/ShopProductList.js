import '../css/shopProductList.scss';
import ProductItems from '../../../components/productItem/js/ProductItems';
import AIproducts from '../../../components/productItem/js/AIproducts';
import AiComponent from './../../../components/productItem/js/AiComponent';

const shopProductList = () => {
  return (
    <>
      <div className="productsPageContainer">
        <AIproducts />
        <AiComponent />
        <ProductItems />
      </div>
    </>
  );
};

export default shopProductList;
