import '../css/mypagePointListItem.scss';

export default function MypagePointListItem({ point }) {
  return (
    <div className="pointContainer">
      <div className="createdAt">
        <p>{point.createdAt}</p>
      </div>
      {point.cash >= 0 ? (
        <>
          <div className="plusPoint">+{point.cash}원</div>
          <div className="minusPoint">0원</div>
        </>
      ) : (
        <>
          <div className="plusPoint">0원</div>
          <div className="minusPoint">-{point.cash}원</div>
        </>
      )}
      <div className="remainingPoint">{point.restCash} 원</div>
    </div>
  );
}
