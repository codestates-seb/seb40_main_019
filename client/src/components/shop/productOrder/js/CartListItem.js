import '../css/cartListItem.scss';
import QuantityBtn from '../../js/QuantityBtn';

const CartListItem = ({ item }) => {
  return (
    <div className="cartListContainer">
      <div className="checkbox">
        <input type="checkbox"></input>
      </div>
      <img className="titleImg" src={item.img} alt="productImg" />
      <div className="title">{item.productName}</div>
      <div className="quantity">
        <QuantityBtn />
      </div>
      <div className="price">
        <div>{item.price}원</div>
      </div>
      <div className="delete">
        <button>
          <i className="fa-light fa-x"></i>
        </button>
      </div>
    </div>
  );
};

export default CartListItem;
