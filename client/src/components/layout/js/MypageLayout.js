import '../css/shopLayout.scss';
import { Outlet } from 'react-router-dom';
import SidebarMypage from '../../sidebar/js/SidebarMypage';

export default function ShopLayout() {
  return (
    <>
      <div className="mainInner">
        {/* <div className="sidebar1280">
          <div className="sidebarAb">
            <Sidebar />
          </div>
        </div> */}
        <div className="sideBar">
          <div className="sticky">
            <SidebarMypage />
          </div>
        </div>
        <div className="container">
          <Outlet />
          {/* <div className="test"></div> */}
        </div>
      </div>
    </>
  );
}
