import '../../sellerAddProduct/css/SellerAddProduct.scss';
import { Link, useLocation } from 'react-router-dom';
import ProductForm from '../../../components/seller/js/ProductForm';
import { handleEdit } from '../../../util/api/product';
import { useState } from 'react';

export default function SellerEditProduct() {
  const location = useLocation();
  const { item } = location.state;

  //state
  const [categoryId, setCategoryId] = useState('1');
  const [productName, setProductName] = useState(item.productName);
  const [price, setPrice] = useState(item.price);
  const [titleImg, setTitleImg] = useState([item.titleImg]); //배열로 해야 이미지 보임
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
    handleEdit(data, item);
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
      />

      <div className="addPageBtns">
        <Link to="/seller/product">
          <button className="close">닫기</button>
        </Link>
        <button onClick={clickEdit}>수정하기</button>
      </div>
    </div>
  );
}
