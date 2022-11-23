import '../css/SellerAddProduct.scss';
import { Link } from 'react-router-dom';
import ProductForm from '../../../components/seller/js/ProductForm';
import { useState } from 'react';
import axios from 'axios';
import ModalOk from '../../../components/modal/js/ModalOk';
// import { useSelector } from 'react-redux';

export default function SellerAddProduct() {
  const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
  // const loginData = useSelector((state) => state.login);
  const [modalOn, setModalOn] = useState(false);

  const [categoryId, setCategoryId] = useState('1');
  const [productName, setProductName] = useState('');
  const [price, setPrice] = useState(0);
  const [titleImg, setTitleImg] = useState([
    'https://cdn.discordapp.com/attachments/386786128939843606/1040949752998662195/664a0ccdd29f2239a222c8026a41afb4.jpg',
  ]); //배열로 해야 이미지 보임
  const [detailImg, setDetailImg] = useState([
    'https://cdn.discordapp.com/attachments/386786128939843606/1043406127515320390/A97-01-03_1.jpg',
  ]);

  // const data = { productName, price, titleImg, detailImg };

  axios.defaults['withCredentials'] = true;
  // axios.defaults.headers.common['Content-Type'] = 'multipart/form-data';

  const handleSubmit = () => {
    if (productName.length < 5 || productName.length >= 30) {
      // alert('상품명이 5~30자여야 합니다');
      setModalOn(true);
    } else if (price < 100 || price > 1000000000) {
      alert('판매가가 100원 ~ 1,000,000,000원 사이여야 합니다');
    } else if (titleImg.length === 0) {
      alert('대표이미지를 업로드하셔야 합니다');
    } else if (detailImg.length === 0) {
      alert('상세이미지를 업로드하셔야 합니다');
    } else {
      //formdata에 data입력
      const formData = new FormData();
      formData.append('titleImg', titleImg);
      formData.append('detailImg', detailImg);
      formData.append('productName', productName);
      formData.append('price', price);
      for (const value of formData.value()) {
        console.log(value);
      }

      axios
        .post(`${REACT_APP_API_URL}products/${categoryId}`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
            processData: false,
            Authorization: JSON.parse(
              window.sessionStorage.getItem('accesstoken')
            ),
          },
        })
        .then((res) => {
          console.log(res.data);
          // navigate(`/seller/product`)
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div className="sellerAddProduct">
      <div className="productTitle">
        <h1>상품 등록</h1>
      </div>
      <ProductForm
        categoryId={categoryId}
        setCategoryId={setCategoryId}
        productName={productName}
        setProductName={setProductName}
        price={price}
        setPrice={setPrice}
        titleImg={titleImg}
        setTitleImg={setTitleImg}
        detailImg={detailImg}
        setDetailImg={setDetailImg}
      />
      <div className="addPageBtns">
        <Link to="/seller/product">
          <button className="close">닫기</button>
        </Link>
        <button onClick={handleSubmit}>저장하기</button>
      </div>
      <ModalOk setClick={setModalOn} modalOn={modalOn} />
    </div>
  );
}
