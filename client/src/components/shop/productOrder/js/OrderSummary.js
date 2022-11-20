import '../css/orderSummary.scss';

export default function OrderSummary() {
  return (
    <>
      <div className="orderSummaryContainer">
        <div className="orderSticky">
          <div className="orderSummaryBox">
            <h1>Order Summary</h1>
          </div>

          {/* 배송지 정보 */}
          <div className="orderAddressContainer">
            <div className="AddressPadding">배송지</div>
            <div className="AddressFlexBox">
              <div>주소입력란</div>
              <button>주소찾기</button>
            </div>
          </div>

          {/* 결제 정보 */}
          <div className="orderInfoContainer">
            <div className="orderTextAlign">결제</div>
            <div className="totalPointBox">
              <div>총포인트</div>
              <input type="text"></input>
            </div>
            <div className="pointBox">
              <div>포인트사용</div>
              <div>20,000원</div>
            </div>
            <div className="productPriceBox">
              <div>소계</div>
              <div>23,000원</div>
            </div>
            <div className="deliveryFeeBox">
              <div>배송비</div>
              <div>3000원</div>
            </div>
          </div>

          {/* 총 금액 정보 */}
          <div>
            <div className="totalPriceBox">
              <div>총금액</div>
              <div>23,000원</div>
            </div>
          </div>

          {/* order버튼 -> 결제창 띄우가 */}
          <div className="orderBtnBox">
            <button>ORDER</button>
          </div>
        </div>
      </div>
    </>
  );
}
