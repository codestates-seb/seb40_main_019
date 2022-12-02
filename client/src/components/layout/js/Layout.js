import '../css/layout.scss';
import { Outlet, useLocation } from 'react-router-dom';
import Header from '../../header/js/Header';
import Main from '../../main/js/Main';
import Footer from '../../footer/js/Footer';
import FooterYellow from '../../footer/js/FooterYellow';
import Nav from '../../header/js/Nav';

export default function Layout() {
  const location = useLocation();

  return (
    <>
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
