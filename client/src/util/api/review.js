import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'multipart/form-data';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export const handleEditReview = async (data, pastData) => {
  const formData = new FormData();
  formData.append('reviewContent', data.reviewContent);
  formData.append('star', data.star);
  if (data.reviewImg[0] !== pastData.reviewImg) {
    formData.append('reviewImg', data.reviewImg[0]);
  }

  // if (reviewContent.length < 10 || reviewContent.length >= 200) {
  //   alert('리뷰가 10~200자여야 합니다');
  // } else if (clickStar === 0) {
  //   alert('리뷰평점을 선택해셔야 합니다');
  // }

  try {
    const res = await axios.patch(
      `${REACT_APP_API_URL}review/${pastData.reviewId}`,
      formData
    );
    console.log(res.data);
    if (res.status === 200) {
      console.log(res.data);
    }
  } catch (error) {
    return error.response.data;
  }
};
