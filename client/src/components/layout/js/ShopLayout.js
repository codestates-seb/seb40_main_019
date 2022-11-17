import '../css/TotalLayout.scss';
import { Outlet } from 'react-router-dom';
import Sidebar from '../../sidebar/js/Sidebar';

export default function ShopLayout() {
  return (
    <>
      <div className="sidebar1280">
        <div className="sidebarAb">
          <Sidebar />
        </div>
      </div>
      <div className="totalLayout">
        <div className="sidebarLayout">
          <div className="sidebar">
            <Sidebar />
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
