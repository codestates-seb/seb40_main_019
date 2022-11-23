import '../css/header.scss';
import logo from '../../../assets/img/logo_white.png';
import { useLocation } from 'react-router-dom';

export default function Header() {
  const location = useLocation();
  return (
    <>
      <div>
        <div
          className={
            location.pathname === '/seller' ? 'header sellerHeader' : 'header'
          }
        >
          <img src={logo} alt="logo" />
        </div>
      </div>
    </>
  );
}
