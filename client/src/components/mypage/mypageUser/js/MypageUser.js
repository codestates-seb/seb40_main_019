import { useState, useEffect } from 'react';
import '../css/mypageUser.scss';
import UserInfo from '../../js/UserInfo';
import FormButtonYellow from '../../../sign/js/FormButtonYellow';
import FormButtonBlue from '../../../sign/js/FormButtonBlue';
import { Link } from 'react-router-dom';
import DeleteUserModal from './DeleteUserModal';
import {
  // deleteUserAccount,
  getUserInfo,
} from '../../../../util/api/mypageUser';
export default function MypageUser() {
  const [data, setData] = useState({
    email: '',
    nickname: '',
    phone: '',
    username: '',
  });
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);
  const [address, setAddress] = useState('');
  const [zipCode, setZipCode] = useState('');

  // useEffect 사용해서 데이터 받아올 예정.
  useEffect(() => {
    // 상세 정보 받아와 data에 저장
    let userData = getUserInfo();
    userData.then((res) => {
      // null 값 처리 나중에 서버에서 빈문자열로 변경
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
    });
  }, []);

  const deleteUser = () => {
    let check = window.confirm('회원 탈퇴를 진행하시겠습니까?');
    if (check) {
      // deleteUserAccount();
      setDeleteModalOpen(true);
      // window.alert('회원탈퇴 이메일 모달창');
    }
  };

  return (
    <>
      <div className="userInner">
        <h1>회원 정보</h1>
        <div className="userInfoArea">
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
            disabled={true}
          />

          <h2>추가정보</h2>
          <UserInfo
            labelName="이름"
            inputId="username"
            inputType="text"
            name="username"
            value={data.username}
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
          <UserInfo
            labelName="zipCode"
            inputId="zipCode"
            inputType="text"
            name="zipCode"
            value={zipCode}
            placeholder="정보를 추가해주세요"
            p="정확한 배송을 위해 올바른 우편번호를 입력해주세요."
            disabled={true}
          />
          <Link to={'/mypage/user/edit'}>
            <FormButtonBlue btnContent="정보수정" />
          </Link>
          <FormButtonYellow formSubmit={deleteUser} btnContent="회원탈퇴" />
        </div>
      </div>
      {deleteModalOpen && (
        <>
          <DeleteUserModal
            email={data.email}
            setDeleteModalOpen={setDeleteModalOpen}
          />
        </>
      )}
    </>
  );
}
