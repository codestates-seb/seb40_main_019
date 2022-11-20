// import React from 'react';
import '../css/userInfo.scss';
export default function UserInfo({
  labelName,
  inputId,
  inputType,
  name,
  value,
  placeholder,
}) {
  return (
    <>
      <div className="userInfoFlex">
        <label htmlFor={inputId}>{labelName}</label>
        <input
          name={name}
          id={inputId}
          type={inputType}
          disabled
          value={value}
          placeholder={placeholder}
        />
      </div>
    </>
  );
}
