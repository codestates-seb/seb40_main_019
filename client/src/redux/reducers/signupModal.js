import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  open: false,
  email: '',
  emailValidation: false,
};

export const modalSlice = createSlice({
  name: 'modal',
  initialState,
  reducers: {
    openModal: (state) => {
      state.open = true;
    },
    closeModal: (state) => {
      state.open = false;
    },
    setEmail: (state, { payload }) => {
      state.email = payload;
    },
    setEmailValidation: (state, { payload }) => {
      state.emailValidation = payload;
    },
  },
});

export const { openModal, closeModal, setEmail, setEmailValidation } =
  modalSlice.actions;

export default modalSlice.reducer;
