package com.ozan.be.awsServices;

import com.ozan.be.customException.types.BadRequestException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

  @Autowired private final S3Client s3Client;
  private final String region;
  private final String bucketName;

  public S3Service(
      S3Client s3Client,
      @Value("${application.aws.s3.bucket-name}") String bucketName,
      @Value("${application.aws.region}") String region) {
    this.s3Client = s3Client;
    this.bucketName = bucketName;
    this.region = region;
  }

  public List<String> uploadImages(MultipartFile[] images) {
    List<String> imageUrls = new ArrayList<>();
    for (MultipartFile image : images) {
      try {
        String key = generateKey(image); // Generate a unique key for each image
        byte[] resizedImageBytes = resizeImage(image); // Resize image to max 512x512 pixels
        uploadToS3(key, resizedImageBytes, image.getContentType()); // Upload resized image to S3
        String imageUrl = generateImageUrl(key); // Generate permanent URL for the uploaded image
        imageUrls.add(imageUrl);
      } catch (IOException e) {
        throw new BadRequestException("Error while uploading image " + image.getOriginalFilename());
      }
    }
    return imageUrls;
  }

  private byte[] resizeImage(MultipartFile image) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
    if (bufferedImage == null) {
      throw new BadRequestException("Unsupported image format: " + image.getOriginalFilename());
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Thumbnails.of(bufferedImage)
        .size(512, 512)
        .outputFormat(getImageFormat(image.getContentType()))
        .toOutputStream(outputStream);
    return outputStream.toByteArray();
  }

  private String getImageFormat(String contentType) {
    if (contentType == null)
      throw new BadRequestException(
          "Unable to determine image format for content type: " + contentType);

    return switch (contentType.toLowerCase()) {
      case "image/jpeg", "image/jpg" -> "jpeg";
      case "image/png" -> "png";
      case "image/gif" -> "gif";
      default -> throw new BadRequestException("Unsupported image format: " + contentType);
    };
  }

  private void uploadToS3(String key, byte[] resizedImageBytes, String contentType) {
    PutObjectRequest putObjectRequest =
        PutObjectRequest.builder().bucket(bucketName).key(key).contentType(contentType).build();
    s3Client.putObject(putObjectRequest, RequestBody.fromBytes(resizedImageBytes));
  }

  private String generateImageUrl(String key) {
    return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
  }

  private String generateKey(MultipartFile file) {
    return System.currentTimeMillis() + "_" + file.getOriginalFilename();
  }
}
