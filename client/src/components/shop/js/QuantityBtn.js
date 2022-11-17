import '../css/quantityBtn.scss';
import { useState } from 'react';

//수량 + - 버튼
export default function QuantityBtn() {
  const [count, setCount] = useState(0);

  return (
    <div className="quantityContainer">
      <span>Quantity</span>
      <div className="controlBtn">
        <button onClick={() => setCount(count - 1)}>-</button>
        <span>{count}</span>
        <button onClick={() => setCount(count + 1)}>+</button>
      </div>
    </div>
  );
}
