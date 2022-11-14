import { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/signupForm.scss';
import FormInput from '../../sign/js/FormInput';
import FormDisabledInput from '../../sign/js/FormDisabledInput';
import FormButtonYellow from '../../sign/js/FormButtonYellow';
import { useDaumPostcodePopup } from 'react-daum-postcode';
import FormInputError from '../../sign/js/FormInputError';
import SignupModal from './SignupModal';

import { useSelector, useDispatch } from 'react-redux';
import {
  openModal,
  setFormData,
} from '../../../redux/reducers/signupModalSlice';
// eslint-disable-next-line no-useless-escape
let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
let passwordExptext = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;

export default function Signup() {
  const [address, setAddress] = useState('');
  const [postCode, setPostCode] = useState('');
  const [data, setDate] = useState({});
  // eslint-disable-next-line no-unused-vars

  const [nameError, setNameError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [passwordConfirmError, setPasswordConfirmError] = useState(false);
  const [addressError, setAddressError] = useState(false);

  const modal = useSelector((state) => state.modal.open);

  const dispatch = useDispatch();
  // 회원가입 폼 전송
  const formSubmit = (e) => {
    e.preventDefault();
    let error = false;

    if (!data.Name) {
      setNameError(true);
      error = true;
    }
    if (!emailExptext.test(data.Email)) {
      setEmailError(true);
      error = true;
    }
    if (!passwordExptext.test(data.Password)) {
      setPasswordError(true);
      error = true;
    }
    if (data.Password !== data.PasswordConfirm || !data.PasswordConfirm) {
      setPasswordConfirmError(true);
      error = true;
    }
    if (!address) {
      setAddressError(true);
      error = true;
    }
    if (!postCode) {
      setAddressError(true);
      error = true;
    }
    if (!error) {
      openModalControl(e);
    }
  };
  // 인풋 변경 함수
  const onChangeInput = (e) => {
    setDate({ ...data, [e.target.name]: e.target.value });
    if (emailExptext.test(data.Email)) {
      setEmailError(false);
    }
    if (passwordExptext.test(data.Password)) {
      setPasswordError(false);
    }
    if (data.password === data.PasswordConfirm) {
      setPasswordConfirmError(false);
    }
  };
  // 주소 입력
  const open = useDaumPostcodePopup();

  const addressPostHandler = (data) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }

      if (data.buildingName !== '') {
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }
    setAddress(fullAddress);
    setPostCode(data.zonecode);
    setAddressError(false);
  };

  const postCodeHandler = (e) => {
    e.preventDefault();
    open({ onComplete: addressPostHandler });
  };
  // 이메일 인증 모달창 함수
  const openModalControl = (e) => {
    e.preventDefault();
    let temp = {
      name: data.Name,
      email: data.Email,
      password: data.Password,
      address: address,
      postCode: postCode,
    };
    // console.log(temp);
    dispatch(setFormData(temp));
    dispatch(openModal());
  };
  return (
    <>
      <h1>Sign Up</h1>
      <form
        className="signupForm"
        action="#"
        onSubmit={(e) => e.preventDefault()}
      >
        <FormInput
          labelName="Name"
          inputId="Text"
          inputType="text"
          name="Name"
          onChangeInput={onChangeInput}
          placeholder="Please enter your name"
        />
        {nameError && <FormInputError text="Please enter your nickname." />}
        <div className="addressFlexBox">
          <FormInput
            labelName="Email"
            inputId="Email"
            inputType="email"
            name="Email"
            value={data.Email}
            onChangeInput={onChangeInput}
            placeholder="Please enter your email"
          />
        </div>
        {emailError && (
          <FormInputError text="The email is not a valid email address." />
        )}
        <FormInput
          labelName="Password"
          inputId="Password"
          inputType="Password"
          name="Password"
          onChangeInput={onChangeInput}
          placeholder="Please enter your password"
        />
        {passwordError && (
          <FormInputError text="The password is not a valid password." />
        )}
        <FormInput
          labelName="Password Confirm"
          inputId="PasswordConfirm"
          inputType="Password"
          name="PasswordConfirm"
          onChangeInput={onChangeInput}
          placeholder="Please re-enter your password"
        />
        {passwordConfirmError && (
          <FormInputError text="Confirm password does not match password." />
        )}
        <div className="addressFlexBox">
          <FormDisabledInput
            labelName="Address"
            inputId="Address"
            inputType="text"
            name="Address"
            value={address}
          />
          <button onClick={postCodeHandler}>Address</button>
        </div>
        {addressError && <FormInputError text="Please enter your address." />}
        <FormDisabledInput
          labelName="PostCode"
          inputId="PostCode"
          inputType="text"
          name="PostCode"
          value={postCode}
        />
        {addressError && <FormInputError text="Please enter your address." />}
        <FormButtonYellow formSubmit={formSubmit} btnContent="Signup" />
      </form>
      <div className="loginLink">
        Already have an account?
        <Link to={'/login'}>Log in</Link>
      </div>
      {modal && <SignupModal />}
    </>
  );
}
