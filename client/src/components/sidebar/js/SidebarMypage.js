import { useState } from 'react';
import '../css/sidebar.scss';
import SidebarMypageMenu from './SidebarMypageMenu';
export default function SidebarMypage() {
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
          <SidebarMypageMenu />
        </div>
      )}
      <div className="sidebarDesktop">
        <SidebarMypageMenu />
      </div>
    </div>
  );
}
