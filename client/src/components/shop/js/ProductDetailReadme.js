import '../css/productDetailReadme.scss';
import readme from '../../../assets/img/readme.svg';

export default function ProductDetailReadme() {
  return (
    <div className="readmeContainer">
      <div className="readmeTitle">READ ME</div>
      <div className="readmeTextContainer">
        {/* <ul>
            <li>결제수단안내</li>
            <li>배송안내</li>
            <li>교환 및 반품안내</li>
          </ul> */}
        <img src={readme} alt="readme" />
      </div>
    </div>
  );
}
