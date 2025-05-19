package com.vynyleshop.org.vynyleshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service // Spring lo riconosce come bean utilizzabile in altri componenti
public class ImageService {
  //Path of all images
  private final Path basePath = Paths.get("src/main/resources/static/img"); 

  // Update image URL
  public List<String> getImagesFor(String type, String name) {
    List<String> imagePaths = new ArrayList<>();
    String webName = name.trim().toLowerCase().replaceAll(" ", "_");
    Path targetDir = basePath.resolve(Paths.get(type, webName));

    // If directory exists
    if (Files.exists(targetDir) && Files.isDirectory(targetDir)) {
      // Try to read all files inside directory
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(targetDir)) {
        for (Path entry : stream) {
          // If element is not a directory && is a valid file
          if (!Files.isDirectory(entry) && isImageFile(entry)) {
            // Add to List an accessible path
            // String relativePath = "/img/" + type + "/" + webName + "/" + entry.getFileName();
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
            String relativePath = baseUrl + "/img/" + type + "/" + webName + "/" + entry.getFileName();

            imagePaths.add(relativePath);
          }
        }
      } catch (IOException e) {
        // Print the error in console
        e.printStackTrace();
      }
    }

    return imagePaths;
  }


  // Check if the image is a valid file
  private boolean isImageFile(Path file) {
    String filename = file.getFileName().toString().toLowerCase();
    return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".webp");
  }

}
