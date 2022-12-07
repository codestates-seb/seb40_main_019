import '../css/SellerReview.scss';

import Review from '../../../components/seller/js/Review';
import useFetch from '../../../util/useFetch';
import ReactPaginate from 'react-paginate';
import { useState } from 'react';

export default function SellerReview() {
  const [pageNum, setPageNum] = useState({ selected: 1 });
  const [pageInfo, setPageInfo] = useState();

  const handlePageChange = (page) => {
    setPageNum({ selected: page.selected + 1 });
    window.scrollTo({
      top: 200,
    });
  };
  const [items] = useFetch('review/seller', pageNum, setPageInfo);

  return (
    <div className="sellerReview">
      <div className="productTitle">
        <h1>리뷰 목록</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>등록일</li>
        <li>상품명</li>
        <li>리뷰</li>
        <li>별점</li>
      </ul>

      {items &&
        items.map((item, idx) => {
          return (
            <div key={idx}>
              <Review item={item} />
            </div>
          );
        })}
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
