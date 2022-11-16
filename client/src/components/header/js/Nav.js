import '../css/nav.scss';
import textLogo from '../../../assets/img/LUXMEAL.png';

export default function Nav() {
  return (
    <>
      <div className="navbar">
        <nav>
          <div className="navLeft" onScroll={scroll}>
            <button>Shop</button>
            <button>Point</button>
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
            <button>Login</button>
          </div>
        </nav>
      </div>
    </>
  );
}
