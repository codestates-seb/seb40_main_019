import axios from 'axios';

axios.defaults['withCredentials'] = true;
axios.defaults.headers.common['Content-Type'] = 'application/json';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

// 유저 정보 상세 조회
export const getUserInfo = async () => {
  console.log('유저 정보 상세조회 내부');
  try {
    const res = await axios.get(`${REACT_APP_API_URL}users`);

    console.log(res);

    return res;
  } catch (error) {
    return error.response.data;
  }
};
// 유저 정보 수정
export const editUserInfo = async (data) => {
  console.log('유저 정보 수정 내부');
  try {
    const res = await axios.patch(`${REACT_APP_API_URL}users`, data);

    console.log(res);

    return res;
  } catch (error) {
    return error.response.data;
  }
};
// 유저 계정 삭제
export const deleteUserAccount = async () => {
  console.log('유저 정보 삭제 내부');
  try {
    const res = await axios.delete(`${REACT_APP_API_URL}users`);

    console.log(res);

    return res;
  } catch (error) {
    return error.response.data;
  }
};
