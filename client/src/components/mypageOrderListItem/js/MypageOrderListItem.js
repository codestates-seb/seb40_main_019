import '../css/mypageOrderListItem.scss';
import { Link, useNavigate } from 'react-router-dom';
import Empty from '../../empty/js/Empty';
import { formatDate, formatMoney } from '../../../util/function/formatData';
import { handleDltReview } from '../../../util/api/review';
import useFetchNotPage from '../../../util/useFetchNotPage';

export default function MypageOrderListItem({ item }) {
  const navigate = useNavigate();
  let status = '상품준비중';
  if (item) {
    if (item.orderStatus === 'SHIPPING') status = '배송중';
    else if (item.orderStatus === 'SHIPPED') status = '배송 완료';
    else if (item.orderStatus === 'CANCLE') status = '주문 취소';
  }
  console.log(item);
  const clickDlt = () => {
    handleDltReview(item.reviewId);
  };

  let [pastData] = '';

  if (item) {
    pastData = useFetchNotPage(`review/read/${item.reviewId}`);
  }

  const clickEdit = () => {
    navigate(`/mypage/reviewedit/${item.reviewId}`, {
      state: { item: pastData },
    });
  };
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
                  <Link to={`/product/detail/${item.productId}`}>
                    <img
                      className="titleImg"
                      src={item.imgUrl}
                      alt="productImg"
                    />
                  </Link>
                  <div className="text">
                    <div className="preparing">{status}</div>
                    <Link to={`/product/detail/${item.productId}`}>
                      <div className="title">{item.productName}</div>
                    </Link>
                  </div>
                  <div className="priceAndCount">
                    <div className="price">
                      {formatMoney(item.totalPrice)}원
                    </div>
                    <div className="orderCount">{item.quantity}개</div>
                  </div>
                  {item.reviewStatus === 'WRITING' ? (
                    <Link to={`/mypage/reviewadd/${item.productId}`}>
                      <div className="reviewAddBtn">
                        <button>리뷰작성</button>
                      </div>
                    </Link>
                  ) : (
                    <>
                      <div className="reviewBtn">
                        <button className="edit" onClick={clickEdit}>
                          <i className="fa-solid fa-pen-to-square"></i>
                        </button>
                        <h3>/</h3>
                        <button className="delete" onClick={clickDlt}>
                          <i className="fa-solid fa-trash-can"></i>
                        </button>
                      </div>
                    </>
                  )}
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
