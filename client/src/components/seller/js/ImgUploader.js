import ImageUploader from 'react-images-upload';
import '../css/ImgUploader.scss';

export default function ImgUploader({ pictures, setPictures, pastImg }) {
  const onDrop = (picture) => {
    setPictures([...pictures, picture]);
  };
  return (
    <ImageUploader
      withIcon={false}
      onChange={onDrop}
      imgExtension={['.jpg', '.png']}
      withPreview={true}
      maxFileSize={5242880}
      singleImage={true}
      defaultImages={(pastImg || pastImg !== null) && [pastImg]}
      buttonText={'이미지 업로드'}
      label={'최대크기: 5MB, 확장자: jpg, png'}
      fileSizeError={'파일크기가 5MB 이상입니다'}
      fileTypeError={'올바르지 않는 확장자 입니다'}
    />
  );
}
