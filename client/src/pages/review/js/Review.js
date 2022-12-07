import '../css/Review.scss';

import ReviewList from '../../../components/review/js/ReviewList';
import useFetch from '../../../util/useFetch';
import Empty from '../../../components/empty/js/Empty';
import { useState } from 'react';
import ReactPaginate from 'react-paginate';

export default function Review() {
  const [pageNum, setPageNum] = useState({ selected: 1 });
  const [pageInfo, setPageInfo] = useState();

  const handlePageChange = (page) => {
    setPageNum({ selected: page.selected + 1 });
    window.scrollTo({
      top: 200,
    });
  };

  const [items] = useFetch('user/review', pageNum, setPageInfo); // 페이지도 있음
  return (
    <div className="reviewContainer">
      <div className="orderListTitle">
        <h1>내가 작성한 리뷰</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>상품정보</li>
        <li>리뷰</li>
        <li>수정 / 삭제</li>
      </ul>
      {items && items.length !== 0 ? (
        items.map((item) => {
          return (
            <div key={item.orederId}>
              <ReviewList item={item} />
            </div>
          );
        })
      ) : (
        <Empty text={'작성한 리뷰가 없습니다'} />
      )}
      <ReactPaginate
        pageCount={pageInfo && pageInfo.totalPages}
        pageRangeDisplayed={5}
        marginPagesDisplayed={0}
        breakLabel={''}
        previousLabel={'<'}
        nextLabel={'>'}
        onPageChange={handlePageChange}
        containerClassName={'pagination-ul'}
        pageClassName={'pageButton'}
        activeClassName={'currentPage'}
        previousClassName={'switchPage'}
        nextClassName={'switchPage'}
      />
    </div>
  );
}
