import '../css/MainVideo.scss';

export default function MainVideo({ name, src }) {
  return (
    <>
      <video className={name} autoPlay={true} muted loop="true">
        <source src={src} type="video/webm" />
      </video>
    </>
  );
}
