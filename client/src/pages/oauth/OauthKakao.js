import { useEffect } from 'react';
import { kakaoCallback } from '../../util/api/oauthKakao';
export default function OauthKakao() {
  useEffect(() => {
    const url = new URL(window.location.href);
    const urlParserReg = /state=([a-z]+)/;
    console.log(url);
    if (urlParserReg.test(url.search)) {
      const state = urlParserReg.exec(url.search)[1];
      console.log(state);
      if (state === 'kakao') {
        kakaoCallback(url);
      }
    }
  }, []);

  return (
    <>
      <div>oauthKakao</div>
    </>
  );
}
