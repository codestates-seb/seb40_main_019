import '../css/layout.scss';
import { Outlet } from 'react-router-dom';
import Header from '../../header/js/Header';
import Main from '../../main/js/Main';
import Footer from '../../footer/js/Footer';

export default function Layout() {
  return (
    <>
      <div className="appLayout">
        <Header />
        <div />
        <Main>
          <Outlet />
        </Main>
        <Footer />
      </div>
    </>
  );
}
