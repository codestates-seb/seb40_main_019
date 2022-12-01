import '../css/mypageOrderListItem.scss';
import { Link } from 'react-router-dom';
import Empty from '../../empty/js/Empty';
import { formatDate } from '../../../util/function/formatData';

export default function MypageOrderListItem({ item }) {
  let status = '상품준비중';
  if (item) {
    if (item.orderStatus === 'SHIPPING') status = '배송중';
    else if (item.orderStatus === 'SHIPPED') status = '배송 완료';
    else if (item.orderStatus === 'CANCLE') status = '주문 취소';
  }

  return (
    <>
      {item ? (
        <div className="mypageOrderContainer">
          <div className="orderDate">
            <p>{formatDate(item.orderDate)}</p>
          </div>
          <div className="orderContainer">
            {item.orderProductDtoList.map((item) => {
              return (
                <div className="orderContent" key={item.productId}>
                  <img
                    className="titleImg"
                    src={item.imgUrl}
                    alt="productImg"
                  />
                  <div className="text">
                    <div className="preparing">{status}</div>
                    <div className="title">{item.productName}</div>
                  </div>
                  <div className="priceAndCount">
                    <div className="price">{item.totalPrice}원</div>
                    <div className="orderCount">{item.quantity}개</div>
                  </div>
                  <Link to={`/mypage/reviewadd/${item.productId}`}>
                    <div className="reviewBtn">
                      <button>리뷰작성</button>
                    </div>
                  </Link>
                </div>
              );
            })}
          </div>
        </div>
      ) : (
        <Empty text={'최근 주문한 목록이 없습니다'} />
      )}
    </>
  );
}
