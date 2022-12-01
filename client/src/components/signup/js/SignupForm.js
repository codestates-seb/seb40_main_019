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
import ModalOk from '../../modal/js/ModalOk';
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

  const [modalOn, setModalOn] = useState(false);
  const [modalText, setModalText] = useState('');

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
      setModalOn(true);
      setModalText('닉네임을 입력하세요.');
      return;
    }

    if (!data.email) {
      setModalOn(true);
      setModalText('이메일을 입력하세요.');
      return;
    }
    if (!emailExptext.test(data.email)) {
      setModalOn(true);
      setModalText('유효하지 않은 형식의 이메일 주소입니다.');
      return;
    }

    if (!data.password) {
      setModalOn(true);
      setModalText('비밀번호를 입력하세요');
      return;
    }
    if (!passwordExptext.test(data.password)) {
      setModalOn(true);
      setModalText(
        '영문 대소문자/숫자/특수문자를 포함한 8자~16자 사이의 비밀번호를 입력해주세요.'
      );
      return;
    }

    if (!data.passwordConfirm) {
      setModalOn(true);
      setModalText('비밀번호를 한 번 더 입력해주세요.');
      return;
    }
    if (data.password !== data.passwordConfirm || !data.passwordConfirm) {
      setModalOn(true);
      setModalText('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
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
      <ModalOk
        setModalOn={setModalOn}
        modalOn={modalOn}
        modalText={modalText}
      />
    </>
  );
}
