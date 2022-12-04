import '../css/formButtonBlue.scss';

export default function FormButtonBlue({
  formSubmit,
  btnContent,
  setModalOn,
  setModalText,
}) {
  return (
    <button
      onClick={() => formSubmit(setModalOn, setModalText)}
      type="submit"
      className="btn btnColorBlue"
    >
      {btnContent}
    </button>
  );
}
