import '../css/cartQuantityBtn.scss';

//수량 + - 버튼
export default function QuantityBtn({
  item,
  decreaseQuantity,
  increaseQuantity,
}) {
  return (
    <div className="quantityContainer">
      {/* <span>Quantity</span> */}
      <div className="controlBtn">
        <button onClick={() => decreaseQuantity(item)}>
          <i className="fa-solid fa-minus"></i>
        </button>
        <span>{item.count}</span>
        <button onClick={() => increaseQuantity(item)}>
          <i className="fa-solid fa-plus"></i>
        </button>
      </div>
    </div>
  );
}
