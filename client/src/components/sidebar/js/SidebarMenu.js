import '../css/sidebarMenu.scss';
import { Link } from 'react-router-dom'; //수정 삭제?

import { useDispatch } from 'react-redux';
import { setCategory } from '../../../redux/reducers/productSlice';

export default function SidebarMenu() {
  const dispatch = useDispatch();

  return (
    <div className="sideMargin">
      <div className="sidebarContainer">
        <Link to="/">
          <div className="logoBox" />
        </Link>
        <ul className="tittleList">
          <button
            onClick={() =>
              dispatch(
                setCategory({
                  allProduct: true,
                  category: '',
                })
              )
            }
          >
            NEW
          </button>
          <button
            onClick={() =>
              dispatch(
                setCategory({
                  allProduct: false,
                  category: '1',
                })
              )
            }
          >
            건식 사료
          </button>
          <button
            onClick={() =>
              dispatch(
                setCategory({
                  allProduct: false,
                  category: '2',
                })
              )
            }
          >
            습식 사료
          </button>
          <button
            onClick={() =>
              dispatch(
                setCategory({
                  allProduct: false,
                  category: '3',
                })
              )
            }
          >
            자연식
          </button>
          <button
            onClick={() =>
              dispatch(
                setCategory({
                  allProduct: false,
                  category: '4',
                })
              )
            }
          >
            동결 사료
          </button>
        </ul>
      </div>
    </div>
  );
}
