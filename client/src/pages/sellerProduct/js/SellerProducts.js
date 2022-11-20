import '../css/SellerProducts.scss';
import { useEffect, useState } from 'react';
import axios from 'axios';
import SellerProduct from '../../../components/seller/js/SellerProduct';
import { Link } from 'react-router-dom';

export default function SellerProducts() {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/sellerproducts/').then((res) => {
      setItems(res.data);
    });
  }, []);

  return (
    <div className="sellerProducts">
      <div className="productTitle">
        <h1>상품 관리</h1>
        <div className="productBtn">
          <Link to="/seller/add">
            <button className="addBtn">
              <i className="fa-solid fa-plus"></i>상품 등록
            </button>
          </Link>
        </div>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>등록일</li>
        <li>상품명</li>
        <li>가격</li>
        <li>수정/삭제</li>
      </ul>

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
