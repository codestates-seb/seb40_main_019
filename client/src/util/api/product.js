import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'multipart/form-data';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

//상품등록
export const handleSubmit = async (data, setModalOn) => {
  //formdata에 data입력
  const formData = new FormData();
  formData.append('titleImg', data.titleImg[0]);
  formData.append('detailImg', data.detailImg[0]);
  formData.append('productName', data.productName);
  formData.append('price', data.price);

  if (data.productName.length < 5 || data.productName.length >= 30) {
    setModalOn(true);
  } else if (data.price < 100 || data.price > 1000000000) {
    alert('판매가가 100원 ~ 1,000,000,000원 사이여야 합니다');
  } else if (data.titleImg.length === 0) {
    alert('대표이미지를 업로드하셔야 합니다');
  } else if (data.detailImg.length === 0) {
    alert('상세이미지를 업로드하셔야 합니다');
  }
  try {
    const res = await axios.post(
      `${REACT_APP_API_URL}products/${data.categoryId}`,
      formData
    );
    if (res.status === 200) {
      console.log(res.data);
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};
