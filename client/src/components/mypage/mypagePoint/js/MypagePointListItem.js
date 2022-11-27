import '../css/mypagePointListItem.scss';

export default function MypagePointListItem({ point }) {
  return (
    <div className="pointContainer">
      <div className="createdAt">
        <p>{point.createdAt}</p>
      </div>
      <div className="plusPoint">+{point.price}원</div>
      <div className="minusPoint">-{point.newRestCash} 원</div>
      <div className="remainingPoint">{point.restCash} 원</div>
    </div>
  );
}
