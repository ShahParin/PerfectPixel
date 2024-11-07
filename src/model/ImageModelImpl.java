package model;

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
  protected Map<String, Image> images;

  /**
   * This constructor initializes the HashMap.
   */
  public ImageModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void applyRedComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image redImage = extractRedComponent(original);
      images.put(newImageName, redImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void applyGreenComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image greenImage = extractGreenComponent(original);
      images.put(newImageName, greenImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void applyBlueComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blueImage = extractBlueComponent(original);
      images.put(newImageName, blueImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void applyValue(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image valueImage = pixelValue(original);
      valueImage.clamp();
      images.put(newImageName, valueImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void applyIntensity(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image intensityImage = pixelIntensity(original);
      intensityImage.clamp();
      images.put(newImageName, intensityImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void applyLuma(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image lumaImage = pixelLuma(original);
      lumaImage.clamp();
      images.put(newImageName, lumaImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void flipHorizontally(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image flippedImage = ImageOperations.flipHorizontally(original);
      images.put(newImageName, flippedImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void flipVertically(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image flippedImage = ImageOperations.flipVertically(original);
      images.put(newImageName, flippedImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void brightenImage(int increment, String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image brightenedImage = brighten(original, increment);
      brightenedImage.clamp();
      images.put(newImageName, brightenedImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void blurImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blurredImage = blur(original);
      blurredImage.clamp();
      images.put(newImageName, blurredImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void sharpenImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image sharpenedImage = sharpen(original);
      sharpenedImage.clamp();
      images.put(newImageName, sharpenedImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void applySepia(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image sepiaImage = sepia(original);
      sepiaImage.clamp();
      images.put(newImageName, sepiaImage);
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
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
    } else {
      throw new IllegalArgumentException("Image-" + imageName + " was never loaded.");
    }
  }

  @Override
  public void rgbCombine(String newImageName, String redImage, String greenImage,
                         String blueImage) {
    Image red = images.get(redImage);
    Image green = images.get(greenImage);
    Image blue = images.get(blueImage);
    if (red != null && green != null && blue != null) {
      Image combinedImage = combineRGB(red, green, blue);
      images.put(newImageName, combinedImage);
    } 
    else if (red == null) {
      throw new IllegalArgumentException("Image-" + redImage + " was never loaded.");
    }
    else if (green == null) {
      throw new IllegalArgumentException("Image-" + greenImage + " was never loaded.");
    }
    else {
      throw new IllegalArgumentException("Image-" + blueImage + " was never loaded.");
    }
  }

  @Override
  public Image getImage(String imageName) {
    Image thisImage = this.images.get(imageName);
    if (thisImage == null) {
      throw new IllegalArgumentException("Image Not Found: " + imageName);
    }

    int[][] redChannel = thisImage.getRedChannel();
    int[][] greenChannel = thisImage.getGreenChannel();
    int[][] blueChannel = thisImage.getBlueChannel();

    return new Image(redChannel, greenChannel, blueChannel);
  }

  @Override
  public void putImage(String imageName, Image image) {
    images.put(imageName, image);
  }
}
