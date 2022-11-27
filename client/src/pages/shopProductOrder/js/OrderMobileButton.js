import '../css/orderMobileButton.scss';

export default function OrderMobileButton({ totalPrice }) {
  return (
    <div className="mobileButtonContainer">
      <div className="buttonBox">
        <button className="priceBtn">{totalPrice + 3000}원</button>
        <button>포인트 결제</button>
      </div>
    </div>
  );
}
