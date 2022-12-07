import '../css/mypagePointList.scss';
import MypagePointListItem from './MypagePointListItem';

import Empty from '../../../empty/js/Empty';
import useFetch from '../../../../util/useFetch';
import ReactPaginate from 'react-paginate';
import { useState } from 'react';

export default function MypagePointList() {
  const [pageNum, setPageNum] = useState({ selected: 1 });
  const [pageInfo, setPageInfo] = useState();

  const handlePageChange = (page) => {
    setPageNum({ selected: page.selected + 1 });
    window.scrollTo({
      top: 200,
    });
  };
  const [pointList] = useFetch('point/history', pageNum, setPageInfo);
  return (
    <div className="mypagePointListContainer">
      <div className="pointListTitle">
        <h1>포인트 내역</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="pointTitle">
        <li>거래일</li>
        <li>충전 포인트</li>
        <li>사용 포인트</li>
        <li>잔여 포인트</li>
      </ul>

      {pointList ? (
        pointList.map((point) => {
          return (
            <div key={point.pointHistoryId}>
              <MypagePointListItem point={point} />
            </div>
          );
        })
      ) : (
        <Empty text={'포인트 거래내역이 없습니다'} />
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
