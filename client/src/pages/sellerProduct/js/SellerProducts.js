import '../css/SellerProducts.scss';

import SellerProduct from '../../../components/seller/js/SellerProduct';
import { Link } from 'react-router-dom';
import useFetch from '../../../util/useFetch';

export default function SellerProducts() {
  const [items] = useFetch('products');

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
        <li>상품정보</li>
        <li>가격</li>
        <li>수정/삭제</li>
      </ul>

      {items &&
        items.map((item) => {
          return (
            <div key={item.productId}>
              <SellerProduct item={item} />
            </div>
          );
        })}
    </div>
  );
}
