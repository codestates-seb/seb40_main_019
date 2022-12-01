import '../css/header.scss';
import logo from '../../../assets/img/logo_white.png';
import { useLocation, Link } from 'react-router-dom';

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
          <Link to={'/'}>
            <img src={logo} alt="logo" />
          </Link>
        </div>
      </div>
    </>
  );
}
