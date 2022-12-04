import axios from 'axios';
import { useState, useEffect } from 'react';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
const useFetch = (url, pageNum, setPageInfo, state, state2) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    let params = {
      page: pageNum.selected,
    };
    //요청과 통신하거나 중단하는 데에 사용하는 신호 역할
    axios
      .get(`${REACT_APP_API_URL}${url}`, {
        params: params,
      })
      .then((res) => {
        setData(res.data.data);
        setError(null);
        setPageInfo(res.data.pageInfo);
      })
      .catch((err) => {
        setError(err.message);
      });
  }, [state, state2, pageNum]);

  return [data, error];
};

export default useFetch;
