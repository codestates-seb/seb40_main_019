import '../css/shopProductDetail.scss';
import Sidebar from '../../../components/sidebar/js/Sidebar';
import ProductInfo from '../../../components/shop/js/ProductInfo';
import ProductDetailBox from '../../../components/shop/js/ProductDetailBox';

const shopProductDetail = () => {
  return (
    <>
      <div className="detailDiv">
        <div className="detailUp">
          <Sidebar />
          <ProductInfo />
        </div>
        <div className="detailDown">
          <ProductDetailBox />
        </div>
      </div>
    </>
  );
};

export default shopProductDetail;
