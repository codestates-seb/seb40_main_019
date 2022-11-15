import '../css/MainImg.scss';
import PropTypes from 'prop-types';

export default function MainImg({ src }) {
  MainImg.propsTypes = {
    src: PropTypes.string.isRequired,
  };

  // const detailData = [
  //   '릴리즈 키친 카운트다운 크리스마스 어드벤트 캘린더',
  //   '하림 펫푸드 더리얼 그레인프리 도그밸런스 포크릭 소이빈 연어 200g',
  //   '와이즈 앤 원더풀 시니어레시피 스패셜 크런치어덜트 네츄럴초이스 닭고기 1kg',
  // ];

  return (
    <div className="mainImgWrap">
      <div className="mainImg">
        <img src={src} alt="img" />
      </div>
      {/* <p>{detailData[idx]}</p> */}
    </div>
  );
}
