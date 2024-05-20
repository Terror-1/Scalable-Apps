package com.productservice.productservice.service;

import com.google.api.client.util.IOUtils;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class ImageService {

  private String uploadFile(File file, String fileName) throws IOException {
      BlobId blobId = BlobId.of("scalable-apps-b6bd1.appspot.com", fileName); // Replace with your bucker name
      BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
      InputStream inputStream = ImageService.class.getClassLoader().getResourceAsStream("firebase.json"); // change the file name with your one
      Credentials credentials = GoogleCredentials.fromStream(inputStream);
      Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
      storage.create(blobInfo, Files.readAllBytes(file.toPath()));

      String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/scalable-apps-b6bd1.appspot.com/o/%s?alt=media";
      return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
  }

  private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
      File tempFile = new File(fileName);
      try (FileOutputStream fos = new FileOutputStream(tempFile)) {
          fos.write(multipartFile.getBytes());
          fos.close();
      }
      return tempFile;
  }

  private String getExtension(String fileName) {
      return fileName.substring(fileName.lastIndexOf("."));
  }


  public String upload(String imagePath) throws IOException {
//// Create a DiskFileItem from the image file
//      FileItem fileItem = new DiskFileItem("image", Files.probeContentType(imageFile.toPath()), false, imageFile.getName(), (int) imageFile.length(), imageFile.getParentFile());
//
//      try (InputStream input = new FileInputStream(imageFile); OutputStream os = fileItem.getOutputStream()) {
//          IOUtils.copy(input, os);
//      } catch (IOException e) {
//          e.printStackTrace();
//      }

      try {
//          String fileName = multipartFile.getOriginalFilename();
          String fileName = "file";
//          fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

//          File file = this.convertToFile(multipartFile, fileName);
          File file = new File(imagePath);// to convert multipartFile to File
          String URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
          return URL;
      } catch (Exception e) {
          e.printStackTrace();
          return "Image couldn't upload, Something went wrong";
      }
  }

}