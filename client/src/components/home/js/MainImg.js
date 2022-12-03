import '../css/MainImg.scss';
import PropTypes from 'prop-types';

export default function MainImg({ src }) {
  MainImg.propsTypes = {
    src: PropTypes.string.isRequired,
  };

  return (
    <div className="mainImgWrap">
      <div className="mainImg">
        <img src={src} alt="img" />
      </div>
    </div>
  );
}
