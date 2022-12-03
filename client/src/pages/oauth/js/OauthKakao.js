import { useEffect } from 'react';
import { kakaoCallback } from '../../../util/api/oauthKakao';
import Loading from '../../../components/loading/js/Loading';
import '../css/oauthKakao.scss';

export default function OauthKakao() {
  useEffect(() => {
    const url = new URL(window.location.href);
    const tokenArr = url.search.split('&');
    const accesstoken = tokenArr[0].split('=')[1].replace('%20', ' ');
    const refreshtoken = tokenArr[1].split('=')[1];

    kakaoCallback(accesstoken, refreshtoken);
  }, []);

  return (
    <>
      <div className="kakaoInner">
        <Loading />
      </div>
    </>
  );
}
