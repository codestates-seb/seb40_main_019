import '../css/shopProductDetail.scss';
// import Sidebar from '../../../components/sidebar/js/Sidebar';
import ProductDetailBox from '../../../components/shop/js/ProductDetailBox';
import ProductDetailReadme from '../../../components/shop/js/ProductDetailReadme';
import ProductDetailReview from './../../../components/shop/js/ProductDetailReview';

import ProductInfoBox from '../../../components/shop/js/ProductInfoBox';

const shopProductDetail = () => {
  return (
    <>
      <div className="detailInfoContainer">
        <div className="leftContainer">
          <ProductDetailBox />
          <ProductDetailReadme />
          <ProductDetailReview />
        </div>
        <div className="rightContainer">
          <ProductInfoBox />
        </div>
      </div>
    </>
  );
};

export default shopProductDetail;
