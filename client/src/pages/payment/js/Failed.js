import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/failed.scss';
import Loading from '../../../components/loading/js/Loading';

export default function Failed() {
  const navigate = useNavigate();

  useEffect(() => {
    window.alert('결제 실패.');
    navigate('/');
  }, []);

  return (
    <>
      <div className="failedInner">
        <Loading />
      </div>
    </>
  );
}
