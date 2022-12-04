import '../css/empty.scss';

export default function Empty({ text }) {
  return (
    <>
      <div className="emptyBox">
        <i className="fa-solid fa-triangle-exclamation"></i>
        <h3>{text}</h3>
      </div>
    </>
  );
}
