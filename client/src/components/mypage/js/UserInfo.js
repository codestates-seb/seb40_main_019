// import React from 'react';
import '../css/userInfo.scss';
export default function UserInfo({
  labelName,
  inputId,
  inputType,
  name,
  value,
  placeholder,
  p,
  disabled,
}) {
  return (
    <>
      {disabled ? (
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
          {p ? (
            <p className="explanation">{p}</p>
          ) : (
            <div className="blank"></div>
          )}
        </>
      ) : (
        <>
          <div className="userInfoFlex">
            <label htmlFor={inputId}>{labelName}</label>
            <input
              name={name}
              id={inputId}
              type={inputType}
              value={value}
              placeholder={placeholder}
            />
          </div>
          {p ? (
            <p className="explanation">{p}</p>
          ) : (
            <div className="blank"></div>
          )}
        </>
      )}
    </>
  );
}
