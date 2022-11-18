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
    <div className="sellerProducts">
      <div className="productTitle">
        <h1>상품 관리</h1>
        <div className="productBtn">
          <button className="addBtn">
            <i className="fa-solid fa-plus"></i>상품 등록
          </button>
        </div>
      </div>

      <div className="lineBold"></div>

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
