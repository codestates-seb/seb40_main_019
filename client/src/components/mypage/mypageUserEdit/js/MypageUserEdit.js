import { useState, useEffect } from 'react';
import '../css/mypageUserEdit.scss';
import UserInfo from '../../js/UserInfo';
import FormButtonYellow from '../../../sign/js/FormButtonYellow';
import FormButtonBlue from '../../../sign/js/FormButtonBlue';
import { Link } from 'react-router-dom';
import { editUserInfo } from '../../../../util/api/mypageUser';
import { useDaumPostcodePopup } from 'react-daum-postcode';

export default function MypageUserEdit() {
  const [passwordOn, setPasswordOn] = useState(false);
  const [data, setData] = useState({
    Email: '',
    nickname: '',
    phone: '',
    name: '',
  });
  const [address, setAddress] = useState('');
  const [postCode, setPostCode] = useState('');

  const onChangeInput = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
    console.log(data);
  };
  useEffect(() => {
    setData({
      Email: 'shinker1002@naver.com',
      nickname: 'shinker1002',
      phone: '01012345678',
      name: '최민수',
    });
    setAddress('서울 관악구 관악로 1 (신림동, 서울대학교)');
    setPostCode('08826');
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
    setPostCode(data.zonecode);
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
            inputId="Email"
            inputType="email"
            name="Email"
            value={data.Email}
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
          <button className="adressBtn" onClick={postCodeHandler}>
            <UserInfo
              labelName="Address"
              inputId="Address"
              inputType="text"
              name="Address"
              value={address}
              placeholder="정보를 추가해주세요"
              p="정확한 배송을 위해 올바른 거주지를 입력해주세요."
              disabled={true}
            />
          </button>
          <button className="adressBtn" onClick={postCodeHandler}>
            <UserInfo
              labelName="PostCode"
              inputId="PostCode"
              inputType="text"
              name="PostCode"
              value={postCode}
              placeholder="정보를 추가해주세요"
              p="정확한 배송을 위해 올바른 우편번호를 입력해주세요."
              disabled={true}
            />
          </button>
          <button onClick={passwordToggleControl} className="passwordBtn">
            비밀번호 설정
          </button>
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
            inputId="name"
            inputType="text"
            name="name"
            value={data.name}
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
          {/* <UserInfo
            labelName="생일"
            inputId="name"
            inputType="text"
            name="name"
            value={data.birth}
            placeholder="Please enter your email"
          /> */}
          <FormButtonBlue
            // 리덕스에서 유저 아이디 가져와서 전달인자로 전송
            formSubmit={() =>
              editUserInfo({ ...data, Address: address, PostCode: postCode })
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
