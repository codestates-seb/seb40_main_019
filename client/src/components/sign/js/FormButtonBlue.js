// import React from 'react';
import '../css/formButtonBlue.scss';

export default function FormButtonBlue({ formSubmit, btnContent }) {
  return (
    <button onClick={formSubmit} type="submit" className="btn btnColorBlue">
      {btnContent}
    </button>
  );
}
