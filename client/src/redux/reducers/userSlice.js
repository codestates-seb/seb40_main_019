import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  name: '',
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
        name: payload.name,
        email: payload.email,
        imageUrl: payload.imageUrl,
      };
    },
    clearUser: (state) => {
      return {
        ...state,
        name: '',
        email: '',
        imageUrl: '',
      };
    },
  },
});

export const { setUser, clearUser } = userSlice.actions;

export default userSlice.reducer;
