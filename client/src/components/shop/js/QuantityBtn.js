import '../css/quantityBtn.scss';

//수량 + - 버튼
export default function QuantityBtn({ count, setCount }) {
  return (
    <div className="quantityContainer">
      {/* <span>Quantity</span> */}
      <div className="controlBtn">
        <button
          onClick={() => {
            if (count > 1) setCount(count - 1);
          }}
        >
          <i className="fa-solid fa-minus"></i>
        </button>
        <span>{count}</span>
        <button
          onClick={() => {
            if (count < 100) setCount(count + 1);
          }}
        >
          <i className="fa-solid fa-plus"></i>
        </button>
      </div>
    </div>
  );
}
