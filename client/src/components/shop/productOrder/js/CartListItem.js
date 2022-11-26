import '../css/cartListItem.scss';
import QuantityBtn from '../../js/QuantityBtn';

const CartListItem = ({ item, count, setCount }) => {
  function deleteCartItem() {
    localStorage.setItem(
      'orderItems',
      JSON.stringify([
        {
          receiverAddress: item.orderAdress,
          receiverName: item.orderName,
          // receiverZipcode: item.,
          // receiverPhone: item.,
          productId: item.productsId,
          quantity: item.orderCount,
        },
      ])
    );
    // console.log('orderItem: ', JSON.parse(localStorage.getItem('orderItem')));
  }
  return (
    <div className="cartListContainer">
      <div className="checkbox">
        <input type="checkbox" checked="checked"></input>
      </div>
      <img className="titleImg" src={item.img} alt="productImg" />
      <div className="title">{item.productName}</div>
      <div className="quantity">
        <QuantityBtn count={count} setCount={setCount} />
      </div>
      <div className="price">
        <div>{item.price * count}원</div>
      </div>
      <div className="delete">
        <button onClick={deleteCartItem}>
          <i className="fa-light fa-x"></i>
        </button>
      </div>
    </div>
  );
};

export default CartListItem;
