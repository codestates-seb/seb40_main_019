import '../css/productDetailReview.scss';
import ReviewShop from '../../review/js/ReviewShop';
import useFetch from '../../../util/useFetch';
import ReactPaginate from 'react-paginate';
import { useState } from 'react';

export default function ProductDetailReview({ setClickBtn, id }) {
  const [pageNum, setPageNum] = useState({ selected: 1 });
  const [pageInfo, setPageInfo] = useState();

  const handlePageChange = (page) => {
    setPageNum({ selected: page.selected + 1 });
  };
  const [items] = useFetch(`review/${id}`, pageNum, setPageInfo);

  return (
    <div className="reviewContainer">
      <div className="detailProductBtn">
        <button className="detailBtn" onClick={() => setClickBtn('detail')}>
          DETAIL
        </button>
        <button className="reviewBtn" onClick={() => setClickBtn('review')}>
          REVIEW
        </button>
      </div>
      <div className="reviewTextContainer">
        {items &&
          items.map((item) => {
            return (
              <div key={item.reviewId}>
                <ReviewShop item={item} />
              </div>
            );
          })}
      </div>
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
