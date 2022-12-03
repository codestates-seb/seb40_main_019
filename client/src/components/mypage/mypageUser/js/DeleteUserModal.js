import { useState } from 'react';
import '../css/deleteUserModal.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import FormButtonBlue from '../../../sign/js/FormButtonBlue';
import { deleteUserAccount } from '../../../../util/api/mypageUser';
export default function DeleteUserModal({ email, setDeleteModalOpen }) {
  const [inputEmail, setInputEmail] = useState('');

  const changeInputNumber = (e) => {
    setInputEmail(e.target.value);
  };
  const closeModal = () => {
    setDeleteModalOpen(false);
  };
  const deleteUser = () => {
    if (email === inputEmail) {
      deleteUserAccount();
    } else {
      window.alert('이메일이 일치하지 않습니다.');
    }
  };
  return (
    <>
      <div className="modalBackground">
        <div className="modal">
          <div className="logoImg" />
          <h2>회원 탈퇴 확인</h2>
          <p>자신의 이메일을 입력해 회원탈퇴를 진행하세요</p>
          <p>
            <span>{email}</span>
          </p>
          <div className="modalInner">
            <input
              name="emailValidation"
              id="emailValidation"
              type="text"
              placeholder="이메일 입력"
              value={inputEmail}
              onChange={changeInputNumber}
            />
            <FormButtonBlue formSubmit={deleteUser} btnContent="탈퇴하기" />
          </div>
          <FontAwesomeIcon
            className="closeBtn"
            icon={faXmark}
            onClick={closeModal}
          />
        </div>
      </div>
    </>
  );
}
