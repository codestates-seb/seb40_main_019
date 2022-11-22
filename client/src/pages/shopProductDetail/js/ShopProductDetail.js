import '../css/shopProductDetail.scss';
// import Sidebar from '../../../components/sidebar/js/Sidebar';
import ProductDetailBox from '../../../components/shop/js/ProductDetailBox';
import ProductDetailReadme from '../../../components/shop/js/ProductDetailReadme';
import ProductDetailReview from '../../../components/shop/js/ProductDetailReview';

import ProductInfoBox from '../../../components/shop/js/ProductInfoBox';
//상품 디테일 데이터 1개만 담고 보여주게 했음
import axios from 'axios';
import { useState, useEffect } from 'react';
import QuantityBtn from '../../../components/shop/js/QuantityBtn';

const shopProductDetail = () => {
  const [product, setProducts] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/productdetail/').then((res) => {
      setProducts(res.data);
    });
  }, []);
  //상세페이지 detail & review선택
  const [clickBtn, setClickBtn] = useState('detail');

  //quantity
  const [count, setCount] = useState(1);

  return (
    <>
      <div className="detailInfoContainer">
        <div className="leftContainer">
          <div className="titleImg">
            {product && <img src={product.titleImg} alt="titleImg" />}
          </div>
          {clickBtn === 'detail' ? (
            <ProductDetailBox product={product} setClickBtn={setClickBtn} />
          ) : (
            <ProductDetailReview setClickBtn={setClickBtn} />
          )}
          <ProductDetailReadme />
        </div>
        <div className="rightContainer">
          {product && (
            <ProductInfoBox
              product={product}
              count={count}
              setCount={setCount}
            />
          )}
        </div>
        <div className="bottomContainer">
          {product && (
            <>
              <div>
                <div>{product.price} 원</div>
                <QuantityBtn count={count} setCount={setCount} />
              </div>
              <button>CART</button>
              <button>ORDER</button>
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default shopProductDetail;
