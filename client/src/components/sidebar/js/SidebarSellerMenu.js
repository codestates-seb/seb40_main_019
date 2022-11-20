import { Link } from 'react-router-dom';
import '../css/sidebarMenu.scss';

export default function SidebarSellerMenu() {
  return (
    <div className="sideMargin">
      <div className="sidebarContainer">
        <Link to="/">
          <div className="logoBox" />
        </Link>
        <ul className="tittleList">
          <Link to="/seller">
            <li>대시보드</li>
          </Link>
          <Link to="/seller/product">
            <li>상품 관리</li>
          </Link>
          <Link to="/seller/order">
            <li>주문 내역</li>
          </Link>
          <Link to="/seller/delivering">
            <li>배송 중</li>
          </Link>
          <Link to="/seller/deliverycom">
            <li>배송 완료</li>
          </Link>
          <Link to="/seller/review">
            <li>리뷰 목록</li>
          </Link>
        </ul>
      </div>
    </div>
  );
}
