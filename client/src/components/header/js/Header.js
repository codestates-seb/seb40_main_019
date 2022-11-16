import '../css/header.scss';
import logo from '../../../assets/img/logo_white.png';

export default function Header() {
  return (
    <>
      <div>
        <div className="header">
          <img src={logo} alt="logo" />
        </div>
      </div>
    </>
  );
}
