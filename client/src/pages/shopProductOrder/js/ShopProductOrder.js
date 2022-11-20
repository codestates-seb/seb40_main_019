import '../css/shopProductOrder.scss';
import CartList from '../../../components/shop/productOrder/js/CartList';
import OrderSummary from '../../../components/shop/productOrder/js/OrderSummary';

export default function ShopProductOrder() {
  return (
    <div className="productOrderContainer">
      <div style={{ width: '70%' }}>
        <CartList />
      </div>
      <div style={{ width: '30%' }}>
        <OrderSummary />
      </div>
    </div>
  );
}
