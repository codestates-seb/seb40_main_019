import '../css/nav.scss';
import textLogo from '../../../assets/img/LUXMEAL.svg';
import textLogoYellow from '../../../assets/img/LUXMEALy.svg';
import { Link, useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { userLogout } from '../../../util/api/loginForm';

export default function Nav() {
  const location = useLocation();
  // const user = useSelector((state) => state.user);
  const loginData = useSelector((state) => state.login);
  return (
    <>
      <div className="navbar">
        <nav>
          <div className="maxNav">
            <div className="navLeft">
              <Link to="/products">
                <button className="margin">Shop</button>
              </Link>
              <Link to="/seller">
                <button className="margin">Seller</button>
              </Link>
              <Link to="/mypage/user">
                <button>Mypage</button>
              </Link>
            </div>
            <Link to="/">
              <div className="textLogo">
                <img
                  src={
                    location.pathname === '/seller' ? textLogoYellow : textLogo
                  }
                  alt="textLogo"
                />
              </div>
            </Link>
            <div className="navRight">
              <Link to="/product/order">
                <button>
                  <i className="fa-solid fa-cart-shopping"></i>
                  Cart
                </button>
              </Link>
              {loginData.isLogin ? (
                <button className="margin" onClick={userLogout}>
                  Logout
                </button>
              ) : (
                <Link to="/login">
                  <button className="margin">Login</button>
                </Link>
              )}
            </div>
          </div>
        </nav>
      </div>
    </>
  );
}
