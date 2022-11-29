// import React from 'react';
import '../css/loading.scss';
import Spinner from '../../../assets/img/Spinner-1s-200px.gif';
export default function Loading() {
  return (
    <>
      <div className="spinnerInner">
        <img src={Spinner} alt="" />
      </div>
    </>
  );
}
