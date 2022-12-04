import axios from 'axios';
import { setCookie } from '../cookie/cookie';

axios.defaults.withCredentials = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export const kakaoLogin = `${REACT_APP_API_URL}oauth2/authorization/kakao`;

export const kakaoCallback = async (accesstoken, refreshtoken) => {
  if (accesstoken && refreshtoken) {
    window.sessionStorage.setItem('accesstoken', JSON.stringify(accesstoken));
    setCookie('refreshtoken', refreshtoken, {
      path: '/',
      secure: true,
      sameSite: 'none',
      expires: new Date(Date.now() + 1000 * 60 * 60 * 24 * 7),
    });

    const res = await axios.get(`${REACT_APP_API_URL}users/social-user`, {
      headers: {
        Authorization: JSON.parse(window.sessionStorage.getItem('accesstoken')),
      },
    });
    if (res.status === 200) {
      let userData = res.data;
      window.sessionStorage.setItem('userData', JSON.stringify(userData));
      window.location.replace('/');
    }
  }
};
