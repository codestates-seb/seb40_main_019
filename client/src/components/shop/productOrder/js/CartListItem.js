import ModalYesorNo from '../../../modal/js/ModalYesorNo';
import '../css/cartListItem.scss';
// import QuantityBtn from '../../js/QuantityBtn';
import CartQuantityBtn from './CartQuantityBtn';

const CartListItem = ({
  item,
  decreaseQuantity,
  increaseQuantity,
  deleteItem,
  checkBuyItem,
  setModalOn,
  modalOn,
  modalText,
  api,
}) => {
  return (
    <div className="cartListContainer">
      <div className="checkbox">
        <input
          onChange={() => checkBuyItem(item)}
          type="checkbox"
          checked={item.check}
        ></input>
      </div>
      <img className="titleImg" src={item.titleImg} alt="productImg" />
      <div className="title">{item.productName}</div>
      <div className="quantity">
        <CartQuantityBtn
          item={item}
          decreaseQuantity={decreaseQuantity}
          increaseQuantity={increaseQuantity}
        />
      </div>
      <div className="price">
        <div>{item.price * item.count}원</div>
      </div>
      <div className="delete">
        <button onClick={() => deleteItem(item)}>
          <i className="fa-light fa-x"></i>
        </button>
      </div>
      <ModalYesorNo
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
        api={() => {
          api(item);
        }}
      />
    </div>
  );
};

export default CartListItem;
