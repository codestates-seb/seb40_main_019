import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'multipart/form-data';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

//상품등록
export const handleSubmit = async (data, setModalOn, setModalText) => {
  //formdata에 data입력
  const formData = new FormData();
  formData.append('titleImg', data.titleImg[0][0]);
  formData.append('detailImg', data.detailImg[0][0]);
  formData.append('productName', data.productName);
  formData.append('price', data.price);
  formData.append('tag', data.tag);
  if (data.productName.length < 5 || data.productName.length >= 30) {
    setModalOn(true);
    setModalText('상품명이 5~30자여야 합니다');
  } else if (data.price < 100 || data.price > 1000000000) {
    setModalOn(true);
    setModalText('판매가가 100원 ~ 1,000,000,000원 사이여야 합니다');
  } else if (data.titleImg.length === 0) {
    setModalOn(true);
    setModalText('대표이미지를 업로드하셔야 합니다');
  } else if (data.detailImg.length === 0) {
    setModalOn(true);
    setModalText('상세이미지를 업로드하셔야 합니다');
  } else {
    try {
      const res = await axios.post(
        `${REACT_APP_API_URL}products/${data.categoryId}`,
        formData
      );
      if (res.status === 201) {
        console.log(res.data);
        window.location.replace('/seller/product');
      }
    } catch (error) {
      console.error(error);
      return error;
    }
  }
};

//상품수정
export const handleEdit = async (data, pastData, setModalOn, setModalText) => {
  //formdata에 data입력
  const formData = new FormData();
  formData.append('titleImg', data.titleImg[0][0]);
  formData.append('detailImg', data.detailImg[0][0]);
  formData.append('productName', data.productName);
  formData.append('price', data.price);

  if (data.titleImg[0] === pastData.titleImg) {
    formData.delete('titleImg', '');
  }
  if (data.detailImg[0] === pastData.detailImg) {
    formData.delete('detailImg', '');
  }

  if (data.productName.length < 5 || data.productName.length >= 30) {
    setModalOn(true);
    setModalText('상품명이 5~30자여야 합니다');
  } else if (data.price < 100 || data.price > 1000000000) {
    setModalOn(true);
    setModalText('판매가가 100원 ~ 1,000,000,000원 사이여야 합니다');
  } else if (data.titleImg.length === 0) {
    setModalOn(true);
    setModalText('대표이미지를 업로드하셔야 합니다');
  } else if (data.detailImg.length === 0) {
    setModalOn(true);
    setModalText('상세이미지를 업로드하셔야 합니다');
  } else {
    try {
      const res = await axios.patch(
        `${REACT_APP_API_URL}products/${data.categoryId}/${data.productId}`,
        formData
      );
      if (res.status === 200) {
        console.log(res.data);
        window.location.replace('/seller/product');
      }
    } catch (error) {
      console.error(error);
      return error;
    }
  }
};

//상품삭제알람
export const handleDeleteAlert = async (setModalText, setModalOn) => {
  setModalOn(true);
  setModalText('삭제하시겠습니까?');
};

//상품삭제
export const handleDelete = async (data) => {
  console.log('yes');

  try {
    const res = await axios.delete(
      `${REACT_APP_API_URL}products/${data.productId}`
    );
    if (res.status === 200) {
      location.reload();
      console.log(res.data);
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};
