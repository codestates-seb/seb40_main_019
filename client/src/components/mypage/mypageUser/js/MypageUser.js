import { useState, useEffect } from 'react';
import '../css/mypageUser.scss';
import UserInfo from '../../js/UserInfo';
import FormButtonYellow from '../../../sign/js/FormButtonYellow';
import FormButtonBlue from '../../../sign/js/FormButtonBlue';
import { Link } from 'react-router-dom';
import {
  deleteUserAccount,
  getUserInfo,
} from '../../../../util/api/mypageUser';
export default function MypageUser() {
  const [data, setData] = useState({
    Email: '',
    nickname: '',
    phone: '',
    name: '',
    Address: '',
    PostCode: '',
  });

  // useEffect 사용해서 데이터 받아올 예정.
  useEffect(() => {
    // 상세 정보 받아와 data에 저장
    getUserInfo();

    setData({
      Email: 'shinker1002@naver.com',
      nickname: 'shinker1002',
      phone: '010-1234-5678',
      name: '최민수',
      Address: '서울 관악구 관악로 1 (신림동, 서울대학교)',
      PostCode: '08826',
    });
  }, []);

  return (
    <>
      <div className="userInner">
        <h1>회원 정보</h1>
        <div className="userInfoArea">
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
            disabled={true}
          />
          <UserInfo
            labelName="Address"
            inputId="Address"
            inputType="text"
            name="Address"
            value={data.Address}
            placeholder="정보를 추가해주세요"
            p="정확한 배송을 위해 올바른 거주지를 입력해주세요."
            disabled={true}
          />
          <UserInfo
            labelName="PostCode"
            inputId="PostCode"
            inputType="text"
            name="PostCode"
            value={data.PostCode}
            placeholder="정보를 추가해주세요"
            p="정확한 배송을 위해 올바른 우편번호를 입력해주세요."
            disabled={true}
          />

          <h2>추가정보</h2>
          <UserInfo
            labelName="이름"
            inputId="name"
            inputType="text"
            name="name"
            value={data.name}
            placeholder="정보를 추가해주세요"
            p="실명으로 기입하지 않는 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
            disabled={true}
          />
          <UserInfo
            labelName="휴대폰번호"
            inputId="phone"
            inputType="text"
            name="phone"
            value={data.phone}
            placeholder="정보를 추가해주세요"
            p="정확한 번호가 아닐 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
            disabled={true}
          />
          {/* <UserInfo
            labelName="생일"
            inputId="name"
            inputType="text"
            name="name"
            value={data.birth}
            placeholder="Please enter your email"
          /> */}
          <Link to={'/mypage/user/edit'}>
            <FormButtonBlue btnContent="정보수정" />
          </Link>
          <FormButtonYellow
            formSubmit={deleteUserAccount}
            btnContent="회원탈퇴"
          />
        </div>
      </div>
    </>
  );
}
