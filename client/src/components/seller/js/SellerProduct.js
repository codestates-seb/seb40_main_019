import { useNavigate } from 'react-router-dom';
import '../css/SellerProduct.scss';
import { handleDelete, handleDeleteAlert } from '../../../util/api/product';
import { formatDate } from '../../../util/function/formatData';
import ModalYesorNo from '../../modal/js/ModalYesorNo';
import { useState } from 'react';
export default function SellerProduct({ item }) {
  const navigate = useNavigate();
  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const clickEdit = () => {
    navigate(`/seller/edit/${item.productId}`, {
      state: { item },
    });
  };

  const clickDelete = () => {
    handleDeleteAlert(setModalText, setModalOn);
  };

  const clickDeleteOk = () => {
    handleDelete(item);
  };

  return (
    <div className="sellerproduct">
      <div className="createdAt">
        <p>{formatDate(item.createdAt)}</p>
      </div>
      <img className="titleImg" src={item.titleImg} alt="productImg" />
      <div className="title">{item.productName}</div>
      <div className="price">{item.price} 원</div>
      <div className="productBtn">
        <button className="edit" onClick={clickEdit}>
          <i className="fa-solid fa-pen-to-square"></i>
        </button>
        <h3>/</h3>
        <button className="delete" onClick={clickDelete}>
          <i className="fa-solid fa-trash-can"></i>
        </button>
      </div>
      <ModalYesorNo
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
        api={clickDeleteOk}
      />
    </div>
  );
}
