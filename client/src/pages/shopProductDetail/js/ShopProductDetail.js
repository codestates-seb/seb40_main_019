import '../css/shopProductDetail.scss';
// import Sidebar from '../../../components/sidebar/js/Sidebar';
import ProductDetailBox from '../../../components/shop/js/ProductDetailBox';
import ProductDetailReadme from '../../../components/shop/js/ProductDetailReadme';
import ProductDetailReview from './../../../components/shop/js/ProductDetailReview';
import ProductInfoBox from '../../../components/shop/js/ProductInfoBox';
//상품 디테일 데이터 1개만 담고 보여주게 했음
import axios from 'axios';
import { useState, useEffect } from 'react';

const shopProductDetail = () => {
  const [products, setProducts] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/productdetail/').then((res) => {
      setProducts(res.data);
    });
  }, []);

  return (
    <>
      <div className="detailInfoContainer">
        <div className="leftContainer">
          <ProductDetailBox />
          <ProductDetailReadme />
          <ProductDetailReview />
        </div>
        <div className="rightContainer">
          {/* <ProductInfoBox /> */}
          {products &&
            products.map((product) => {
              return (
                <div key={product.productId}>
                  <ProductInfoBox product={product} />;
                </div>
              );
            })}
        </div>
      </div>
    </>
  );
};

export default shopProductDetail;
