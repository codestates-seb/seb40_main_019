import '../css/shopProductOrder.scss';
import CartList from '../../../components/shop/productOrder/js/CartList';
import OrderSummary from '../../../components/shop/productOrder/js/OrderSummary';

export default function ShopProductOrder() {
  return (
    <div>
      <CartList />
      <OrderSummary />
    </div>
  );
}
