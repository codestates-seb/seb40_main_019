import { useEffect } from 'react';
import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Layout from './components/layout/js/Layout';
import Home from './pages/home/js/Home';
import Login from './pages/login/js/Login';
import Signup from './pages/signup/js/Signup';
import OauthKakao from './pages/oauth/OauthKakao';
import OauthGoogle from './pages/oauth/OauthGoogle';
import { useSelector, useDispatch } from 'react-redux';
import { login } from './redux/reducers/loginSlice';
import { setUser } from './redux/reducers/userSlice';
import { getCookie } from './util/cookie/cookie';
import ShopProductList from './pages/shopProductList/js/ShopProductList';
import ShopProductDetail from './pages/shopProductDetail/js/ShopProductDetail';
import ShopProductOrder from './pages/shopProductOrder/js/ShopProductOrder';
// import { tokenReissue } from './util/api/Reissue';
import Seller from './pages/seller/js/Seller';
import SellerLayout from './components/layout/js/SellerLayout';
import ShopLayout from './components/layout/js/ShopLayout';
import Success from './pages/payment/js/Success';
import Failed from './pages/payment/js/Failed';
import SellerProducts from './pages/sellerProduct/js/SellerProducts';
import SellerAddProduct from './pages/sellerAddProduct/js/SellerAddProduct';
import SellerEditProduct from './pages/sellerEditProduct/js/SellerEditProduct';
import SellerOrder from './pages/sellerOrder/js/SellerOrder';
import SellerDelivering from './pages/sellerDelivering/js/SellerDelivering';
import SellerDeliveryCom from './pages/sellerDeliveryCom/js/SellerDeliveryCom';
import SellerReview from './pages/sellerReview/js/SellerReview';
import MypageUserPage from './pages/mypage/user/js/MypageUserPage';
import MypageUserEditPage from './pages/mypage/userEdit/js/MypageUserEditPage';
import MypagePointPage from './pages/mypage/point/js/MypagePointPage';
import ReviewAdd from './pages/reviewAdd/js/ReviewAdd';

function App() {
  //json-server 주소
  //json-server --watch data.json --port 3001
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);
  const loginData = useSelector((state) => state.login);

  console.log(user);
  console.log(loginData);

  useEffect(() => {
    const userData = JSON.parse(window.sessionStorage.getItem('userData'));
    const accessToken = JSON.parse(
      window.sessionStorage.getItem('accessToken')
    );
    // console.log(userData);
    // console.log(accessToken);
    // 스토리지에서 받아온 데이터가 null 이 아니면 리덕스에 데이터 저장.
    if (userData && accessToken) {
      console.log('리덕스에 저장');
      dispatch(setUser(userData));
      dispatch(login({ accessToken }));
    } else {
      console.log(getCookie('refreshToken'));
      if (getCookie('refreshToken')) {
        console.log('재발급 요청');
        // tokenReissue(getCookie('refreshToken'));
      } else {
        console.log('리프레시 토큰 없음');
      }
    }
  }, []);
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/oauth/kakao" element={<OauthKakao />} />
          <Route path="/oauth/google" element={<OauthGoogle />} />
          <Route path="/payment/success" element={<Success />} />
          <Route path="/payment/failed" element={<Failed />} />

          <Route path="/" element={<ShopLayout />}>
            <Route path="/products" element={<ShopProductList />} />
            <Route path="/product/detail" element={<ShopProductDetail />} />
            <Route path="/product/order" element={<ShopProductOrder />} />
          </Route>

          <Route path="/" element={<SellerLayout />}>
            <Route path="/seller" element={<Seller />} />
            <Route path="/seller/product" element={<SellerProducts />} />
            <Route path="/seller/add" element={<SellerAddProduct />} />
            <Route path="/seller/edit" element={<SellerEditProduct />} />
            <Route path="/seller/order" element={<SellerOrder />} />
            <Route path="/seller/delivering" element={<SellerDelivering />} />
            <Route path="/seller/deliverycom" element={<SellerDeliveryCom />} />
            <Route path="/seller/review" element={<SellerReview />} />
          </Route>

          <Route path="/" element={<ShopLayout />}>
            <Route path="/mypage/user" element={<MypageUserPage />} />
            <Route path="/mypage/user/edit" element={<MypageUserEditPage />} />
            <Route path="/mypage/point" element={<MypagePointPage />} />
            <Route path="/mypage/reviewadd" element={<ReviewAdd />} />
          </Route>
        </Route>
      </Routes>
    </>
  );
}

export default App;
