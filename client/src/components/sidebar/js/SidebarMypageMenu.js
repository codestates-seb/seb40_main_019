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
            <button>마이페이지</button>
          </Link>
          <Link to="/mypage/user">
            <button>회원정보수정</button>
          </Link>
          <Link to="/mypage/point">
            <button>포인트적립</button>
          </Link>
          <Link to="/mypage/order">
            <button>주문목록</button>
          </Link>
          <Link to="/mypage/review">
            <button>내가작성한리뷰</button>
          </Link>
        </ul>
      </div>
    </div>
  );
}
