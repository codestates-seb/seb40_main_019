import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
// 엑세스 토큰 재발급
export const tokenReissue = async () => {
  console.log('재발급 내부');
  try {
    const res = await axios.get(`${REACT_APP_API_URL}users/reissue`);

    let jwtToken = res.headers.get('authorization');
    let userData = res.data;
    window.sessionStorage.setItem('jwtToken', JSON.stringify(jwtToken));
    window.sessionStorage.setItem('userData', JSON.stringify(userData));

    return res;
  } catch (error) {
    return error.response.data;
  }
};
