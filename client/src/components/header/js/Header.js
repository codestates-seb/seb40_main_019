import '../css/header.scss';
import logo from '../../../assets/img/logo_white.png';
import { useLocation, useNavigate } from 'react-router-dom';

export default function Header() {
  const location = useLocation();
  const Navigate = useNavigate();
  const gotoMain = () => {
    Navigate('/');
  };

  return (
    <>
      <div>
        <div
          className={
            location.pathname.split('/')[1] === 'seller'
              ? 'header sellerHeader'
              : 'header'
          }
        >
          <button onClick={gotoMain}>
            <img src={logo} alt="logo" />
          </button>
        </div>
      </div>
    </>
  );
}
//img 네비게이션 오류나서 button만들어서 Navigation 추가함
