import '../css/nav.scss';
import textLogo from '../../../assets/img/LUXMEAL.svg';
import textLogoYellow from '../../../assets/img/LUXMEALy.svg';
import { Link, useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { userLogout } from '../../../util/api/loginForm';
import { useState } from 'react';
import ModalOk from '../../modal/js/ModalOk';
import ModalMove from '../../modal/js/ModalMove';

export default function Nav() {
  const location = useLocation();
  const user = useSelector((state) => state.user);
  const loginData = useSelector((state) => state.login);
  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');
  const [modalMoveOn, setModalMoveOn] = useState(false);
  const [modalMoveText, setModalMoveText] = useState('');

  //마이페이지 구매자 로그인시 페이지 사용가능
  const handleMypage = (e) => {
    if (user.userRole === '') {
      setModalMoveOn(true);
      setModalMoveText(`구매자로 로그인 시 이용 가능합니다\n이동하시겠습니까?`);
      e.preventDefault();
    } else if (
      user.userRole === 'ROLE_ADMIN_TEST' ||
      user.userRole === 'ROLE_ADMIN'
    ) {
      setModalOn(true);
      setModalText('구매자로 로그인 시 이용 가능합니다');
      e.preventDefault();
    }
  };

  //seller페이지 판매자 로그인시 페이지 사용가능
  const handleSeller = (e) => {
    if (user.userRole === '') {
      setModalMoveOn(true);
      setModalMoveText(
        `판매자게스트로 로그인 시 이용 가능합니다\n체험하러 이동하시겠습니까?`
      );
      e.preventDefault();
    } else if (
      user.userRole === 'ROLE_USER' ||
      user.userRole === 'ROLE_USER_TEST'
    ) {
      setModalOn(true);
      setModalText('판매자로 로그인 시 이용 가능합니다');
      e.preventDefault();
    }
  };

  return (
    <>
      <div className="navbar">
        <nav>
          <div className="maxNav">
            <div className="navLeft">
              <Link to="/products">
                <button className="margin">Shop</button>
              </Link>
              <Link to="/seller" onClick={handleSeller}>
                <button className="margin">Seller</button>
              </Link>
            </div>
            <Link to="/">
              <div className="textLogo">
                <img
                  src={
                    location.pathname.split('/')[1] === 'seller'
                      ? textLogoYellow
                      : textLogo
                  }
                  alt="textLogo"
                />
              </div>
            </Link>
            <div className="navRight">
              <Link to="/product/order">
                <button className="cartMargin">
                  <i className="fa-solid fa-cart-shopping"></i>
                  Cart
                </button>
              </Link>
              <Link to="/mypage" onClick={handleMypage}>
                <button className="mypageMargin">Mypage</button>
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
      <ModalOk
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
      />
      <ModalMove
        setModalOn={setModalMoveOn}
        modalOn={modalMoveOn}
        modalText={modalMoveText}
        link={'/login'}
      />
    </>
  );
}
