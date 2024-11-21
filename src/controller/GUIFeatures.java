package controller;

import java.io.IOException;

import model.Image;

/**
 * Interface that defines the features and operations supported for images operation in GUI.
 */
public interface GUIFeatures {

  /**
   * Loads an image from the specified file path.
   *
   * @param path      the path to the image file.
   * @param imageName the name to assign to the loaded image.
   * @throws IOException if there is an error loading the image.
   */
  void load(String path, String imageName) throws IOException;

  /**
   * Saves the specified image to the given file path.
   *
   * @param path      the path where the image will be saved.
   * @param imageName the name of the image to save.
   * @throws IOException if there is an error saving the image.
   */
  void save(String path, String imageName) throws IOException;

  /**
   * Applies a blur effect to the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image after the blur effect.
   * @throws IOException if there is an error processing the image.
   */
  void blur(String imageName, String newImageName) throws IOException;

  /**
   * Applies a sharpen effect to the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image after the sharpen effect.
   * @throws IOException if there is an error processing the image.
   */
  void sharpen(String imageName, String newImageName) throws IOException;

  /**
   * Flips the specified image horizontally and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image after the horizontal flip.
   * @throws IOException if there is an error processing the image.
   */
  void horizontalFlip(String imageName, String newImageName) throws IOException;

  /**
   * Flips the specified image vertically and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image after the vertical flip.
   * @throws IOException if there is an error processing the image.
   */
  void verticalFlip(String imageName, String newImageName) throws IOException;

  /**
   * Converts the specified image to greyscale and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new greyscale image.
   * @throws IOException if there is an error processing the image.
   */
  void greyscale(String imageName, String newImageName) throws IOException;

  /**
   * Applies a sepia filter to the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image after applying the sepia filter.
   * @throws IOException if there is an error processing the image.
   */
  void sepia(String imageName, String newImageName) throws IOException;

  /**
   * Extracts and saves the red component of the specified image with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image with the red component.
   * @throws IOException if there is an error processing the image.
   */
  void redComponent(String imageName, String newImageName) throws IOException;

  /**
   * Extracts and saves the green component of the specified image with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image with the green component.
   * @throws IOException if there is an error processing the image.
   */
  void greenComponent(String imageName, String newImageName) throws IOException;

  /**
   * Extracts and saves the blue component of the specified image with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image with the blue component.
   * @throws IOException if there is an error processing the image.
   */
  void blueComponent(String imageName, String newImageName) throws IOException;

  /**
   * Compresses the specified image by a percentage and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new compressed image.
   * @param percent      the percentage to compress the image by.
   * @throws IOException if there is an error processing the image.
   */
  void compress(String imageName, String newImageName, double percent) throws IOException;

  /**
   * Adjusts the levels (black, mid, and white) of the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image after the levels adjustment.
   * @param black        the new black point.
   * @param mid          the new mid point.
   * @param white        the new white point.
   * @throws IOException if there is an error processing the image.
   */
  void levelsAdjust(String imageName, String newImageName, int black, int mid, int white)
          throws IOException;

  /**
   * Applies color correction to the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new color-corrected image.
   * @throws IOException if there is an error processing the image.
   */
  void colorCorrect(String imageName, String newImageName) throws IOException;

  /**
   * Retrieves the image object associated with the specified image name.
   *
   * @param imageName the name of the image to retrieve.
   * @return the image object corresponding to the specified image name.
   */
  Image getImage(String imageName);

  /**
   * Retrieves the histogram of the specified image.
   *
   * @param imageName the name of the image for which to retrieve the histogram.
   * @return the histogram of the specified image.
   */
  Image getHistogram(String imageName);

  /**
   * Applies a blur effect with split operations to the specified image and saves it with a new
   * name.
   *
   * @param imageName    the name of the image to process.
   * @param newImageName the name of the new image after the blur effect with split operations.
   * @param percent      the percentage to apply the blur effect.
   * @throws IOException if there is an error processing the image.
   */
  void blurSplitOperation(String imageName, String newImageName, double percent) throws IOException;

  /**
   * Applies a levels adjustment with split operations to the specified image and saves it with a
   * new name.
   *
   * @param currentImageName the name of the current image.
   * @param outputImageName  the name of the new image after the levels adjustment.
   * @param black            the new black point.
   * @param mid              the new mid point.
   * @param white            the new white point.
   * @param percent          the percentage to apply the levels adjustment.
   * @throws IOException if there is an error processing the image.
   */
  void levelsAdjustmentSplitOperation(String currentImageName, String outputImageName, double black,
                                      double mid, double white, double percent) throws IOException;

  /**
   * Applies a sharpen effect with split operations to the specified image and saves it with
   * a new name.
   *
   * @param currentImageName the name of the current image.
   * @param outputImageName  the name of the new image after the sharpen effect.
   * @param inputValue       the input value for the sharpen operation.
   * @throws IOException if there is an error processing the image.
   */
  void sharpenSplitOperation(String currentImageName, String outputImageName, double inputValue)
          throws IOException;

  /**
   * Applies a sepia filter with split operations to the specified image and saves it with
   * a new name.
   *
   * @param currentImageName the name of the current image.
   * @param outputImageName  the name of the new image after the sepia filter.
   * @param inputValue       the input value for the sepia operation.
   * @throws IOException if there is an error processing the image.
   */
  void sepiaSplitOperation(String currentImageName, String outputImageName, double inputValue)
          throws IOException;

  /**
   * Converts the specified image to greyscale using LUMA Component with split operations and
   * saves it with a new name.
   *
   * @param currentImageName the name of the current image.
   * @param outputImageName  the name of the new greyscale image.
   * @param inputValue       the input value for the greyscale operation.
   * @throws IOException if there is an error processing the image.
   */
  void greyscaleSplitOperation(String currentImageName, String outputImageName, double inputValue)
          throws IOException;

  /**
   * Applies color correction with split operations to the specified image and saves it with
   * a new name.
   *
   * @param currentImageName the name of the current image.
   * @param outputImageName  the name of the new image after the color correction.
   * @param inputValue       the input value for the color correction operation.
   * @throws IOException if there is an error processing the image.
   */
  void colorCorrectionSplitOperation(String currentImageName, String outputImageName,
                                     double inputValue) throws IOException;
}
