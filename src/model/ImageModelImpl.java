package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static model.ImageOperations.blur;
import static model.ImageOperations.brighten;
import static model.ImageOperations.combineRGB;
import static model.ImageOperations.extractBlueComponent;
import static model.ImageOperations.extractGreenComponent;
import static model.ImageOperations.extractRedComponent;
import static model.ImageOperations.pixelIntensity;
import static model.ImageOperations.pixelLuma;
import static model.ImageOperations.pixelValue;
import static model.ImageOperations.readOther;
import static model.ImageOperations.readPPM;
import static model.ImageOperations.saveOther;
import static model.ImageOperations.savePPM;
import static model.ImageOperations.sepia;
import static model.ImageOperations.sharpen;
import static model.ImageOperations.splitRGB;

/**
 * This class provides functionality to manage and manipulate images. It supports operations like
 * loading, saving, applying color filters, transforming, and combining images.
 * The images can be loaded and saved in different formats, and the class offers various methods to
 * extract and manipulate specific color components (red, green, blue) or grayscale versions of
 * images (based on value, intensity, and luma).
 */
public class ImageModelImpl implements ImageModel {
  /*
  This class uses a Map to store images by their names and applies different operations
  such as flipping, brightening, and applying filters on these images.
  */
  private final Map<String, Image> images;

  /**
   * This constructor initializes the HashMap.
   */
  public ImageModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void loadImage(String path, String imageName) throws IOException {
    try {
      String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + path;
      Image image;
      if (path.contains("ppm")) {
        image = readPPM(pathRelative);
      } else if (path.contains("png") || path.contains(("jpeg")) || path.contains("jpg")) {
        image = readOther(pathRelative);
      } else {
        throw new IllegalArgumentException("Unsupported image type: " + path);
      }
      images.put(imageName, image);
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  @Override
  public void saveImage(String path, String imageName) throws IOException {
    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + path;

    Image image = images.get(imageName);
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

  @Override
  public void applyRedComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image redImage = extractRedComponent(original);
      images.put(newImageName, redImage);
    }
  }

  @Override
  public void applyGreenComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image greenImage = extractGreenComponent(original);
      images.put(newImageName, greenImage);
    }
  }

  @Override
  public void applyBlueComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blueImage = extractBlueComponent(original);
      images.put(newImageName, blueImage);
    }
  }

  @Override
  public void applyValue(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image valueImage = pixelValue(original);
      valueImage.clamp();
      images.put(newImageName, valueImage);
    }
  }

  @Override
  public void applyIntensity(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image intensityImage = pixelIntensity(original);
      intensityImage.clamp();
      images.put(newImageName, intensityImage);
    }
  }

  @Override
  public void applyLuma(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image lumaImage = pixelLuma(original);
      lumaImage.clamp();
      images.put(newImageName, lumaImage);
    }
  }

  @Override
  public void flipHorizontally(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image flippedImage = ImageOperations.flipHorizontally(original);
      images.put(newImageName, flippedImage);
    }
  }

  @Override
  public void flipVertically(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image flippedImage = ImageOperations.flipVertically(original);
      images.put(newImageName, flippedImage);
    }
  }

  @Override
  public void brightenImage(int increment, String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image brightenedImage = brighten(original, increment);
      brightenedImage.clamp();
      images.put(newImageName, brightenedImage);
    }
  }

  @Override
  public void blurImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blurredImage = blur(original);
      blurredImage.clamp();
      images.put(newImageName, blurredImage);
    }
  }

  @Override
  public void sharpenImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image sharpenedImage = sharpen(original);
      sharpenedImage.clamp();
      images.put(newImageName, sharpenedImage);
    }
  }

  @Override
  public void applySepia(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image sepiaImage = sepia(original);
      sepiaImage.clamp();
      images.put(newImageName, sepiaImage);
    }
  }

  @Override
  public void rgbSplit(String imageName, String redImage, String greenImage, String blueImage) {
    Image original = images.get(imageName);
    if (original != null) {
      Image[] channels = splitRGB(original);
      images.put(redImage, channels[0]);
      images.put(greenImage, channels[1]);
      images.put(blueImage, channels[2]);
    }
  }

  @Override
  public void rgbCombine(String newImageName, String redImage, String greenImage, String blueImage) {
    Image red = images.get(redImage);
    Image green = images.get(greenImage);
    Image blue = images.get(blueImage);
    if (red != null && green != null && blue != null) {
      Image combinedImage = combineRGB(red, green, blue);
      images.put(newImageName, combinedImage);
    }
  }

  @Override
  public Image getImage(String imgName) throws IllegalArgumentException {
    if (this.images.get(imgName) == null) {
      throw new IllegalArgumentException("Image Not Found: " + imgName);
    }
    return this.images.get(imgName);
  }
}
