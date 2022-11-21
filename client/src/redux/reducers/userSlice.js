import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  nickname: '',
  email: '',
  imageUrl: '',
};

export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setUser: (state, { payload }) => {
      return {
        ...state,
        nickname: payload.nickname,
        email: payload.email,
        imageUrl: payload.imageUrl,
      };
    },
    clearUser: (state) => {
      return {
        ...state,
        nickname: '',
        email: '',
        imageUrl: '',
      };
    },
  },
});

export const { setUser, clearUser } = userSlice.actions;

export default userSlice.reducer;
