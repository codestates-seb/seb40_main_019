import '../css/Order.scss';
import { handleDelivery, handleDeliveryAlert } from '../../../util/api/order';
import ModalYesorNo from '../../modal/js/ModalYesorNo';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import Empty from '../../empty/js/Empty';

export default function Order({ item }) {
  let status = '상품준비중';
  if (item.orderStatus === 'SHIPPING') status = '배송중';
  else if (item.orderStatus === 'SHIPPED') status = '배송 완료';
  else if (item.orderStatus === 'CANCLE') status = '주문 취소';

  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const clickDelivery = () => {
    handleDeliveryAlert(setModalText, setModalOn);
  };

  const clickDeliveryOk = () => {
    handleDelivery(item.orderId);
  };

  // console.log(item);
  return (
    <div className="sellerproduct order">
      {/* <div className="createdAt">
        <p>{item.orderDate}</p>
      </div> */}
      <div className="orderContainer">
        <div className="productBtn">
          {status === '상품준비중' ? (
            <button className="delivery" onClick={clickDelivery}>
              발송처리
            </button>
          ) : (
            <h2>{status}</h2>
          )}
        </div>
        <div className="orderInfo">
          <span>{item.receiverName}</span>
          <span>{item.receiverAddress}</span>
        </div>
        <div className="orderBox">
          {item && item.orderProductDtoList.length !== 0 ? (
            item.orderProductDtoList.map((item) => {
              return (
                <div className="sellerOrderContent" key={item.productId}>
                  <Link to={`/product/detail/${item.productId}`}>
                    <img
                      className="titleImg"
                      src={item.imgUrl}
                      alt="productImg"
                    />
                  </Link>
                  <Link to={`/product/detail/${item.productId}`}>
                    <div className="title">{item.productName}</div>
                  </Link>
                  <div className="orderCount">{item.quantity} 개</div>
                </div>
              );
            })
          ) : (
            <Empty text={'현재 삭제된 상품입니다'} />
          )}
        </div>
      </div>
      <ModalYesorNo
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
        api={clickDeliveryOk}
      />
    </div>
  );
}
