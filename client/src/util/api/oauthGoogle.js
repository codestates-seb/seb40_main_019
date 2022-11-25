// import { checkIsLogin } from '../lib/checkIsLogin';
import axios from 'axios';
import { setCookie } from '../cookie/cookie';

axios.defaults.withCredentials = true;

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export const googleLogin = `${REACT_APP_API_URL}oauth2/authorization/google`;

export const googleCallback = async (accesstoken, refreshtoken) => {
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
