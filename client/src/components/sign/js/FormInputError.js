import '../css/formInputError.scss';

export default function FormInputError({ text }) {
  return (
    <>
      <p className="inputMessage">{text}</p>
    </>
  );
}
