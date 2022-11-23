import axios from 'axios';
import { setCookie } from '../cookie/cookie';

axios.defaults.withCredentials = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

export const kakaoLogin = () => {
  window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_KAKAO_CLIENT_ID}&redirect_uri=${process.env.REACT_APP_KAKAO_REDIRECT_URI}&response_type=code&state=kakao`;
};

export const kakaoCallback = async (accesstoken, refreshtoken) => {
  if (accesstoken && refreshtoken) {
    window.sessionStorage.setItem('accesstoken', JSON.stringify(accesstoken));
    setCookie('refreshToken', refreshtoken, {
      path: '/',
      secure: true,
      sameSite: 'none',
      expires: new Date(Date.now() + 1000 * 60 * 60 * 24 * 7),
    });

    const res = await axios.get(
      `${process.env.REACT_APP_API_URL}oauth2/authorization/kakao`,
      {
        headers: {
          Authorization: JSON.parse(
            window.sessionStorage.getItem('accesstoken')
          ),
        },
      }
    );
    console.log(res);
    // window.location.replace('/');
    if (res.status === 200) {
      let userData = res.data;
      window.sessionStorage.setItem('userData', JSON.stringify(userData));
      // window.location.replace('/');
    }
  }
};
