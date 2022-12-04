import { useState } from 'react';
import '../css/sidebar.scss';
import SidebarSellerMenu from './SidebarSellerMenu';
export default function Sidebar() {
  const [click, setClick] = useState(false);
  const handleClickMenu = () => {
    setClick(!click);
  };
  return (
    <div className="sidebar">
      <button className="sidebarMenuBtn" onClick={handleClickMenu}>
        {click ? (
          <i className="fa-solid fa-x"></i>
        ) : (
          <i className="fa-solid fa-bars"></i>
        )}
      </button>
      {click && (
        <div className="sidebarModal">
          <SidebarSellerMenu />
        </div>
      )}
      <div className="sidebarDesktop">
        <SidebarSellerMenu />
      </div>
    </div>
  );
}
