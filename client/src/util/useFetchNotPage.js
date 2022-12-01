import axios from 'axios';
import { useState, useEffect } from 'react';
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    let params = {
      page: 1,
    };
    //요청과 통신하거나 중단하는 데에 사용하는 신호 역할
    axios
      .get(`${REACT_APP_API_URL}${url}`, {
        params: params,
      })
      .then((res) => {
        setData(res.data.data);
        setError(null);
      })
      .catch((err) => {
        setError(err.message);
      });
  }, []);

  return [data, error];
};

export default useFetch;
