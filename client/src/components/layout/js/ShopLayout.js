import '../css/shopLayout.scss';
import { Outlet, useLocation } from 'react-router-dom';
import Sidebar from '../../sidebar/js/Sidebar';

export default function ShopLayout() {
  const { pathname } = useLocation();
  console.log(pathname);
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
