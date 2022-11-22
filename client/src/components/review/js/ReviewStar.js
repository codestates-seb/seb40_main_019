import '../css/ReviewStar.scss';

export default function ReviewStar({ clickStar, setClickStar, type }) {
  let arr = [1, 2, 3, 4, 5];
  return (
    <div className="reviewStar">
      {arr.map((el) => {
        if (el <= clickStar) {
          return (
            <button
              key={el}
              onClick={() => {
                setClickStar(el);
              }}
            >
              <i
                className={
                  type === 'small'
                    ? 'fa-solid fa-star'
                    : 'fa-solid fa-star fa-2x'
                }
                style={{ color: '#FFB526' }}
              ></i>
            </button>
          );
        } else {
          return (
            <button
              key={el}
              onClick={() => {
                setClickStar(el);
              }}
            >
              <i
                className={
                  type === 'small'
                    ? 'fa-solid fa-star'
                    : 'fa-solid fa-star fa-2x'
                }
              ></i>
            </button>
          );
        }
      })}
    </div>
  );
}
