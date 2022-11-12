import '../css/header.scss';
import Nav from './Nav';
import logo from '../../../assets/img/logo_white.png';

export default function Header() {
  return (
    <>
      <div>
        <div className="header">
          <img src={logo} alt="logo" />
        </div>
        <Nav />
      </div>
    </>
  );
}
