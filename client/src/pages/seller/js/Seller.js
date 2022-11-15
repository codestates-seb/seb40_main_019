import '../css/Seller.scss';

export default function Seller() {
  return (
    <>
      <div className="sellerSidebar">sidebar</div>
      <div className="sellerDashboard">
        <div className="dashboardBox">
          <h2>판매현황</h2>
          <div className="dashboardContent">
            <div className="dashboardDetail">
              <h3>결제 완료</h3>
              <div>
                <h1>25</h1>
                <h3>건</h3>
              </div>
            </div>
            <div className="gapline"></div>
            <div className="dashboardDetail">
              <h3>상품준비중</h3>
              <div>
                <h1>114</h1>
                <h3>건</h3>
              </div>
            </div>
            <div className="gapline"></div>
            <div className="dashboardDetail">
              <h3>배송 지연</h3>
              <div>
                <h1 className="yellow">0</h1>
                <h3>건</h3>
              </div>
            </div>
            <div className="gapline"></div>
            <div className="dashboardDetail">
              <h3>배송 중</h3>
              <div>
                <h1>21</h1>
                <h3>건</h3>
              </div>
            </div>
            <div className="gapline"></div>
            <div className="dashboardDetail">
              <h3>배송 완료</h3>
              <div>
                <h1>162</h1>
                <h3>건</h3>
              </div>
            </div>
          </div>
        </div>
        <div className="dashboardBox">
          <h2>주문 통계</h2>
        </div>
        <div className="dashboardBottom">
          <div className="dashboardBox">
            <h2>총 판매 금액</h2>
            <div className="dashboardTexts">
              <div className="dashboardText">
                <h3>11월 예상 판매금액</h3>
                <div className="dashboardNum">
                  <h4>1,250,870,000</h4>
                  <h4 className="dashboardNumText">원</h4>
                </div>
              </div>
              <div className="dashboardText">
                <h3>11월 예상 판매금액</h3>
                <div className="dashboardNum">
                  <h4>1,250,870,000</h4>
                  <h4 className="dashboardh4Text">원</h4>
                </div>
              </div>
            </div>
          </div>
          <div className="dashboardBox">
            <h2>등록 상품</h2>
          </div>
          <div className="dashboardBox">
            <h2>리뷰</h2>
          </div>
        </div>
      </div>
    </>
  );
}
