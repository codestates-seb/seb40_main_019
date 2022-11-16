import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  isLogin: false,
  accessToken: null,
};

export const loginSlice = createSlice({
  name: 'login',
  initialState,
  reducers: {
    login: (state, { payload }) => {
      return {
        ...state,
        isLogin: true,
        accessToken: payload.accessToken,
      };
    },
    logout: (state) => {
      return {
        ...state,
        isLogin: false,
        accessToken: null,
      };
    },
  },
});

export const { login, logout } = loginSlice.actions;

export default loginSlice.reducer;
