package controller;

import java.io.File;
import java.io.IOException;

import model.Image;
import model.ImageModel;

import static controller.ImageFileUtils.readOther;
import static controller.ImageFileUtils.readPPM;
import static controller.ImageFileUtils.saveOther;
import static controller.ImageFileUtils.savePPM;

/**
 * Service class responsible for handling image loading and saving operations.
 */
public class ImageService {
  private final ImageModel imageModel;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel the
   */
  public ImageService(ImageModel imageModel) {
    this.imageModel = imageModel;
  }

  /**
   * Loads an image from the specified filepath.
   *
   * @param path      the filepath of the image to load.
   * @param imageName the name of the image to load.
   * @throws IOException if there is an error with reading files.
   */
  public void loadImage(String path, String imageName) throws IOException {
    try {
      String pathRelative = new File(System.getProperty("user.dir")) + File.separator + path;
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

  /**
   * Saves an image to the specified filepath.
   *
   * @param path      the filepath of the image to save to.
   * @param imageName the name of the image to save.
   * @throws IOException if there is an error with saving files.
   */
  public void saveImage(String path, String imageName) throws IOException {
    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + path;

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
