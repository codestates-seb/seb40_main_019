import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  isLogin: false,
  accesstoken: null,
};

export const loginSlice = createSlice({
  name: 'login',
  initialState,
  reducers: {
    login: (state, { payload }) => {
      return {
        ...state,
        isLogin: true,
        accesstoken: payload.accesstoken,
      };
    },
    logout: (state) => {
      return {
        ...state,
        isLogin: false,
        accesstoken: null,
      };
    },
  },
});

export const { login, logout } = loginSlice.actions;

export default loginSlice.reducer;
