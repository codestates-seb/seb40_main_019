import { useState, useEffect } from 'react';
import '../css/signupModal.scss';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useDispatch, useSelector } from 'react-redux';
import { closeModal } from '../../../redux/reducers/signupModalSlice';
import { submitForm } from '../../../util/api/signupForm';
import FormInputError from '../../sign/js/FormInputError';

export default function SignupModal() {
  const [number, setNumber] = useState(123456);
  const [inputNumber, setInputNumber] = useState(null);
  const [error, setError] = useState(false);

  const data = useSelector((state) => state.modal);
  const dispatch = useDispatch();
  useEffect(() => {
    // 인증번호 받아옴
    // let value = emailValidationCheck();

    // setNumber(value);
    setNumber(123456);
    // console.log(value);
  }, []);

  const changeInputNumber = (e) => {
    setInputNumber(e.target.value);
    setError(false);
  };
  const checkValidation = () => {
    if (Number(number) === Number(inputNumber)) {
      console.log('성공');

      const dataTemp = {
        username: data.name,
        email: data.email,
        password: data.password,
        address: data.address,
        zipcode: data.postCode,
      };

      console.log(dataTemp);
      // 폼 데이터 전송.
      let submit = submitForm(dataTemp);
      console.log(submit);

      // dispatch(closeModal());
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
