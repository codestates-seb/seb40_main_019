import { useEffect } from 'react';
import { googleCallback } from '../../../util/api/oauthGoogle';
import Loading from '../../../components/loading/js/Loading';
import '../css/oauthGoogle.scss';

export default function OauthGoogle() {
  useEffect(() => {
    const url = new URL(window.location.href);
    const tokenArr = url.search.split('&');
    // console.log(tokenArr);
    const accesstoken = tokenArr[0].split('=')[1].replace('%20', ' ');
    const refreshToken = tokenArr[1].split('=')[1];

    googleCallback(accesstoken, refreshToken);
  }, []);

  return (
    <>
      <div className="googleInner">
        <Loading />
      </div>
    </>
  );
}
