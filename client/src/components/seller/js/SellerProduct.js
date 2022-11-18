import '../css/SellerProduct.scss';
export default function SellerProduct({ item }) {
  return (
    <div>
      <div className="checkbox">checkbox</div>
      <div className="createdAt">{item.createdAt}</div>
      <div className="titleImg">{item.img}</div>
      <div className="title">{item.productName}</div>
      <div className="price">{item.price}</div>
      <div className="edit">수정</div>
    </div>
  );
}
