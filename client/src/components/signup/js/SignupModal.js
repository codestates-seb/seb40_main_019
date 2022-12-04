import { useState, useEffect } from 'react';
import '../css/signupModal.scss';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useDispatch, useSelector } from 'react-redux';
import { closeModal } from '../../../redux/reducers/signupModalSlice';
import { submitForm, emailValidationCheck } from '../../../util/api/signupForm';
import ModalMove from '../../modal/js/ModalMove';
import { useNavigate } from 'react-router-dom';

export default function SignupModal() {
  const [number, setNumber] = useState(123456);
  const [inputNumber, setInputNumber] = useState('');
  const [modalMoveOn, setModalMoveOn] = useState(false);
  const [modalMoveText, setModalMoveText] = useState('');

  const data = useSelector((state) => state.modal);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    emailValidationCheck(data.email).then((data) => {
      setNumber(data.data);
    });
  }, []);

  const changeInputNumber = (e) => {
    setInputNumber(e.target.value);
  };
  const checkValidation = async () => {
    if (number === inputNumber) {
      const dataTemp = {
        nickname: data.nickname,
        email: data.email,
        password: data.password,
      };
      const submit = await submitForm(dataTemp);
      if (submit.status === 201) {
        setModalMoveOn(true);
        window.alert('회원가입 성공');
        dispatch(closeModal());
        navigate('/login');
      } else {
        window.alert(submit.message);
        dispatch(closeModal());
      }
    } else {
      setModalMoveOn(true);
      setModalMoveText(
        '인증번호가 일치하지 않습니다. 게스트 로그인으로 진행하시겠습니까?'
      );
    }
  };

  return (
    <>
      <div className="modalBackground">
        <div className="modal">
          <div className="logoImg" />
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
              value={inputNumber}
              onChange={changeInputNumber}
            />
            <FormButtonBlue
              formSubmit={checkValidation}
              btnContent="인증하기"
            />
          </div>
          <FontAwesomeIcon
            className="closeBtn"
            icon={faXmark}
            onClick={() => dispatch(closeModal())}
          />
        </div>
      </div>
      <ModalMove
        setModalOn={setModalMoveOn}
        modalOn={modalMoveOn}
        modalText={modalMoveText}
        link={'/login'}
      />
    </>
  );
}
