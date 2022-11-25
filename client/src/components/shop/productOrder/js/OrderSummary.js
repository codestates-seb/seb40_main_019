import '../css/orderSummary.scss';
import { handleOrder } from '../../../../util/api/order';

export default function OrderSummary() {
  //주문내역 생성 테스트
  const data = {
    receiverAddress: '십정동',
    receiverName: '준오',
    receiverZipcode: 2133,
    receiverPhone: '010-2222-333',
    productId: 2,
    quantity: 3,
  };
  const clickOrder = () => {
    handleOrder(data);
  };
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
              <div>100,000원</div>
            </div>
            <div className="productPriceBox">
              <div>제품 금액</div>
              <div>32,000원</div>
            </div>
            <div className="deliveryFeeBox">
              <div>배송비</div>
              <div>3,000원</div>
            </div>
          </div>

          {/* 총 금액 정보 */}
          <div>
            <div className="totalPriceBox">
              <div>총금액</div>
              <div>35,000원</div>
            </div>
          </div>

          {/* order버튼 -> 결제창 띄우가 */}
          <div className="orderBtnBox">
            <button onClick={clickOrder}>포인트 결제</button>
          </div>
        </div>
      </div>
    </>
  );
}
