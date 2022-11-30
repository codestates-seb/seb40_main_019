import '../css/shopProductDetail.scss';
// import Sidebar from '../../../components/sidebar/js/Sidebar';
import ProductDetailBox from '../../../components/shop/js/ProductDetailBox';
import ProductDetailReadme from '../../../components/shop/js/ProductDetailReadme';
import ProductDetailReview from '../../../components/shop/js/ProductDetailReview';
import ProductInfoBox from '../../../components/shop/js/ProductInfoBox';
//상품 디테일 데이터 1개만 담고 보여주게 했음
import { useState } from 'react';
import ProductInfoSmall from '../../../components/shop/js/ProductInfoSmall';
import { useParams } from 'react-router-dom';
import PaymentModal from '../../../components/payment/js/PaymentModal';
import useFetch from '../../../util/useFetch';

export default function shopProductDetail() {
  //:id 에 따라서 해당 상품 상세페이지 보여줌
  const params = useParams();
  // `products/${productsid}`
  const [modal, setModal] = useState(false);
  // const [product, setProducts] = useState({});
  //quantity
  const [count, setCount] = useState(1);

  const [product] = useFetch(`products/${params.id}`);

  // useEffect(() => {
  //   axios.get('http://localhost:3001/productdetail/').then((res) => {
  //     setProducts(res.data);
  //     // console.log(res);
  //   });

  // 장바구니 없을 때 장바구니 생성
  //   if (!JSON.parse(window.localStorage.getItem('cartItem'))) {
  //     // window.alert('장바구니 없음');
  //     window.localStorage.setItem('cartItem', JSON.stringify({}));
  //   }
  // }, []);

  //상세페이지 detail & review선택
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
            <ProductDetailReview setClickBtn={setClickBtn} />
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
            />
          )}
        </div>
      </div>
      {modal && (
        <>
          <PaymentModal
            setModal={setModal}
            totalPrice={product.price * count}
            type="single"
            product={product}
            count={count}
          />
        </>
      )}
    </>
  );
}

//let 찾는상품 = prodcut.filter((x) => {return x.id == id})
//{찾는상품.price}원
