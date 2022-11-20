import '../css/Order.scss';

export default function Order({ item }) {
  return (
    <div className="sellerproduct">
      <div className="createdAt">
        <p>{item.orderDate}</p>
      </div>
      <img className="titleImg" src={item.img} alt="productImg" />
      <div className="title">{item.productName}</div>
      <div className="orderInfo">
        <span>{item.orderName}</span>
        <span>{item.orderAdress}</span>
      </div>
      <div className="orderCount">{item.orderCount} 개</div>
      <div className="productBtn">
        <button className="delivery">
          <i className="fa-solid fa-truck"></i>
          발송처리
        </button>
      </div>
    </div>
  );
}
