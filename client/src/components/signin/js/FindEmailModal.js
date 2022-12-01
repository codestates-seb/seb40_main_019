import { useState } from 'react';
import '../css/findEmailModal.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import { findEmailController } from '../../../util/api/userAccount';
import ModalOk from '../../modal/js/ModalOk';
export default function FindEmailModal({ setEmailModal }) {
  const [inputPhone, setInputPhone] = useState('');

  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const changeInputPhone = (e) => {
    setInputPhone(e.target.value);
  };
  const closeModal = () => {
    setEmailModal(false);
  };
  const findEmail = () => {
    findEmailController(inputPhone, setModalOn, setModalText);
    // window.alert('아이디 찾기');
  };
  return (
    <>
      <div className="modalBackground">
        <div className="modal">
          <div className="logoImg" />
          <h2>아이디 찾기</h2>
          <p>휴대폰 번호를 입력한 기록이 있어야 사용이 가능합니다.</p>
          <p>
            <span>휴대폰 번호</span>를 입력하세요.
          </p>
          <div className="modalInner">
            <input
              name="phone"
              id="phone"
              type="text"
              placeholder="휴대폰 번호"
              value={inputPhone}
              onChange={changeInputPhone}
            />
            <FormButtonBlue formSubmit={findEmail} btnContent="아이디 찾기" />
            {/* <FormButtonBlue formSubmit={deleteUser} btnContent="탈퇴하기" /> */}
          </div>
          <FontAwesomeIcon
            className="closeBtn"
            icon={faXmark}
            onClick={closeModal}
          />
        </div>
      </div>
      <ModalOk
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
      />
    </>
  );
}
