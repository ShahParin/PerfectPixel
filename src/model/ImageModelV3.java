package model;

/**
 * Interface for image model functionality with support for applying partial image manipulations
 * using a mask image.
 * Extends the functionality of ImageModelV2.
 */
public interface ImageModelV3 extends ImageModelV2{

  /**
   * Applies a specified image manipulation operation (e.g., blur, sharpen, sepia, etc.) to an image,
   * while using a mask image to selectively apply the operation to certain pixels of the original image.
   * The result is saved as a new image with the provided name.
   *
   * @param operationName the name of the operation to apply (e.g., "blur", "sharpen", "sepia").
   * @param imageName the name of the original image to apply the operation to.
   * @param maskImageName the name of the mask image that determines which pixels to manipulate.
   * @param sourceImageName the name to save the new manipulated image as.
   */
  void applyPartialImageManipulation(String operationName,String imageName, String maskImageName, String sourceImageName);

}
