import '../css/mypageOrderListItem.scss';

export default function MypageOrderListItem({ item }) {
  return (
    <div className="mypageOrderContainer">
      <div className="orderDate">
        <p>{item.orderDate}</p>
      </div>
      {/* <div className="productTitle"> */}
      <img className="titleImg" src={item.img} alt="productImg" />
      <div className="text">
        <div className="preparing">상품준비중</div>
        <div className="title">{item.productName}</div>
      </div>
      {/* </div> */}

      <div className="priceAndCount">
        <div className="price">{item.price}원</div>
        <div className="orderCount">{item.orderCount}개</div>
      </div>
      <div className="reviewBtn">
        <button>리뷰작성</button>
      </div>
    </div>
  );
}
