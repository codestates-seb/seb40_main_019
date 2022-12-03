import '../css/shopLayout.scss';
import { Outlet } from 'react-router-dom';
import SidebarSeller from '../../sidebar/js/SidebarSeller';

export default function ShopLayout() {
  return (
    <>
      <div className="mainInner">
        <div className="sideBar">
          <div className="sticky">
            <SidebarSeller />
          </div>
        </div>
        <div className="container">
          <Outlet />
        </div>
      </div>
    </>
  );
}
