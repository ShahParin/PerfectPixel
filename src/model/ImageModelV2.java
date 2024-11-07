package model;

/**
 * This interface extends the ImageModel and supports additional functions like compression,
 * histogram, color correction, level adjustment and split image display.
 */
public interface ImageModelV2 extends ImageModel {

  /**
   * Compresses the image to the provided percentage.
   *
   * @param imageName    the name of the image to compress.
   * @param newImageName the name of compressed image.
   * @param percent      the percentage of compression.
   */
  void applyCompression(String imageName, String newImageName, double percent);

  /**
   * Creates an image that is the histogram of the provided image.
   *
   * @param imageName    the name of the original image.
   * @param newImageName the name of the image that is the histogram.
   */
  void applyHistogramVisualization(String imageName, String newImageName);

  /**
   * Corrects the color of the image by aligning the histogram peaks of individual channels.
   *
   * @param imageName    the name of the image to apply color correct.
   * @param newImageName the name of the color corrected image.
   */
  void applyColorCorrection(String imageName, String newImageName);

  /**
   * Adjusts the levels of an image by mapping its color channels to specified black, middle tone,
   * and white point values, enhancing contrast and brightness.
   *
   * @param black        the black point for clamping pixels.
   * @param mid          the mid value for clamping pixels.
   * @param white        the white value for clamping pixels.
   * @param imageName    the name of the image to adjust levels.
   * @param newImageName the name of the image with adjusted levels.
   */
  void applyLevelsAdjustment(int black, int mid, int white, String imageName, String newImageName);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param imageName    the name of image to apply transformation.
   * @param newImageName the name of the transformed image.
   * @param percentage   the percentage of image split for the image transformation.
   */
  void blurImageSplit(String imageName, String newImageName, double percentage);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param imageName    the name of image to apply transformation.
   * @param newImageName the name of the transformed image.
   * @param percentage   the percentage of image split for the image transformation.
   */
  void sharpenImageSplit(String imageName, String newImageName, double percentage);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param imageName    the name of image to apply transformation.
   * @param newImageName the name of the transformed image.
   * @param percentage   the percentage of image split for the image transformation.
   */
  void sepiaImageSplit(String imageName, String newImageName, double percentage);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param imageName    the name of image to apply transformation.
   * @param newImageName the name of the transformed image.
   * @param percentage   the percentage of image split for the image transformation.
   */
  void valueComponentImageSplit(String imageName, String newImageName, double percentage);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param imageName    the name of image to apply transformation.
   * @param newImageName the name of the transformed image.
   * @param percentage   the percentage of image split for the image transformation.
   */
  void lumaComponentImageSplit(String imageName, String newImageName, double percentage);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param imageName    the name of image to apply transformation.
   * @param newImageName the name of the transformed image.
   * @param percentage   the percentage of image split for the image transformation.
   */
  void intensityComponentImageSplit(String imageName, String newImageName, double percentage);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param imageName    the name of the image to apply color correct.
   * @param newImageName the name of the color corrected image.
   */
  void applyColorCorrectionSplit(String imageName, String newImageName,double percentage);

  /**
   * Provides a split view of the applied image transformation.
   *
   * @param black        the black point for clamping pixels.
   * @param mid          the mid value for clamping pixels.
   * @param white        the white value for clamping pixels.
   * @param imageName    the name of the image to adjust levels.
   * @param newImageName the name of the image with adjusted levels.
   */
  void applyLevelsAdjustmentSplit(int black, int mid, int white, String imageName, String newImageName, double percentage);


}
