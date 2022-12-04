import '../css/formInput.scss';

export default function FormInput({
  labelName,
  inputId,
  inputType,
  name,
  onChangeInput,
  value,
  placeholder,
}) {
  return (
    <>
      <div className="inputArea">
        <label className="formLabel" htmlFor={inputId}>
          {labelName}
        </label>
        <input
          name={name}
          className="formInput"
          id={inputId}
          type={inputType}
          value={value}
          onChange={onChangeInput}
          placeholder={placeholder}
        />
      </div>
    </>
  );
}
