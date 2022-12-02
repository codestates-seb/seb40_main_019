import '../css/layout.scss';
import { Outlet, useLocation } from 'react-router-dom';
import Header from '../../header/js/Header';
import Main from '../../main/js/Main';
import Footer from '../../footer/js/Footer';
import FooterYellow from '../../footer/js/FooterYellow';
import Nav from '../../header/js/Nav';
// import mainPanding from '../../../assets/video/mainPanding1080p.webm';

export default function Layout() {
  const location = useLocation();

  return (
    <>
      {/* <div className="pandingPosition">
        <div className="pandingBox">
          <video className="mainPanding" autoPlay={true} muted loop={false}>
            <source src={mainPanding} type="video/webm" />
          </video>
        </div>
      </div> */}
      <div className="appLayout">
        <Header />
        <Nav />
        {/* <div /> */}
        <Main>
          <Outlet />
        </Main>
        {location.pathname.split('/')[1] === 'seller' ? (
          <FooterYellow />
        ) : (
          <Footer />
        )}
      </div>
    </>
  );
}
