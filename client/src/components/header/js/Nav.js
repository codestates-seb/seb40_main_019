import '../css/nav.scss';
import textLogo from '../../../assets/img/LUXMEAL.png';
import { Link } from 'react-router-dom';

export default function Nav() {
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
                <img src={textLogo} alt="textLogo" />
              </div>
            </Link>
            <div className="navRight">
              <Link to="/product/order">
                <button>
                  <i className="fa-solid fa-cart-shopping"></i>
                  Cart
                </button>
              </Link>
              <Link to="/login">
                <button className="margin">Login</button>
              </Link>
            </div>
          </div>
        </nav>
      </div>
    </>
  );
}
