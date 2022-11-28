// import React from 'react';
import '../css/paymentModal.scss';
import { useState, useEffect } from 'react';
import FormInput from '../../sign/js/FormInput';
import FormDisabledInput from '../../sign/js/FormDisabledInput';
import { useDaumPostcodePopup } from 'react-daum-postcode';
import FormButtonBlue from '../../sign/js/FormButtonBlue';
import { getUserInfo } from '../../../util/api/mypageUser';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { handleOrder } from '../../../util/api/order';
import { getPoint } from '../../../util/api/point';
// import { paymentPoint } from '../../../util/api/point';
export default function PaymentModal({ setModal, totalPrice, type }) {
  console.log(totalPrice);
  const [data, setData] = useState({
    receiverName: '',
    receiverPhone: '',
  });

  const [receiverAddress, setReceiverAddress] = useState('');
  const [receiverZipcode, setReceiverZipcode] = useState('');

  useEffect(() => {
    getPoint().then((res) => {
      if (res.data < totalPrice) {
        window.alert('포인트가 부족합니다.');
        setModal(false);
      }
    });
    let userData = getUserInfo();
    userData.then((res) => {
      // null 값 처리 나중에 서버에서 빈문자열로 변경
      Object.keys(res).forEach(function (el) {
        if (res[el] === null) {
          res[el] = '';
        }
      });
      setData({
        receiverPhone: res.phone,
        receiverName: res.username,
      });
      setReceiverAddress(res.address);
      setReceiverZipcode(res.zipCode);
    });
  }, []);

  const onChangeInput = (e) => {
    console.log(data);
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const order = () => {
    if (type === 'multi') {
      let check = window.confirm('상품울 주문하시겠습니까?');
      if (check) {
        let temp = {
          ...data,
          receiverAddress,
          receiverZipcode,
          cartOrderProductDtoList: [],
        };
        let orderItem = JSON.parse(window.localStorage.getItem('cartItem'));
        Object.keys(orderItem).forEach((el) => {
          if (orderItem[el].check) {
            console.log(orderItem[el]);
            temp.cartOrderProductDtoList.push({
              productId: orderItem[el].productsId,
              quantity: orderItem[el].count,
            });
          }
        });
        console.log(temp);
        console.log('주문 생성');
        // handleOrder
        console.log('포인트 결제');
        // paymentPoint
        return;
      }
    } else {
      window.alert('single');
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
    setReceiverAddress(fullAddress);
    setReceiverZipcode(data.zonecode);
  };

  const postCodeHandler = (e) => {
    e.preventDefault();
    open({ onComplete: addressPostHandler });
  };

  return (
    <>
      <div className="modalBackground">
        <div className="paymentModal">
          <div className="logoImg" />
          <h2>주문 정보 입력</h2>
          <FormInput
            labelName="이름"
            inputId="receiverName"
            inputType="text"
            name="receiverName"
            value={data.receiverName}
            onChangeInput={onChangeInput}
            placeholder="주문자 이름"
          />
          <FormInput
            labelName="휴대폰번호"
            inputId="receiverPhone"
            inputType="text"
            name="receiverPhone"
            value={data.receiverPhone}
            onChangeInput={onChangeInput}
            placeholder="휴대폰 번호"
          />
          <FormDisabledInput
            labelName="주소"
            inputId="receiverAddress"
            inputType="text"
            name="receiverAddress"
            value={receiverAddress}
            placeholder="주소"
            onClick={postCodeHandler}
          />
          <FormDisabledInput
            labelName="우편번호"
            inputId="receiverZipcode"
            inputType="text"
            name="receiverZipcode"
            value={receiverZipcode}
            placeholder="우편번호"
          />
          <FormButtonBlue formSubmit={order} btnContent="주문하기" />
          <FontAwesomeIcon
            className="closeBtn"
            icon={faXmark}
            onClick={() => setModal(false)}
          />
        </div>
      </div>
    </>
  );
}
