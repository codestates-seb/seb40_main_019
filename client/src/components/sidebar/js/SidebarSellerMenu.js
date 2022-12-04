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
            <button>대시보드</button>
          </Link>
          <Link to="/seller/product">
            <button>상품 관리</button>
          </Link>
          <Link to="/seller/order">
            <button>판매 관리</button>
          </Link>
          <Link to="/seller/review">
            <button>리뷰 목록</button>
          </Link>
        </ul>
      </div>
    </div>
  );
}
