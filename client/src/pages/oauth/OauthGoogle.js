import { useEffect } from 'react';
import { googleCallback } from '../../util/api/oauthGoogle';
export default function OauthKakao() {
  useEffect(() => {
    const url = new URL(window.location.href);
    const tokenArr = url.search.split('&');
    // console.log(tokenArr);
    const accessToken = tokenArr[0].split('=')[1].replace('%20', ' ');
    const refreshToken = tokenArr[1].split('=')[1];

    googleCallback(accessToken, refreshToken);
  }, []);

  return (
    <>
      <div>oauthGoogle</div>
    </>
  );
}
