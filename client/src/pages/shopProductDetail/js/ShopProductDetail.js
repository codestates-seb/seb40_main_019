import '../css/shopProductDetail.scss';
// import Sidebar from '../../../components/sidebar/js/Sidebar';
import ProductInfo from '../../../components/shop/js/ProductInfo';
import ProductDetailBox from '../../../components/shop/js/ProductDetailBox';
import ProductDetailReadme from '../../../components/shop/js/ProductDetailReadme';
import ProductDetailReview from './../../../components/shop/js/ProductDetailReview';

const shopProductDetail = () => {
  return (
    <>
      <div className="detailDiv">
        <div className="detailUp">
          {/* <Sidebar /> */}
          <ProductInfo />
        </div>
        <div className="detailDown">
          <ProductDetailBox />
          <ProductDetailReadme />
          <ProductDetailReview />
        </div>
      </div>
    </>
  );
};

export default shopProductDetail;
