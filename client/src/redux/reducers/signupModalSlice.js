import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  open: false,
  nickname: '',
  email: '',
  password: '',
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
    setFormData: (state, { payload }) => {
      return {
        ...state,
        nickname: payload.nickname,
        email: payload.email,
        password: payload.password,
      };
    },
  },
});

export const { openModal, closeModal, setFormData } = modalSlice.actions;
export default modalSlice.reducer;
