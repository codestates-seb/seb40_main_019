import '../css/nav.scss';
import textLogo from '../../../assets/img/LUXMEAL.png';
import { useEffect, useState } from 'react';

export default function Nav() {
  const [fixNav, setFixNav] = useState({});
  useEffect(() => {
    window.addEventListener('scroll', handleScroll, { capture: true }); // 스크롤 이벤트 등록
    return () => {
      window.removeEventListener('scroll', handleScroll); // 스크롤 이벤트 등록 제거(성능저하방지)
    };
  }, []);

  const handleScroll = () => {
    if (scrollY > 120) {
      setFixNav({
        position: 'fixed',
        top: '0',
        left: '0',
        right: '0',
      });
    } else {
      setFixNav({});
    }
  };

  return (
    <>
      <div style={fixNav} className="navbar">
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
