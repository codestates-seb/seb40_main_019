import '../css/Modal.scss';
import { Transition } from 'react-transition-group';
import { useEffect, useRef } from 'react';

export default function ModalOk({ setClick, modalOn }) {
  const box = useRef();
  const handleColseModal = (e) => {
    if (modalOn && (!box.current || !box.current.contains(e.target)))
      setClick(false);
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
            <h3>상품명이 5~30자여야 합니다</h3>
          </div>
          <button onClick={() => setClick(false)}>확인</button>
        </div>
      </div>
    </Transition>
  );
}
