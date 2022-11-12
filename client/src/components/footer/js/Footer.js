import '../css/footer.scss';
import footer from '../../../assets/img/footer.png';

export default function Footer() {
  return (
    <>
      <footer>
        <img src={footer} alt="footer" />
        <div className="footer"></div>
      </footer>
    </>
  );
}
