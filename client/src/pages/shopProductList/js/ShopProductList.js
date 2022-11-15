import '../css/shopProductList.scss';
import ProductItems from '../../../components/productItem/js/ProductItems';
import Sidebar from '../../../components/sidebar/js/Sidebar';

const shopProductList = () => {
  return (
    <>
      <div className="productsPageContainer">
        <Sidebar />
        <ProductItems />
      </div>
    </>
  );
};

export default shopProductList;
