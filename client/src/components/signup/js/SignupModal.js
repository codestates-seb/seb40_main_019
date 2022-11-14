import { useState, useEffect } from 'react';
import '../css/signupModal.scss';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useDispatch, useSelector } from 'react-redux';
import {
  closeModal,
  setEmailValidation,
} from '../../../redux/reducers/signupModal';
import { emailValidationCheck } from '../../../util/api/signupForm';
import FormInputError from '../../sign/js/FormInputError';

export default function SignupModal() {
  const [number, setNumber] = useState(null);
  const [inputNumber, setInputNumber] = useState(null);
  const [error, setError] = useState(false);

  const dispatch = useDispatch();
  const email = useSelector((state) => state.modal.email);
  console.log(inputNumber);

  useEffect(() => {
    // 인증번호 받아옴
    let value = emailValidationCheck();

    setNumber(value);
    setNumber(123456);
    console.log(value);
    console.log(email);

    console.log(number);
  }, []);

  const changeInputNumber = (e) => {
    setInputNumber(e.target.value);
    setError(false);
  };
  const checkValidation = () => {
    console.log(number);
    console.log(inputNumber);
    if (Number(number) === Number(inputNumber)) {
      console.log('성공');
      dispatch(setEmailValidation(true));
      dispatch(closeModal());
    } else {
      setError(true);
      console.log('실패');
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
            {error && (
              <FormInputError text="Please check the verification number" />
            )}

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
    </>
  );
}
