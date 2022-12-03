import '../css/shopProductDetail.scss';
import ProductDetailBox from '../../../components/shop/js/ProductDetailBox';
import ProductDetailReadme from '../../../components/shop/js/ProductDetailReadme';
import ProductDetailReview from '../../../components/shop/js/ProductDetailReview';
import ProductInfoBox from '../../../components/shop/js/ProductInfoBox';
import { useState, useEffect } from 'react';
import ProductInfoSmall from '../../../components/shop/js/ProductInfoSmall';
import { useParams } from 'react-router-dom';
import PaymentModal from '../../../components/payment/js/PaymentModal';
import useFetchNotPage from '../../../util/useFetchOne';
import { getPoint } from '../../../util/api/point';

export default function shopProductDetail() {
  const params = useParams();
  const [modal, setModal] = useState(false);
  const [count, setCount] = useState(1);

  const [product] = useFetchNotPage(`products/${params.id}`);

  const [myPoint, setMyPoint] = useState(0);

  useEffect(() => {
    if (!JSON.parse(window.localStorage.getItem('cartItem'))) {
      window.localStorage.setItem('cartItem', JSON.stringify({}));
    }

    getPoint().then((res) => {
      setMyPoint(res.data);
    });
  }, []);

  const [clickBtn, setClickBtn] = useState('detail');
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
            <ProductDetailReview setClickBtn={setClickBtn} id={params.id} />
          )}
          <ProductDetailReadme />
        </div>
        <div className="InfoContainer">
          {product && (
            <ProductInfoBox
              product={product}
              count={count}
              setCount={setCount}
              setModal={setModal}
              myPoint={myPoint}
            />
          )}
        </div>
        <div className="bottomContainer">
          {product && (
            <ProductInfoSmall
              product={product}
              count={count}
              setCount={setCount}
              setModal={setModal}
              myPoint={myPoint}
            />
          )}
        </div>
      </div>
      {modal && (
        <>
          <PaymentModal
            setModal={setModal}
            type="single"
            product={product}
            count={count}
          />
        </>
      )}
    </>
  );
}
