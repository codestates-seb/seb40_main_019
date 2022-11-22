import { useEffect } from 'react';
import { kakaoCallback } from '../../util/api/oauthKakao';
export default function OauthKakao() {
  useEffect(() => {
    const url = new URL(window.location.href);
    const tokenArr = url.search.split('&');
    // console.log(tokenArr);
    const accesstoken = tokenArr[0].split('=')[1].replace('%20', ' ');
    const refreshtoken = tokenArr[1].split('=')[1];

    kakaoCallback(accesstoken, refreshtoken);
  }, []);

  return (
    <>
      <div>oauthKakao</div>
    </>
  );
}
