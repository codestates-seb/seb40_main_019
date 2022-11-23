import { useState, useEffect } from 'react';
import '../css/signupModal.scss';
import { useNavigate } from 'react-router-dom';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useDispatch, useSelector } from 'react-redux';
import { closeModal } from '../../../redux/reducers/signupModalSlice';
import { submitForm, emailValidationCheck } from '../../../util/api/signupForm';
import FormInputError from '../../sign/js/FormInputError';

export default function SignupModal() {
  const [number, setNumber] = useState(123456);
  const [inputNumber, setInputNumber] = useState(null);
  const [error, setError] = useState(false);

  const data = useSelector((state) => state.modal);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    // 인증번호 받아옴
    emailValidationCheck(data.email).then((data) => {
      setNumber(data.data);
    });
  }, []);

  const changeInputNumber = (e) => {
    setInputNumber(e.target.value);
    setError(false);
  };
  const checkValidation = async () => {
    if (number === inputNumber) {
      console.log('성공');

      const dataTemp = {
        nickname: data.nickname,
        email: data.email,
        password: data.password,
      };
      // 폼 데이터 전송.
      const submit = await submitForm(dataTemp);
      if (submit.status !== 409) {
        window.alert('회원가입 성공');
        dispatch(closeModal());
        navigate('/login');
      } else {
        window.alert(submit.message);
        dispatch(closeModal());
      }
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
