package controller;

import java.io.File;
import java.io.IOException;

import model.Image;
import model.ImageModelV2;

import static controller.ImageFileUtils.readOther;
import static controller.ImageFileUtils.readPPM;
import static controller.ImageFileUtils.saveOther;
import static controller.ImageFileUtils.savePPM;

public class ImageService {
  private final ImageModelV2 imageModel;


  public ImageService(ImageModelV2 imageModel) {
    this.imageModel = imageModel;
  }

  public void loadImage(String path, String imageName) throws IOException {
    try {
      String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images"
              + File.separator + path;
      Image image;
      if (path.contains("ppm")) {
        image = readPPM(pathRelative);
      } else if (path.contains("png") || path.contains(("jpeg")) || path.contains("jpg")) {
        image = readOther(pathRelative);
      } else {
        throw new IllegalArgumentException("Unsupported image type: " + path);
      }
      imageModel.putImage(imageName, image);
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  public void saveImage(String path, String imageName) throws IOException {
    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images"
            + File.separator + path;
    System.out.println("hello");

    Image image = imageModel.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image found with name: " + imageName);
    }
    try {
      if (path.contains("ppm")) {
        savePPM(pathRelative, image);
      } else {
        saveOther(pathRelative, image);
      }
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}
