import { configureStore } from '@reduxjs/toolkit';
import signupModal from './reducers/signupModalSlice';
import user from './reducers/userSlice';
import login from './reducers/loginSlice';
import product from './reducers/productSlice';

export const store = configureStore({
  reducer: {
    modal: signupModal,
    user: user,
    login: login,
    product: product,
  },
});
