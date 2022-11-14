import { configureStore } from '@reduxjs/toolkit';
import signupModal from './reducers/signupModal';

export const store = configureStore({
  reducer: {
    modal: signupModal,
  },
});
