package com.backend.domain.product.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.backend.domain.product.exception.NoImage;
import com.backend.domain.product.exception.UploadFailed;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service implements ImageUploadService{
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @SneakyThrows
    public String StoreImage(MultipartFile img) {
        validateFileExists(img);
        String originalFilename = img.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        //mutipartfile->bufferedImage
        BufferedImage bi= ImageIO.read(img.getInputStream());
        //이미지 사이즈변경
        bi=resizeImage(bi);
        //이미지 위치에 저장
        ImageIO.write(bi,"jpg",new File(storeFileName));

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(img.getContentType());

        try (InputStream inputStream = img.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName, storeFileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new UploadFailed();

        }

        return amazonS3.getUrl(bucketName, storeFileName).toString();
    }

    private BufferedImage resizeImage(BufferedImage src) {
        return Scalr.resize(src, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 500, 500, Scalr.OP_ANTIALIAS);
    }

    public void deleteImage(String fileUrl) {
        String splitStr = ".com/";
        String fileName = fileUrl.substring(fileUrl.lastIndexOf(splitStr) + splitStr.length());

        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }


    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new NoImage();
        }
    }

    private static String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private static String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
