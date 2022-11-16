import '../css/nav.scss';
import textLogo from '../../../assets/img/LUXMEAL.png';

export default function Nav() {
  return (
    <>
      <div className="navbar">
        <nav>
          <div className="maxNav">
            <div className="navLeft" onScroll={scroll}>
              <button className="margin">Shop</button>
              <button className="margin">Point</button>
              <button>Mypage</button>
            </div>
            <div className="textLogo">
              <img src={textLogo} alt="textLogo" />
            </div>
            <div className="navRight">
              <button>
                <i className="fa-solid fa-cart-shopping"></i>
                Cart
              </button>
              <button className="margin">Login</button>
            </div>
          </div>
        </nav>
      </div>
    </>
  );
}
