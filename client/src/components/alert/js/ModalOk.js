import '../css/Modal.scss';

export default function ModalOk({ setClick }) {
  return (
    <div className="modalBackground">
      <div className="modalBox">
        <div className="modalText">
          <h3>상품명이 5~30자여야 합니다</h3>
        </div>
        <button onClick={() => setClick(false)}>확인</button>
      </div>
    </div>
  );
}
