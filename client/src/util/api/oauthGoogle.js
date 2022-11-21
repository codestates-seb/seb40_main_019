// import { checkIsLogin } from '../lib/checkIsLogin';
import axios from 'axios';
import { setCookie } from '../cookie/cookie';
axios.defaults.withCredentials = true;

export const googleLogin = () => {
  window.location.href = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${process.env.REACT_APP_GOOGLE_CLIENT_ID}&redirect_uri=${process.env.REACT_APP_GOOGLE_REDIRECT_URI}&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile&state=google`;
};

export const googleCallback = async (accessToken, refreshToken) => {
  if (accessToken && refreshToken) {
    window.sessionStorage.setItem('accessToken', JSON.stringify(accessToken));
    setCookie('refreshToken', refreshToken, {
      path: '/',
      secure: true,
      sameSite: 'none',
      expires: new Date(Date.now() + 1000 * 60 * 60 * 24 * 7),
    });

    const res = await axios.get(
      `${process.env.REACT_APP_API_URL}users/test/access-token`,
      {
        headers: {
          Authorization: JSON.parse(
            window.sessionStorage.getItem('accessToken')
          ),
        },
      }
    );
    if (res.status === 200) {
      let userData = res.data;
      window.sessionStorage.setItem('userData', JSON.stringify(userData));
      window.location.replace('/');
    }
  }
};
