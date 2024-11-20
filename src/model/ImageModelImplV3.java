package model;

public class ImageModelImplV3 extends ImageModelImplV2 implements ImageModelV3 {

  public ImageModelImplV3() {
    super();
  }
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
        manipulatedImage= ImageOperations.pixelIntensity(original);
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

    // Create new channels for the result
    int[][] resultRed = new int[originalRed.length][originalRed[0].length];
    int[][] resultGreen = new int[originalRed.length][originalRed[0].length];
    int[][] resultBlue = new int[originalRed.length][originalRed[0].length];

    // Iterate through all pixels to selectively apply the manipulation
    for (int i = 0; i < originalRed.length; i++) {
      for (int j = 0; j < originalRed[i].length; j++) {
        // If the mask pixel is black (0), apply manipulation; otherwise, retain the original pixel
        if (maskRed[i][j] == 0) {
          resultRed[i][j] = manipulatedImage.getRedChannel()[i][j];
          resultGreen[i][j] = manipulatedImage.getGreenChannel()[i][j];
          resultBlue[i][j] = manipulatedImage.getBlueChannel()[i][j];
        } else {
          resultRed[i][j] = originalRed[i][j];
          resultGreen[i][j] = original.getGreenChannel()[i][j];
          resultBlue[i][j] = original.getBlueChannel()[i][j];
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
