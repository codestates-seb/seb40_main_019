import { useState } from 'react';
import '../css/signinForm.scss';
import FormInput from '../../sign/js/FormInput';
import FormButtonYellow from '../../sign/js/FormButtonYellow';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import kakaoIcon from '../../../assets/img/kakaoIcon.png';
import googleIcon from '../../../assets/img/googleIcon.png';
import { Link } from 'react-router-dom';
import FormInputError from '../../sign/js/FormInputError';
import { kakaoLogin } from '../../../util/api/oauthKakao';
import { googleLogin } from '../../../util/api/oauthGoogle';

export default function SigninForm() {
  const [data, setDate] = useState({});

  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  const formSubmit = (e) => {
    e.preventDefault();
    let error = false;

    if (data.Email === undefined) {
      setEmailError(true);
      error = true;
    }
    if (data.Password === undefined) {
      setPasswordError(true);
      error = true;
    }
    if (!error) {
      window.alert('제출');
    }
  };

  const onChangeInput = (e) => {
    setDate({ ...data, [e.target.name]: e.target.value });

    if (data.Email !== undefined) {
      setEmailError(false);
    }
    if (data.Password !== undefined) {
      setPasswordError(false);
    }
  };

  const handleOAuthKakao = () => {
    kakaoLogin();
  };
  // const handleOAuthGoogle = () => {
  //   googleLogin();
  // };
  return (
    <>
      <h1>Log in</h1>
      <form
        className="signinForm"
        action="#"
        onSubmit={(e) => e.preventDefault()}
      >
        <FormInput
          labelName="Email"
          inputId="Text"
          inputType="text"
          name="Email"
          onChangeInput={onChangeInput}
          placeholder="Please enter your Email"
        />
        {emailError && <FormInputError text="Email cannot be empty." />}

        <FormInput
          labelName="Password"
          inputId="Password"
          inputType="Password"
          name="Password"
          onChangeInput={onChangeInput}
          placeholder="Please enter your password"
        />
        {passwordError && <FormInputError text="Password cannot be empty." />}

        <FormButtonYellow formSubmit={formSubmit} btnContent="Log in" />
        <div className="signupLink">
          Don’t have an account?
          <Link to={'/signup'}>Sign in</Link>
        </div>
        <div className="flexBox">
          <button onClick={handleOAuthKakao}>
            <img src={kakaoIcon} alt="kakaoAuth" />
          </button>
          <button onClick={googleLogin}>
            <img src={googleIcon} alt="googleAuth" />
          </button>
        </div>
        <FormButtonBlue btnContent="Guest" />
        <FormButtonBlue btnContent="Seller" />
        {/* <FormButtonYellow formSubmit={formSubmit} btnContent="Signup" /> */}
      </form>
    </>
  );
}
