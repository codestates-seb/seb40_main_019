import '../css/formButtonYellow.scss';

export default function FormButtonYellow({ formSubmit, btnContent }) {
  return (
    <button onClick={formSubmit} type="submit" className="btn btnColorYellow">
      {btnContent}
    </button>
  );
}
