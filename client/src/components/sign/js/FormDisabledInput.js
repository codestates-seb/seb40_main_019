// import React from 'react';
import '../css/formDisabledInput.scss';

export default function FormDisabledInput({
  labelName,
  inputId,
  inputType,
  name,
  value,
  onClick,
}) {
  return (
    <>
      <div className="labelArea">
        <label className="formLabel" htmlFor={inputId}>
          {labelName}
        </label>
      </div>
      <button onClick={onClick} className="disabledinputArea">
        <input
          name={name}
          className="formInput"
          id={inputId}
          type={inputType}
          value={value}
          disabled
        />
      </button>
    </>
  );
}
