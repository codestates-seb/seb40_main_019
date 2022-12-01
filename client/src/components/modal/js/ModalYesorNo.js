import '../css/Modal.scss';
import { Transition } from 'react-transition-group';
import { useEffect, useRef } from 'react';

export default function ModalYesorNo({ setModalOn, modalOn, modalText, api }) {
  const box = useRef();
  const handleColseModal = (e) => {
    if (modalOn && (!box.current || !box.current.contains(e.target)))
      setModalOn(false);
  };
  useEffect(() => {
    if (modalOn) {
      window.addEventListener('click', handleColseModal);
      return () => {
        window.removeEventListener('click', handleColseModal);
      };
    }
  }, []);
  return (
    <Transition
      in={modalOn}
      timeout={{ enter: 0, exit: 200 }}
      appear
      unmountOnExit
    >
      <div
        className={
          modalOn ? 'modalBackground modalOpen' : 'modalBackground modalClose'
        }
        ref={box}
      >
        <div className="modalBox">
          <div className="modalText">
            <h3>{modalText}</h3>
          </div>
          <div className="modalYesorNoBtn">
            <button
              className="close"
              onClick={() => {
                setModalOn(false);
              }}
            >
              취소
            </button>
            <button
              onClick={() => {
                setModalOn(false);
                api();
              }}
            >
              확인
            </button>
          </div>
        </div>
      </div>
    </Transition>
  );
}
