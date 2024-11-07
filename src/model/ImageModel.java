package model;

/**
 * The interface defines a set of operations that can be performed on the images.
 * It provides operations like loading, saving, and applying transformations to images,
 * changing color components, flipping, brightening, filter, and transformations.
 */
public interface ImageModel {

  /**
   * A generic component class that supports extracting the image components - red, green, blue,
   * value, intensity, and luma.
   *
   * @param imageName     the name of the original image.
   * @param newImageName  the name of the new image.
   * @param componentType the Enum object having the components.
   */
  void applyComponent(String imageName, String newImageName, ComponentType componentType);

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
   * @param imageName the name of the image to get.
   * @return returns the fetched image object.
   */
  Image getImage(String imageName);

  /**
   * Assigns the provided image to its image name.
   *
   * @param imageName name of the image.
   * @param image     the actual image object.
   */
  void putImage(String imageName, Image image);
}
