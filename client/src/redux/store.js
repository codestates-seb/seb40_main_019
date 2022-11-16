import { configureStore } from '@reduxjs/toolkit';
import signupModal from './reducers/signupModalSlice';

export const store = configureStore({
  reducer: {
    modal: signupModal,
  },
});
