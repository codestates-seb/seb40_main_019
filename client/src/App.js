import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Layout from './components/layout/js/Layout';
import Home from './pages/home/js/Home';
import Login from './pages/login/js/Login';
import Signup from './pages/signup/js/Signup';
import ShopProductList from './pages/shopProductList/js/ShopProductList';
import ShopProductDetail from './pages/shopProductDetail/js/ShopProductDetail';

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
          <Route path="/products" element={<ShopProductList />} />
          <Route path="/productdetail" element={<ShopProductDetail />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
