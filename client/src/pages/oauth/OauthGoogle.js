import { useEffect } from 'react';
import { googleCallback } from '../../util/api/oauthGoogle';
export default function OauthKakao() {
  useEffect(() => {
    const url = new URL(window.location.href);
    const urlParserReg = /state=([a-z]+)/;
    console.log(url);
    if (urlParserReg.test(url.search)) {
      const state = urlParserReg.exec(url.search)[1];
      console.log(state);
      if (state === 'google') {
        googleCallback(url);
      }
    }
  }, []);

  return (
    <>
      <div>oauthKakao</div>
    </>
  );
}
