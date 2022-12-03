import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
// 엑세스 토큰 재발급
export const tokenReissue = async (refreshtoken) => {
  try {
    const res = await axios.get(`${REACT_APP_API_URL}users/reissue`, {
      headers: {
        refreshToken: refreshtoken,
      },
    });
    if (res.status === 200) {
      let userData = res.data;
      window.sessionStorage.setItem('userData', JSON.stringify(userData));
      let accesstoken = res.headers.get('authorization');
      window.sessionStorage.setItem('accesstoken', JSON.stringify(accesstoken));
      return res;
    }

    return res;
  } catch (error) {
    return error.response.data;
  }
};
