import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  open: false,
  name: '',
  email: '',
  password: '',
  address: '',
  postCode: '',
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
    setFormData: (state, { payload }) => {
      return {
        ...state,
        name: payload.name,
        email: payload.email,
        password: payload.password,
        address: payload.address,
        postCode: payload.postCode,
      };
    },
  },
});

export const { openModal, closeModal, setFormData } = modalSlice.actions;

export default modalSlice.reducer;
