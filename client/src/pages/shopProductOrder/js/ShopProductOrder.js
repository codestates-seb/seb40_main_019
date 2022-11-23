import '../css/shopProductOrder.scss';
import CartList from '../../../components/shop/productOrder/js/CartList';
import OrderSummary from '../../../components/shop/productOrder/js/OrderSummary';
import OrderMobileButton from './OrderMobileButton';

export default function ShopProductOrder() {
  return (
    <div className="productOrderContainer">
      <div className="orderCartListBox">
        <CartList />
      </div>
      <div className="orderSummaryBox">
        <OrderSummary />
      </div>
      <div className="MobileBtnContainer">
        <OrderMobileButton />
      </div>
    </div>
  );
}
