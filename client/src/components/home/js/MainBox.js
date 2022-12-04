import '../css/MainBox.scss';

export default function MainBox({ name, idx }) {
  const data = [
    ['84%', '설문조사 응답자가 반려동물의 만족도가 크다고 답했습니다'],
    ['3.5M', 'LUXMEAL을 찾아주신 고객 수 입니다'],
    ['3X', 'AI 맞춤 사료 추천을 통해 더욱 만족감을 주었습니다'],
    ['$100B', '연간 유기견 후원기구에 기부하였습니다'],
  ];
  return (
    <>
      <div className={'mainbox' + ` ${name}`}>
        <h1>{data[idx][0]}</h1>
        <p>{data[idx][1]}</p>
      </div>
    </>
  );
}
