import '../css/productDetailReadme.scss';
import readme from '../../../assets/img/readme.svg';

export default function ProductDetailReadme() {
  return (
    <div className="readmeContainer">
      <div className="readmeTitle">READ ME</div>
      <div className="readmeTextContainer">
        <img src={readme} alt="readme" />
      </div>
    </div>
  );
}
