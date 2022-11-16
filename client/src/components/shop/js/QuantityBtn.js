import '../css/quantityBtn.scss';
//수량 + - 버튼
export default function QuantityBtn() {
  console.log();
  return (
    <div className="quantityContainer">
      <span>Quantity</span>
      <div className="controlBtn">
        <button>-</button>
        <span>1</span>
        <button>+</button>
      </div>
    </div>
  );
}
