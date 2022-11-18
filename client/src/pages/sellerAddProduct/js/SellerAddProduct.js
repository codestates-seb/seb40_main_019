import ImgUploader from '../../../components/seller/js/ImgUploader';
import '../css/SellerAddProduct.scss';
import { Link } from 'react-router-dom';

export default function SellerAddProduct() {
  return (
    <div className="sellerAddProduct">
      <div className="productTitle">
        <h1>상품 등록</h1>
      </div>

      <div className="productContent">
        <section className="category">
          <h1>카테고리</h1>
          <div className="categoryBtns">
            <button>건식 사료</button>
            <button>습식 사료</button>
            <button>자연식</button>
            <button>동결 사료</button>
          </div>
        </section>
        <section className="productName">
          <h1>상품명</h1>
          <input></input>
        </section>
        <section className="price">
          <h1>판매가</h1>
          <input></input>원
        </section>
        <div className="titleImg">
          <h1>대표이미지</h1>
          <ImgUploader />
        </div>
        <div className="detailImg">
          <h1>상세이미지</h1>
          <ImgUploader />
        </div>
      </div>

      <div className="addPageBtns">
        <Link to="/seller/product">
          <button className="close">닫기</button>
        </Link>
        <button>저장하기</button>
      </div>
    </div>
  );
}
