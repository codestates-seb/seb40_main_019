import '../css/Modal.scss';
import { Transition } from 'react-transition-group';
import { useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';

export default function ModalMove({ setModalOn, modalOn, modalText, link }) {
  const navigate = useNavigate();
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
          modalOn ? 'modalsBackground modalOpen' : 'modalsBackground modalClose'
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
                navigate(link);
              }}
            >
              이동
            </button>
          </div>
        </div>
      </div>
    </Transition>
  );
}
