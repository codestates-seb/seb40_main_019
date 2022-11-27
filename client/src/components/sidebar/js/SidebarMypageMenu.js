import { Link } from 'react-router-dom';
import '../css/sidebarMenu.scss';

export default function SidebarMypageMenu() {
  return (
    <div className="sideMargin">
      <div className="sidebarContainer">
        <Link to="/">
          <div className="logoBox" />
        </Link>
        <ul className="tittleList">
          <Link to="/mypage">
            <li>마이페이지</li>
          </Link>
          <Link to="/mypage/user">
            <li>회원정보수정</li>
          </Link>
          <Link to="/mypage/point">
            <li>포인트적립</li>
          </Link>
          <Link to="/mypage/order">
            <li>주문목록</li>
          </Link>
          <Link to="/mypage/review">
            <li>내가작성한리뷰</li>
          </Link>
        </ul>
      </div>
    </div>
  );
}
