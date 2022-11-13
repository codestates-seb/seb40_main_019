import '../css/Main.scss';
import { Outlet } from 'react-router-dom';
import { useEffect, useState } from 'react';

export default function Main() {
  const [fixNav, setFixNav] = useState({});
  useEffect(() => {
    window.addEventListener('scroll', handleScroll, { capture: true }); // 스크롤 이벤트 등록
    return () => {
      window.removeEventListener('scroll', handleScroll); // 스크롤 이벤트 등록 제거(성능저하방지)
    };
  }, []);

  const handleScroll = () => {
    if (scrollY > 120) {
      setFixNav({
        marginTop: '70px',
      });
    } else {
      setFixNav({});
    }
  };

  return (
    <>
      <main style={fixNav}>
        <Outlet />
      </main>
    </>
  );
}
