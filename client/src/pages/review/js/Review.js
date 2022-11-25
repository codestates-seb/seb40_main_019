import '../css/Review.scss';
export default function Review() {
  const [items, setItems] = useState();
  useEffect(() => {
    axios.get('http://localhost:3001/review/').then((res) => {
      setItems(res.data);
    });
  }, []);
  return (
    <div className="MypageOrderContainer">
      <div className="orderListTitle">
        <h1>내가 작성한 리뷰</h1>
      </div>

      <div className="lineBold"></div>
      <ul className="lineTitle">
        <li>날짜</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>상품 금액 / 수량</li>
        <li>확인 / 리뷰</li>
      </ul>
      {/* {items &&
        items.map((item) => {
          return (
            <div key={item.orederId}>
              <MypageOrderListItem item={item} />
            </div>
          );
        })} */}
    </div>
  );
}
