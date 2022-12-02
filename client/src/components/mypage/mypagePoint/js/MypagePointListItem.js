import { formatDate, formatMoney } from '../../../../util/function/formatData';
import '../css/mypagePointListItem.scss';

export default function MypagePointListItem({ point }) {
  return (
    <div className="pointContainer">
      <div className="createdAt">
        <p>{formatDate(point.createdAt)}</p>
      </div>
      {point.cash >= 0 ? (
        <>
          <div className="plusPoint">+{formatMoney(point.cash)}</div>
          <div className="minusPoint">0</div>
        </>
      ) : (
        <>
          <div className="plusPoint">0</div>
          <div className="minusPoint">-{formatMoney(point.cash)}</div>
        </>
      )}
      <div className="remainingPoint">{formatMoney(point.restCash)}</div>
    </div>
  );
}
