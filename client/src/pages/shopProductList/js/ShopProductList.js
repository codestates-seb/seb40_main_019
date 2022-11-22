import '../css/shopProductList.scss';
import ProductItems from '../../../components/productItem/js/ProductItems';
import AIproducts from '../../../components/productItem/js/AIproducts';

const shopProductList = () => {
  return (
    <>
      <div className="productsPageContainer">
        <AIproducts />
        <ProductItems />
      </div>
    </>
  );
};

export default shopProductList;
