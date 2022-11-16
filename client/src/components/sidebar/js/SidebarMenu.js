import '../css/sidebarMenu.scss';
import { Link } from 'react-router-dom'; //수정 삭제?

export default function SidebarMenu() {
  return (
    <div className="sideMargin">
      <div className="sidebarContainer">
        <Link to="/">
          <div className="logoBox" />
        </Link>
        <ul className="tittleList">
          <li>NEW</li>
          <li>건식 사료</li>
          <li>습식 사료</li>
          <li>자연식</li>
          <li>동결 사료</li>
          <li>SALE</li>
        </ul>
      </div>
    </div>
  );
}
