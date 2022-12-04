import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  nickname: '',
  email: '',
  imageUrl: '',
  userRole: '',
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
        userRole: payload.userRole,
      };
    },
    clearUser: (state) => {
      return {
        ...state,
        nickname: '',
        email: '',
        imageUrl: '',
        userRole: '',
      };
    },
  },
});
export const { setUser, clearUser } = userSlice.actions;
export default userSlice.reducer;
