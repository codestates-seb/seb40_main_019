import MainImg from './MainImg';

export default function MainReviewContent({ data }) {
  return (
    <>
      <div>
        <MainImg src={data.src} />
      </div>
    </>
  );
}
