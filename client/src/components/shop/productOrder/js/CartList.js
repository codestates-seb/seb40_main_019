import '../css/cartList.scss';
import QuantityBtn from '../../js/QuantityBtn';

export default function CartList() {
  return (
    <div>
      <div className="cart">
        <h1>장바구니</h1>
      </div>
      <div>
        {/* Table-header */}
        <table className="cartListTable">
          <thead className="cartTitle">
            <tr>
              <th>
                <input type="checkbox"></input>
              </th>
              <th>상품</th>
              <th>수량</th>
              <th>가격</th>
              <th>X</th>
            </tr>
          </thead>
          {/* Table-body */}
          <tbody className="cartItemContent">
            <tr>
              <th>
                <input type="checkbox"></input>
              </th>
              <th>
                <div className="productImgName">
                  <div>이미지</div>
                  <div>제품명</div>
                </div>
              </th>
              <th>
                <QuantityBtn />
              </th>
              <th>가격</th>
              <th>
                <button>
                  <i className="fa-light fa-x"></i>
                </button>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}
