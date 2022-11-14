import '../css/sidebar.scss';
import { Link } from 'react-router-dom';

function Sidebar() {
  return (
    <>
      <div className="sidebarWrapper">
        <Link to="/">
          <div className="logoBox" />
        </Link>
        <ul className="tittleList">
          <li>New</li>
          <li>건식 사료</li>
          <li>습식 사료</li>
          <li>자연식</li>
          <li>동결 사료</li>
          <li>SALE</li>
        </ul>
      </div>
    </>
  );
}

export default Sidebar;
