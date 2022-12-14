import '../css/mypagePointPage.scss';
import MypagePoint from '../../../../components/mypage/mypagePoint/js/MypagePoint';
import MypagePointList from '../../../../components/mypage/mypagePoint/js/MypagePointList';

export default function MypagePointPage() {
  return (
    <>
      <div>
        <div className="mypagePointContainer">
          <MypagePoint />
        </div>
        <MypagePointList />
      </div>
    </>
  );
}
