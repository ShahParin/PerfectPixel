package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import util.ImageUtil;

import static model.ImageOperations.blur;
import static model.ImageOperations.brighten;
import static model.ImageOperations.greyscale;
import static model.ImageOperations.sepia;
import static model.ImageOperations.sharpen;

public class ImageModelImpl implements ImageModel {
  private final Map<String, Image> images;

  public ImageModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void loadImage(String path, String imageName) throws IOException {
    try {
      // by design
      String pathRelative = new File(System.getProperty("user.dir")).getParent() + File.separator + "images"+ File.separator + path;
      Image image = null;
      if (path.contains("ppm")) {
        image = ImageUtil.readPPM(pathRelative);
      } else {
        image = ImageUtil.readOther(pathRelative);
      }
      images.put(imageName, image);
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  @Override
  public void saveImage(String path, String imageName) throws IOException {
    String pathRelative = new File(System.getProperty("user.dir")).getParent() + File.separator + "images"+ File.separator + path;

    Image image = images.get(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image found with name: " + imageName);
    }
    try {
      if (path.contains("ppm")) {
        ImageUtil.savePPM(pathRelative, image);
      } else {
        ImageUtil.saveOther(pathRelative, image);
      }
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  @Override
  public void applyRedComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image redImage = ImageUtil.extractRedComponent(original);
      images.put(newImageName, redImage);
    }
  }

  @Override
  public void applyGreenComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image greenImage = ImageUtil.extractGreenComponent(original);
      images.put(newImageName, greenImage);
    }
  }

  @Override
  public void applyBlueComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blueImage = ImageUtil.extractBlueComponent(original);
      images.put(newImageName, blueImage);
    }
  }

  @Override
  public void applyValue(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image valueImage = ImageUtil.pixelValue(original);
      images.put(newImageName, valueImage);
    }
  }

  @Override
  public void applyIntensity(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image intensityImage = ImageUtil.pixelIntensity(original);
      images.put(newImageName, intensityImage);
    }
  }

  @Override
  public void applyLuma(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image lumaImage = ImageUtil.pixelLuma(original);
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
      images.put(newImageName, brightenedImage);
    }
  }

  @Override
  public void blurImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blurredImage = blur(original);
      images.put(newImageName, blurredImage);
    }
  }

  @Override
  public void sharpenImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image sharpenedImage = sharpen(original);
      images.put(newImageName, sharpenedImage);
    }
  }

  @Override
  public void applyGreyscale(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image greyImage = greyscale(original);
      greyImage.clamp();
      images.put(newImageName, greyImage);
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
      Image[] channels = ImageUtil.splitRGB(original);
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
      Image combinedImage = ImageUtil.combineRGB(red, green, blue);
      images.put(newImageName, combinedImage);
    }
  }
}
