import '../css/MypageHome.scss';

export default function MypageHome() {
  return (
    <div>
      <div className="orderSummery">
        <ul>
          <li>
            <div className="orderIcon">
              <p>상품준비중</p>
              <i className="fa-solid fa-cart-flatbed"></i>
            </div>
            <h1>0</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>배송중</p>
              <i className="fa-solid fa-truck"></i>
            </div>
            <h1>11</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>배송완료</p>
              <i className="fa-solid fa-gift"></i>
            </div>
            <h1>0</h1>
          </li>
          <li>
            <div className="orderIcon">
              <p>취소/교환/반품</p>
              <i className="fa-solid fa-arrow-rotate-left"></i>
            </div>
            <h1>0</h1>
          </li>
        </ul>
      </div>
    </div>
  );
}
