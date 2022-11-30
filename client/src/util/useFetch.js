import axios from 'axios';
import { useState, useEffect } from 'react';
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
const useFetch = (url, state, state2) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    let params = {
      page: 0,
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
  }, [state, state2]);

  return [data, error];
};

export default useFetch;
