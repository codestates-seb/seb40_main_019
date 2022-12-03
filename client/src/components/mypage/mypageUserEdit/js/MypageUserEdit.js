import { useState, useEffect } from 'react';
import '../css/mypageUserEdit.scss';
import UserInfo from '../../js/UserInfo';
import FormButtonYellow from '../../../sign/js/FormButtonYellow';
import FormButtonBlue from '../../../sign/js/FormButtonBlue';
import { Link } from 'react-router-dom';
import { getUserInfo, editUserInfo } from '../../../../util/api/mypageUser';
import { useDaumPostcodePopup } from 'react-daum-postcode';
import { useSelector } from 'react-redux';

export default function MypageUserEdit() {
  const [passwordOn, setPasswordOn] = useState(false);
  const [data, setData] = useState({
    email: '',
    nickname: '',
    phone: '',
    username: '',
  });
  const [address, setAddress] = useState('');
  const [zipCode, setZipCode] = useState('');
  const [socialLogin, setSocialLogin] = useState('');

  const user = useSelector((state) => state.user);
  const onChangeInput = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };
  useEffect(() => {
    let userData = getUserInfo();
    userData.then((res) => {
      Object.keys(res).forEach(function (el) {
        if (res[el] === null) {
          res[el] = '';
        }
      });
      setData({
        email: res.email,
        nickname: res.nickname,
        phone: res.phone,
        username: res.username,
      });
      setAddress(res.address);
      setZipCode(res.zipCode);
      setSocialLogin(res.socialLogin);
    });
  }, []);
  // 비밀번호 Open시 빈문자열 데이터 생성
  // 비밀번호 Close시 빈문자열 데이터 삭제
  const passwordToggleControl = () => {
    if (passwordOn) {
      delete data.curPassword;
      delete data.newPassword;
      delete data.newPasswordConfirm;
    } else {
      setData({
        ...data,
        curPassword: '',
        newPassword: '',
        newPasswordConfirm: '',
      });
    }
    setPasswordOn(!passwordOn);
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
    setZipCode(data.zonecode);
  };

  const postCodeHandler = (e) => {
    e.preventDefault();
    open({ onComplete: addressPostHandler });
  };

  return (
    <>
      <div className="userEditInner">
        <h1>회원 정보 수정</h1>
        <div className="userEditInfoArea">
          <h2>기본정보</h2>
          <UserInfo
            labelName="Email"
            inputId="email"
            inputType="email"
            name="email"
            value={data.email}
            placeholder="정보를 추가해주세요"
            disabled={true}
          />
          <UserInfo
            labelName="nickname"
            inputId="nickname"
            inputType="text"
            name="nickname"
            value={data.nickname}
            placeholder="정보를 추가해주세요"
            onChange={onChangeInput}
          />
          {socialLogin === 'original' && user.userRole === 'ROLE_USER' ? (
            <>
              <button onClick={passwordToggleControl} className="passwordBtn">
                비밀번호 설정
              </button>
            </>
          ) : (
            <>
              <button
                onClick={() => {
                  window.alert(
                    '소셜 로그인 계정과 게스트 계정은 비밀번호를 변경할 수 없습니다.'
                  );
                }}
                className="passwordBtn"
              >
                비밀번호 설정
              </button>
            </>
          )}
          {passwordOn && (
            <>
              <UserInfo
                labelName="현재 비밀번호"
                inputId="curPassword"
                inputType="password"
                name="curPassword"
                onChange={onChangeInput}
              />
              <UserInfo
                labelName="새 비밀번호"
                inputId="newPassword"
                inputType="password"
                name="newPassword"
                p="영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자."
                onChange={onChangeInput}
              />
              <UserInfo
                labelName="새 비밀번호 확인"
                inputId="newPasswordConfirm"
                inputType="password"
                name="newPasswordConfirm"
                onChange={onChangeInput}
              />
            </>
          )}
          <h2>추가정보</h2>
          <UserInfo
            labelName="이름"
            inputId="username"
            inputType="text"
            name="username"
            value={data.username}
            placeholder="정보를 추가해주세요"
            p="실명으로 기입하지 않는 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
            onChange={onChangeInput}
          />
          <UserInfo
            labelName="휴대폰번호"
            inputId="phone"
            inputType="text"
            name="phone"
            value={data.phone}
            placeholder="정보를 추가해주세요"
            p="정확한 번호가 아닐 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
            onChange={onChangeInput}
          />
          <button className="adressBtn" onClick={postCodeHandler}>
            <UserInfo
              labelName="Address"
              inputId="address"
              inputType="text"
              name="address"
              value={address}
              placeholder="정보를 추가해주세요"
              p="정확한 배송을 위해 올바른 거주지를 입력해주세요."
              disabled={true}
            />
          </button>
          <button className="adressBtn" onClick={postCodeHandler}>
            <UserInfo
              labelName="ZipCode"
              inputId="zipCode"
              inputType="text"
              name="zipCode"
              value={zipCode}
              placeholder="정보를 추가해주세요"
              p="정확한 배송을 위해 올바른 우편번호를 입력해주세요."
              disabled={true}
            />
          </button>
          <FormButtonBlue
            formSubmit={() =>
              editUserInfo({
                ...data,
                address: address,
                zipCode: zipCode,
                profileImage: user.imageUrl,
              })
            }
            btnContent="OK"
          />
          <Link to={'/mypage/user'}>
            <FormButtonYellow btnContent="CANCEL" />
          </Link>
        </div>
      </div>
    </>
  );
}
