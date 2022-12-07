import '../css/shopMypageOrderList.scss';
import MypageOrderListItem from '../../../components/mypageOrderListItem/js/MypageOrderListItem';
import useFetch from '../../../util/useFetch';
import Empty from '../../../components/empty/js/Empty';
import ReactPaginate from 'react-paginate';
import { useState } from 'react';

export default function ShopMypageOrderList() {
  const [pageNum, setPageNum] = useState({ selected: 1 });
  const [pageInfo, setPageInfo] = useState();

  const handlePageChange = (page) => {
    setPageNum({ selected: page.selected + 1 });
    window.scrollTo({
      top: 200,
    });
  };

  const [items] = useFetch('orders', pageNum, setPageInfo); //page필요

  return (
    <div className="MypageOrderContainer">
      <div className="orderListTitle">
        <h1>주문 목록</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>날짜</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>상품 금액 / 수량</li>
        <li>확인 / 리뷰</li>
      </ul>
      {items && items.length !== 0 ? (
        items.map((item) => {
          return (
            <div key={item.orederId}>
              <MypageOrderListItem item={item} />
            </div>
          );
        })
      ) : (
        <Empty text={'주문한 목록이 없습니다'} />
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
