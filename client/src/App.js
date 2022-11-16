import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Layout from './components/layout/js/Layout';
import Home from './pages/home/js/Home';
import Login from './pages/login/js/Login';
import Signup from './pages/signup/js/Signup';
import Products from './pages/Products/js/Products';
import OauthKakao from './pages/oauth/OauthKakao';
import OauthGoogle from './pages/oauth/OauthGoogle';
function App() {
  //json-server 주소
  //json-server --watch data.json --port 3001

  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/products" element={<Products />} />
          <Route path="oauth/kakao" element={<OauthKakao />} />
          <Route path="oauth/google" element={<OauthGoogle />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
