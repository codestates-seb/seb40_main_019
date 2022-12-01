import '../css/SellerOrder.scss';
import Order from '../../../components/seller/js/Order';
import useFetch from '../../../util/useFetch';
import ReactPaginate from 'react-paginate';
import { useState } from 'react';

export default function SellerOrder() {
  const [pageNum, setPageNum] = useState({ selected: 1 });
  const [pageInfo, setPageInfo] = useState();

  const handlePageChange = (page) => {
    setPageNum({ selected: page.selected + 1 });
  };

  const [items] = useFetch('orders/all', pageNum, setPageInfo);

  return (
    <div className="sellerProducts orderTop">
      <div className="productTitle">
        <h1>판매 관리</h1>
        <div className="productBtn"></div>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>발송현황</li>
        <li>주문자 정보</li>
        <li>상품정보</li>
        <li>수량</li>
      </ul>

      {items &&
        items.map((item) => {
          return (
            <div key={item.productsId}>
              <Order item={item} />
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
