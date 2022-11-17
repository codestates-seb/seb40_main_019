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
          <li>대시보드</li>
          <li>상품 관리</li>
          <li>판매 관리</li>
          <li>리뷰 목록</li>
        </ul>
      </div>
    </div>
  );
}
