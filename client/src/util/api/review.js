import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'multipart/form-data';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

//리뷰작성
export const handleAddReview = async (
  data,
  productId,
  setModalOn,
  setModalText
) => {
  const formData = new FormData();
  formData.append('reviewContent', data.reviewContent);
  formData.append('star', data.star);

  if (data && data.reviewImg.length !== 0) {
    formData.append('reviewImg', data.reviewImg[0]);
  }

  if (data.reviewContent.length < 5 || data.reviewContent.length >= 100) {
    setModalOn(true);
    setModalText('리뷰가 5~100자여야 합니다');
  } else if (data.star === 0) {
    setModalOn(true);
    setModalText('리뷰평점을 선택해셔야 합니다');
  } else {
    try {
      const res = await axios.post(
        `${REACT_APP_API_URL}review/${productId}`,
        formData
      );
      console.log(res.data);
      if (res.status === 201) {
        console.log(res.data);
        window.location.replace('/mypage/review');
      }
    } catch (error) {
      return error.response.data;
    }
  }
};

//리뷰수정
export const handleEditReview = async (
  data,
  pastData,
  setModalOn,
  setModalText
) => {
  const formData = new FormData();
  formData.append('reviewContent', data.reviewContent);
  formData.append('star', data.star);
  // formData.append('reviewImg', data.reviewImg[0]);

  if (
    data &&
    data.reviewImg.length !== 0 &&
    data.reviewImg[0] !== pastData.reviewImg
  ) {
    // formData.delete('reviewImg', '');
    formData.append('reviewImg', data.reviewImg[0]);
  }
  if (data.reviewImg[0] === undefined) {
    formData.append('delete', true);
  } else {
    formData.append('delete', false);
  }
  console.log(data.reviewImg[0]);
  if (data.reviewContent.length < 5 || data.reviewContent.length >= 100) {
    setModalOn(true);
    setModalText('리뷰가 5~100자여야 합니다');
  } else if (data.star === 0) {
    setModalOn(true);
    setModalText('리뷰평점을 선택해셔야 합니다');
  } else {
    try {
      const res = await axios.patch(
        `${REACT_APP_API_URL}review/${pastData.reviewId}`,
        formData
      );
      console.log(res.data);
      if (res.status === 200) {
        console.log(res.data);
        window.location.replace('/mypage/review');
      }
    } catch (error) {
      return error.response.data;
    }
  }
};

//리뷰삭제알람
export const handleDltReviewAlert = async (setModalText, setModalOn) => {
  setModalOn(true);
  setModalText('삭제하시겠습니까?');
};

//리뷰삭제
export const handleDltReview = async (id) => {
  try {
    const res = await axios.delete(`${REACT_APP_API_URL}review/${id}`);
    if (res.status === 204) {
      console.log(res.data);
      location.reload();
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};
