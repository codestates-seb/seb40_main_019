import DashboardBox from '../../../components/seller/js/DashboardBox';
import '../css/Seller.scss';
import Graph from '../../../components/seller/js/Graph';

export default function Seller() {
  return (
    <>
      {/* <div className="sellerSidebar">sidebar</div> */}
      <div className="sellerDashboard">
        <div className="dashboardTop">
          <div className="dashboardHalf gap">
            <div className="dashboardBox">
              <h2>주문현황</h2>
              <DashboardBox title={'결제 대기'} num={'25'} unit={'건'} />
              <DashboardBox title={'신규 주문'} num={'114'} unit={'건'} />
              <DashboardBox title={'오늘 출발'} num={'114'} unit={'건'} />
              <DashboardBox title={'예약 구매'} num={'0'} unit={'건'} />
            </div>
          </div>
          <div className="dashboardHalf">
            <div className="dashboardBox">
              <h2>배송현황</h2>
              <DashboardBox title={'상품 준비중'} num={'114'} unit={'건'} />
              <DashboardBox title={'배송중'} num={'21'} unit={'건'} />
              <DashboardBox
                title={'배송 지연'}
                num={'0'}
                unit={'건'}
                yellow={'yellow'}
              />
              <DashboardBox title={'배송 완료'} num={'162'} unit={'건'} />
            </div>
          </div>
        </div>
        <div className="dashboardBox">
          <h2>주문 통계</h2>
          <div className="graph">
            <Graph />
          </div>
        </div>
        <div className="dashboardBottom">
          <div className="dashboardHalf gap">
            <div className="dashboardBox">
              <h2>총 판매 금액</h2>
              <DashboardBox
                title={'11월 예상 판매금액'}
                num={'1,250,870,000'}
                unit={'원'}
              />
              <DashboardBox
                title={'연간 판매금액'}
                num={'15,250,870,000'}
                unit={'원'}
              />
            </div>
          </div>
          <div className="dashboardHalf">
            <div className="dashboardBox">
              <h2>리뷰</h2>
              <DashboardBox title={'11월 리뷰 갯수'} num={'1442'} unit={'개'} />
              <DashboardBox
                title={'평점 낮은 리뷰 갯수'}
                num={'12'}
                unit={'개'}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
