export default function Delivery({ item }) {
  return (
    <div className="sellerproduct delivering">
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
    </div>
  );
}
