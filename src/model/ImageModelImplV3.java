package model;

/**
 * Implementation of the ImageModelV3 interface, extending the functionality of ImageModelImplV2.
 * This class provides methods for manipulating images using partial image manipulations with masks
 * and various image operations.
 */
public class ImageModelImplV3 extends ImageModelImplV2 implements ImageModelV3 {

  /**
   * Constructs an ImageModelImplV3 instance, initializing the parent class.
   */
  public ImageModelImplV3() {
    super();
  }

  /**
   * Applies a specified image manipulation operation (e.g., blur, sharpen, sepia) to an image,
   * while using a mask image to selectively apply the operation to certain pixels of the original
   * image.
   * The result is saved as a new image with the provided name.
   *
   * @param operationName the name of the operation to apply (e.g., "blur", "sharpen", "sepia").
   * @param imageName     the name of the original image to apply the operation to.
   * @param maskImageName the name of the mask image that determines which pixels to manipulate.
   * @param newImageName  the name to save the new manipulated image as.
   * @throws IllegalArgumentException if the image or mask cannot be found,
   *                                  or if the mask and original image dimensions do not match.
   */
  @Override
  public void applyPartialImageManipulation(String operationName, String imageName, String maskImageName, String newImageName) {
    // Retrieve the original image and mask image
    Image original = images.get(imageName);
    Image mask = images.get(maskImageName);

    if (original == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    if (mask == null) {
      throw new IllegalArgumentException("Mask not found: " + maskImageName);
    }

    // Validate that the mask and image dimensions match
    int[][] originalRed = original.getRedChannel();
    int[][] originalGreen = original.getGreenChannel();
    int[][] originalBlue = original.getBlueChannel();

    int[][] maskRed = mask.getRedChannel();
    if (originalRed.length != maskRed.length || originalRed[0].length != maskRed[0].length) {
      throw new IllegalArgumentException("Mask dimensions do not match the original image dimensions.");
    }

    // Apply the specified operation to the entire image
    Image manipulatedImage;
    switch (operationName.toLowerCase()) {
      case "blur":
        manipulatedImage = ImageOperations.blur(original);
        break;
      case "sharpen":
        manipulatedImage = ImageOperations.sharpen(original);
        break;
      case "sepia":
        manipulatedImage = ImageOperations.sepia(original);
        break;
      case "luma-component":
        manipulatedImage = ImageOperations.pixelLuma(original);
        break;
      case "intensity-component":
        manipulatedImage = ImageOperations.pixelIntensity(original);
        break;
      case "value-component":
        manipulatedImage = ImageOperations.pixelValue(original);
        break;
      case "red-component":
        manipulatedImage = ImageOperations.extractRedComponent(original);
        break;
      case "green-component":
        manipulatedImage = ImageOperations.extractGreenComponent(original);
        break;
      case "blue-component":
        manipulatedImage = ImageOperations.extractBlueComponent(original);
        break;
      default:
        throw new IllegalArgumentException("Unsupported operation: " + operationName);
    }

    // Create channels for the manipulated image
    int[][] manipulatedRed = manipulatedImage.getRedChannel();
    int[][] manipulatedGreen = manipulatedImage.getGreenChannel();
    int[][] manipulatedBlue = manipulatedImage.getBlueChannel();

    // Create new channels for the result
    int[][] resultRed = new int[originalRed.length][originalRed[0].length];
    int[][] resultGreen = new int[originalRed.length][originalRed[0].length];
    int[][] resultBlue = new int[originalRed.length][originalRed[0].length];

    // Iterate through all pixels to selectively apply the manipulation
    for (int i = 0; i < originalRed.length; i++) {
      for (int j = 0; j < originalRed[i].length; j++) {
        // If the mask pixel is black (0), apply manipulation; otherwise, retain the original pixel
        if (maskRed[i][j] == 0) {
          resultRed[i][j] = manipulatedRed[i][j];
          resultGreen[i][j] = manipulatedGreen[i][j];
          resultBlue[i][j] = manipulatedBlue[i][j];
        } else {
          resultRed[i][j] = originalRed[i][j];
          resultGreen[i][j] = originalGreen[i][j];
          resultBlue[i][j] = originalBlue[i][j];
        }
      }
    }

    // Create a new image from the resulting channels
    Image resultImage = new Image(resultRed, resultGreen, resultBlue);
    resultImage.clamp(); // Clamp the values to ensure they are within [0, 255]

    // Save the result image in the image map
    images.put(newImageName, resultImage);
  }
}
