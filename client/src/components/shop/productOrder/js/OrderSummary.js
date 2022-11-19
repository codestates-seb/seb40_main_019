import '../css/orderSummary.scss';

export default function OrderSummary() {
  return (
    <>
      <div className="orderSummaryContainer">
        <div>
          <h1>Order Summary</h1>
        </div>
        <div className="orderAddressContainer">
          <div>배송지</div>
          <div>주소+주소찾기버튼</div>
        </div>
        <div>
          <div>결제</div>
          <div>
            <div>총포인트</div>
            <input type="text"></input>
          </div>
          <div>
            <div>포인트사용</div>
            <div>20,000원</div>
          </div>
          <div>
            <div>소계</div>
            <div>23,000원</div>
          </div>
          <div>
            <div>배송비</div>
            <div>3000원</div>
          </div>
        </div>
        <div>
          <div>
            <div>총금액</div>
            <div>23,000원</div>
          </div>
        </div>
      </div>
    </>
  );
}
