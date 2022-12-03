import '../css/shopLayout.scss';
import { Outlet } from 'react-router-dom';
import Sidebar from '../../sidebar/js/Sidebar';

export default function ShopLayout() {
  return (
    <>
      <div className="mainInner">
        {location.pathname.split('/')[2] === 'detail' ? null : (
          <div className="sideBar">
            <div className="sticky">
              <Sidebar />
            </div>
          </div>
        )}
        <div className="container">
          <Outlet />
        </div>
      </div>
    </>
  );
}
