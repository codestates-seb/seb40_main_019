import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  allProduct: true,
  category: '',
  categoryPage: { selected: 1 },
};

export const productSlice = createSlice({
  name: 'product',
  initialState,
  reducers: {
    setCategory: (state, { payload }) => {
      return {
        ...state,
        allProduct: payload.allProduct,
        category: payload.category,
        categoryPage: { selected: 1 },
      };
    },
    // clearUser: (state) => {
    //   return {
    //     ...state,
    //     allProduct: '',
    //     category: '',
    //   };
    // },
  },
});

export const { setCategory } = productSlice.actions;

export default productSlice.reducer;
