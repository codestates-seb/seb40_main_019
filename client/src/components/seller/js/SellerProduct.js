import '../css/SellerProduct.scss';
export default function SellerProduct({ item }) {
  return (
    <div className="sellerproduct">
      <div className="createdAt">
        <p>{item.createdAt}</p>
      </div>
      <img className="titleImg" src={item.img} alt="productImg" />
      <div className="title">{item.productName}</div>
      <div className="price">{item.price} 원</div>
      <div className="productBtn">
        <button className="edit">
          <i className="fa-solid fa-pen-to-square"></i>
        </button>
        <h3>/</h3>
        <button className="delete">
          <i className="fa-solid fa-trash-can"></i>
        </button>
      </div>
    </div>
  );
}
