import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] = JSON.parse(
  window.sessionStorage.getItem('accesstoken')
);
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export const handleOrder = async (data) => {
  try {
    const res = await axios.post(`${REACT_APP_API_URL}orders`, data);
    if (res.status === 201) {
      console.log(res.data);
    }
  } catch (error) {
    console.error(error);
    return error;
  }
};
