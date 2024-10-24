package model;

import java.io.IOException;

/**
 * The interface defines a set of operations that can be performed on the images.
 * It provides operations like loading, saving, and applying transformations to images,
 * changing color components, flipping, brightening, filter, and transformations.
 */
public interface ImageModel {
  /**
   * Loads an image from a file and gives it a name for future use.
   *
   * @param path      the file path of the image to load.
   * @param imageName the name to assign to the loaded image.
   * @throws IOException if there is a problem reading the file.
   */
  void loadImage(String path, String imageName) throws IOException;

  /**
   * Saves the image with the given name to a given file path.
   *
   * @param path      the file path to save the image.
   * @param imageName the name of the image to save.
   * @throws IOException if there is a problem writing the file.
   */
  void saveImage(String path, String imageName) throws IOException;

  /**
   * Creates a new image that has only the red component of the image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new image with the red component.
   */
  void applyRedComponent(String imageName, String newImageName);

  /**
   * Creates a new image that has only the green component of the image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new image with the green component.
   */
  void applyGreenComponent(String imageName, String newImageName);

  /**
   * Creates a new image that has only the blue component of the image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new image with the blue component.
   */
  void applyBlueComponent(String imageName, String newImageName);

  /**
   * Creates a new image with the value component of the original image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new image.
   */
  void applyValue(String imageName, String newImageName);

  /**
   * Creates a new image with the intensity component of the original image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new image.
   */
  void applyIntensity(String imageName, String newImageName);

  /**
   * Creates a new image with the luma component of the original image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new image.
   */
  void applyLuma(String imageName, String newImageName);

  /**
   * Flips the image horizontally and creates a new image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new flipped image.
   */
  void flipHorizontally(String imageName, String newImageName);

  /**
   * Flips the image vertically and creates a new image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new flipped image.
   */
  void flipVertically(String imageName, String newImageName);

  /**
   * Brightens or darkens the image by the given amount and creates a new image.
   * Positive values brighten the image, negative values darken it.
   *
   * @param increment    the amount to change the brightness.
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new image.
   */
  void brightenImage(int increment, String imageName, String newImageName);

  /**
   * Blurs the original image and creates a new image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new blurred image.
   */
  void blurImage(String imageName, String newImageName);

  /**
   * Sharpens the original image and creates a new image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new blurred image.
   */
  void sharpenImage(String imageName, String newImageName);

  /**
   * Applies a sepia tone to the image and creates a new image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the new sepia image.
   */
  void applySepia(String imageName, String newImageName);

  /**
   * Splits the image into three separate images for each of the red, green, and blue components.
   *
   * @param imageName  the name of the original image.
   * @param redImage   the name of the new red component image.
   * @param greenImage the name of the new green component image.
   * @param blueImage  the name of the new blue component image.
   */
  void rgbSplit(String imageName, String redImage, String greenImage, String blueImage);

  /**
   * Combines three component images, red, green, and blue into a single new image.
   *
   * @param newImageName the name of the new combined image.
   * @param redImage     the name of the red component image.
   * @param greenImage   the name of the green component image.
   * @param blueImage    the name of the blue component image.
   */
  void rgbCombine(String newImageName, String redImage, String greenImage, String blueImage);

  /**
   * Retrieves the image associated with the given name.
   *
   * @param imgName the name of the image to get.
   * @return returns the fetched image object.
   */
  Image getImage(String imgName);
}
