// import React from 'react';
import '../css/formDisabledInput.scss';

export default function FormInput({
  labelName,
  inputId,
  inputType,
  name,
  value,
}) {
  return (
    <>
      <div className="disabledinputArea">
        <label className="formLabel" htmlFor={inputId}>
          {labelName}
        </label>
        <input
          name={name}
          className="formInput"
          id={inputId}
          type={inputType}
          value={value}
          disabled
        />
      </div>
    </>
  );
}
