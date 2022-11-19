import '../css/ProductForm.scss';
import ImgUploader from '../../../components/seller/js/ImgUploader';
// import { useState } from 'react';

export default function ProductForm({
  categoryId,
  setCategoryId,
  productName,
  setProductName,
  price,
  setPrice,
  titleImg,
  setTitleImg,
  detailImg,
  setDetailImg,
}) {
  const handleCategory = (e) => {
    setCategoryId(e.target.value);
  };

  const handleName = (e) => {
    setProductName(e.target.value);
  };

  const handlePrice = (e) => {
    setPrice(e.target.value);
  };
  return (
    <>
      <div className="productContent">
        <section className="category">
          <h1>카테고리</h1>
          <ul className="categoryBtns">
            <li>
              <input
                type="radio"
                name="category"
                value="1"
                id="dryFood"
                onChange={handleCategory}
                checked={categoryId === '1'}
              ></input>
              <label htmlFor="dryFood">건식 사료</label>
            </li>
            <li>
              <input
                type="radio"
                name="category"
                value="2"
                id="wetFood"
                onChange={handleCategory}
                checked={categoryId === '2'}
              ></input>
              <label htmlFor="wetFood">습식 사료</label>
            </li>
            <li>
              <input
                type="radio"
                name="category"
                value="3"
                id="naturalFood"
                onChange={handleCategory}
                checked={categoryId === '3'}
              ></input>
              <label htmlFor="naturalFood">자연식</label>
            </li>
            <li>
              <input
                type="radio"
                name="category"
                value="4"
                id="iceFood"
                onChange={handleCategory}
                checked={categoryId === '4'}
              ></input>
              <label htmlFor="iceFood">동결 사료</label>
            </li>
          </ul>
        </section>
        <section className="productName">
          <h1>상품명</h1>
          <input onChange={handleName} defaultValue={productName}></input>
        </section>
        <section className="price">
          <h1>판매가</h1>
          <input onChange={handlePrice} defaultValue={price}></input>원
        </section>
        <div className="titleImg">
          <h1>대표이미지</h1>
          <ImgUploader pictures={titleImg} setPictures={setTitleImg} />
        </div>
        <div className="detailImg">
          <h1>상세이미지</h1>
          <ImgUploader pictures={detailImg} setPictures={setDetailImg} />
        </div>
      </div>
    </>
  );
}
