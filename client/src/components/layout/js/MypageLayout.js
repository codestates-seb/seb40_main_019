import '../css/shopLayout.scss';
import { Outlet } from 'react-router-dom';
import SidebarMypage from '../../sidebar/js/SidebarMypage';

export default function ShopLayout() {
  return (
    <>
      <div className="mainInner">
        <div className="sideBar">
          <div className="sticky">
            <SidebarMypage />
          </div>
        </div>
        <div className="container">
          <Outlet />
        </div>
      </div>
    </>
  );
}
