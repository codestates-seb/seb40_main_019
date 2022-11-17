import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Failed() {
  const navigate = useNavigate();

  useEffect(() => {
    window.alert('결제 실패.');
    navigate('/');
  }, []);

  return (
    <>
      <div>만약 같은 문제가 지속적으로 발생한다면 문의 부탁드립니다.</div>
    </>
  );
}
