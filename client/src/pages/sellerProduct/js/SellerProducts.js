import '../css/SellerProducts.scss';
import { useEffect, useState } from 'react';
import axios from 'axios';
import SellerProduct from '../../../components/seller/js/SellerProduct';

export default function SellerProducts() {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/sellerproducts/').then((res) => {
      setItems(res.data);
      console.log(res.data);
    });
  }, []);

  return (
    <div className="sellerProduct">
      <div className="productTitle">
        <h1>상품 관리</h1>
        <div className="productBtn">
          <button className="deleteBtn">선택 삭제</button>
          <button className="addBtn">상품 등록</button>
        </div>
        <div className="lineBold"></div>
        <div className="semiTitle">
          <div className="checkbox">
            <i className="fa-solid fa-square-check"></i>
          </div>
          <h3 className="createdAt">등록일</h3>
          <h3 className="titleImg">대표 이미지</h3>
          <h3 className="title">상품명</h3>
          <h3 className="price">판매가</h3>
          <h3 className="edit">수정</h3>
        </div>
        <div className="lineLight"></div>
      </div>
      {items &&
        items.map((item) => {
          return (
            <div key={item.productsId}>
              <SellerProduct item={item} />
            </div>
          );
        })}
    </div>
  );
}
