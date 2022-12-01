import { useState } from 'react';
import '../css/findPasswordModal.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import {
  findPasswordSendEmail,
  changePasssword,
} from '../../../util/api/userAccount';
import ModalOk from '../../modal/js/ModalOk';

export default function FindPasswordModal({ setPasswordModal }) {
  const [step, setStep] = useState(0);

  const [inputEmail, setInputEmail] = useState('');

  const [validationCode, setValidationCode] = useState('');
  const [inputEmailValidationInput, setInputEmailValidationInput] =
    useState('');

  const [inputPassword, setInputPassword] = useState('');

  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

  const closeModal = () => {
    setPasswordModal(false);
  };

  // step 0
  const sendEmail = () => {
    // 인증번호 받아오기
    let res = findPasswordSendEmail(inputEmail, setModalOn, setModalText);
    res.then((data) => {
      console.log(data);
      if (data.status === 200) {
        setModalOn(true);
        setModalText('인증코드 발송 완료');
        console.log(data.data);
        setValidationCode(data.data);
        // 상태코드 200 이면 설정 후 다음 단계로 이동
        setValidationCode('123456');
        setStep(1);
      }
    });
  };

  // step 1
  const checkEmailValidation = () => {
    // 인증코드, 입력코드 비교하기
    if (!inputEmailValidationInput) {
      setModalOn(true);
      setModalText('인증코드를 입력하세요.');
      return;
    }
    if (inputEmailValidationInput !== validationCode) {
      setModalOn(true);
      setModalText('인증코드가 일치하지 않습니다.');
      return;
    }
    setModalOn(true);
    setModalText('이메일 인증 완료');
    setStep(2);
  };

  // step 2
  const changePassword = () => {
    let res = changePasssword(
      inputEmail,
      inputPassword,
      setModalOn,
      setModalText
    );
    // 응답이 200 이면 변경 완료.
    console.log(res);
    res.then((data) => {
      console.log(data);
      if (data.status === 200) {
        setModalOn(true);
        setModalText('비밀번호 변경 완료');
        closeModal();
      }
    });

    // window.alert('비밀번호 변경 완료');
    // setStep(2);
  };
  return (
    <>
      <div className="modalBackground">
        <div className="modal">
          <div className="logoImg" />
          {step === 0 && (
            <>
              <h2>이메일 입력</h2>
              <p>이메일을 입력하여 비밀번호를 찾을 수 있습니다.</p>
              <p>
                자신의 <span>이메일</span>을 입력하세요.
              </p>
              <div className="modalInner">
                <input
                  name="email"
                  id="email"
                  type="email"
                  placeholder="이메일 입력"
                  value={inputEmail}
                  onChange={(e) => setInputEmail(e.target.value)}
                />
                <FormButtonBlue
                  formSubmit={sendEmail}
                  btnContent="비밀번호 찾기"
                />
                {/* <FormButtonBlue formSubmit={deleteUser} btnContent="탈퇴하기" /> */}
              </div>
            </>
          )}
          {step === 1 && (
            <>
              <h2>인증 메일 발송 완료</h2>
              <p>메일함에서 인증 메일을 확인 바랍니다.</p>
              <p>
                이메일의 <span>인증번호</span>를 입력하여 인증을 진행해주세요.
              </p>
              <div className="modalInner">
                <input
                  name="emailValidation"
                  id="emailValidation"
                  type="text"
                  placeholder="인증번호 입력"
                  value={inputEmailValidationInput}
                  onChange={(e) => setInputEmailValidationInput(e.target.value)}
                />
                <FormButtonBlue
                  formSubmit={checkEmailValidation}
                  btnContent="인증하기"
                />
              </div>
            </>
          )}
          {step === 2 && (
            <>
              <h2>비밀번호 변경</h2>
              <p>이메일 인증이 완료되었습니다.</p>
              <p>
                새로운 <span>비밀번호</span>를 입력하여 비밀번호를 변경해주세요.
              </p>
              <div className="modalInner">
                <input
                  name="password"
                  id="password"
                  type="password"
                  placeholder="비밀번호 입력"
                  value={inputPassword}
                  onChange={(e) => setInputPassword(e.target.value)}
                />
                <FormButtonBlue
                  formSubmit={changePassword}
                  btnContent="변경하기"
                />
              </div>
            </>
          )}
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
