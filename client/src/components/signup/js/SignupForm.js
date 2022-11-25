import { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/signupForm.scss';
import FormInput from '../../sign/js/FormInput';
import FormButtonYellow from '../../sign/js/FormButtonYellow';
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
  const [data, setDate] = useState({
    nickname: '',
    email: '',
    password: '',
    passwordConfirm: '',
  });

  const modal = useSelector((state) => state.modal.open);

  const dispatch = useDispatch();
  // 회원가입 폼 전송
  const formSubmit = (e) => {
    e.preventDefault();

    openModalControl(e);
  };
  // 인풋 변경 함수
  const onChangeInput = (e) => {
    console.log(data);
    setDate({ ...data, [e.target.name]: e.target.value });
  };
  // 이메일 인증 모달창 함수
  const openModalControl = (e) => {
    e.preventDefault();

    if (!data.nickname) {
      window.alert('Please enter your nickname');
      return;
    }
    if (!emailExptext.test(data.email)) {
      window.alert('The email is not a valid email address.');
      return;
    }
    if (!passwordExptext.test(data.password)) {
      window.alert('The password is not a valid password.');
      return;
    }
    if (data.password !== data.passwordConfirm || !data.passwordConfirm) {
      window.alert('Confirm password does not match password.');
      return;
    }

    let temp = {
      nickname: data.nickname,
      email: data.email,
      password: data.password,
    };
    // console.log(temp);
    dispatch(setFormData(temp));
    dispatch(openModal());
  };
  return (
    <>
      <div className="signUpTitle">
        <h1>Sign Up</h1>
      </div>
      <form
        className="signupForm"
        action="#"
        onSubmit={(e) => e.preventDefault()}
      >
        <FormInput
          labelName="Nickname"
          inputId="nickname"
          inputType="text"
          name="nickname"
          value={data.nickname}
          onChangeInput={onChangeInput}
          placeholder="Please enter your nickname"
        />
        <FormInput
          labelName="Email"
          inputId="email"
          inputType="email"
          name="email"
          value={data.email}
          onChangeInput={onChangeInput}
          placeholder="Please enter your email"
        />
        <FormInput
          labelName="Password"
          inputId="password"
          inputType="password"
          name="password"
          value={data.password}
          onChangeInput={onChangeInput}
          placeholder="Please enter your password"
        />
        <FormInput
          labelName="Password Confirm"
          inputId="passwordConfirm"
          inputType="Password"
          name="passwordConfirm"
          value={data.passwordConfirm}
          onChangeInput={onChangeInput}
          placeholder="Please re-enter your password"
        />
        <FormButtonYellow formSubmit={formSubmit} btnContent="Sign up" />
      </form>
      <div className="loginLink">
        Already have an account?
        <Link to={'/login'}>Log in</Link>
      </div>
      {modal && <SignupModal />}
    </>
  );
}
