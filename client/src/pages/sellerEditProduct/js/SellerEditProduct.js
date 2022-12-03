import '../../sellerAddProduct/css/SellerAddProduct.scss';
import { useLocation, useNavigate } from 'react-router-dom';
import ProductForm from '../../../components/seller/js/ProductForm';
import { handleEdit } from '../../../util/api/product';
import { useState } from 'react';
import ModalOk from '../../../components/modal/js/ModalOk';

export default function SellerEditProduct() {
  const navigate = useNavigate();
  const location = useLocation();
  const { item } = location.state;

  //state
  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const [categoryId, setCategoryId] = useState('1');
  const [productName, setProductName] = useState(item.productName);
  const [price, setPrice] = useState(item.price);
  const [titleImg, setTitleImg] = useState([item.titleImg]);
  const [detailImg, setDetailImg] = useState([item.detailImg]);

  const data = {
    categoryId,
    productName,
    price,
    titleImg,
    detailImg,
    productId: item.productId,
  };

  const clickEdit = () => {
    handleEdit(data, item, setModalOn, setModalText);
  };

  return (
    <div className="sellerAddProduct">
      <div className="productTitle">
        <h1>상품 수정</h1>
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
        pastTitleImg={item.titleImg}
        pastDetailImg={item.detailImg}
      />

      <div className="addPageBtns">
        <button className="close" onClick={() => navigate('/seller/product')}>
          닫기
        </button>
        <button onClick={clickEdit}>수정하기</button>
      </div>
      <ModalOk
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
      />
    </div>
  );
}
