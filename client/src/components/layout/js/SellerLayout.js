import '../css/TotalLayout.scss';
import { Outlet } from 'react-router-dom';
import SidebarSeller from '../../sidebar/js/SidebarSeller';

export default function SellerLayout() {
  return (
    <>
      <div className="sidebar1280">
        <div className="sidebarAb">
          <SidebarSeller />
        </div>
      </div>
      <div className="totalLayout">
        <div className="sidebarLayout">
          <div className="sidebar">
            <SidebarSeller />
          </div>
        </div>
        <div className="contentLayout">
          <Outlet />
        </div>
        <div className="marginLayout"></div>
      </div>
    </>
  );
}
