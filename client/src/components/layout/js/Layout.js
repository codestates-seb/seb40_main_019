import '../css/layout.scss';
import { Outlet } from 'react-router-dom';
import Header from '../../header/js/Header';
import Main from '../../main/js/Main';
import Footer from '../../footer/js/Footer';
import Nav from '../../header/js/Nav';

export default function Layout() {
  return (
    <>
      <div className="appLayout">
        <Header />
        <Nav />
        {/* <div /> */}
        <Main>
          <Outlet />
        </Main>
        <Footer />
      </div>
    </>
  );
}
