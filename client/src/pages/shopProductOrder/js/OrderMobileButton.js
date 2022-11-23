import '../css/orderMobileButton.scss';

export default function OrderMobileButton() {
  return (
    <div className="mobileButtonContainer">
      <div className="buttonBox">
        <button className="priceBtn">원</button>
        <button>포인트 결제</button>
      </div>
    </div>
  );
}
