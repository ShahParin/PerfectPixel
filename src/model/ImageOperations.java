package model;

import util.ImageUtil;

import static util.ImageUtil.getDimensions;
import static util.ImageUtil.getNewComponent;

public class ImageOperations {

  public static Image greyscale(Image image) {
    double[][] greyscaleKernel = new double[][]{{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};
    return ImageUtil.transfromationHelper(image, greyscaleKernel);
  }

  public static Image sepia(Image image) {
    double[][] sepiaKernel = new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    return ImageUtil.transfromationHelper(image, sepiaKernel);
  }

  public static Image flipHorizontally(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][width - j - 1] = thisRedChannel[i][j];
        newGreenChannel[i][width - j - 1] = thisGreenChannel[i][j];
        newBlueChannel[i][width - j - 1] = thisBlueChannel[i][j];
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }


  public static Image flipVertically(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    // Go through each row
    for (int i = 0; i < height; i++) {
      // Go through each column
      for (int j = 0; j < width; j++) {
        // Flip the pixel from the right side
        newRedChannel[height - i - 1][j] = thisRedChannel[i][j];
        newGreenChannel[height - i - 1][j] = thisGreenChannel[i][j];
        newBlueChannel[height - i - 1][j] = thisBlueChannel[i][j];
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image brighten(Image image, int value) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][j] = thisRedChannel[i][j] + value;
        newGreenChannel[i][j] = thisGreenChannel[i][j] + value;
        newBlueChannel[i][j] = thisBlueChannel[i][j] + value;
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image blur(Image image) {
    float[][] kernel = {
            {1 / 16f, 1 / 8f, 1 / 16f},
            {1 / 8f, 1 / 4f, 1 / 8f},
            {1 / 16f, 1 / 8f, 1 / 16f}
    };

    return ImageUtil.filterHelper(image, kernel);
  }

  public static Image sharpen(Image image) {
    float[][] kernel = {
            {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1f, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}
    };

    return ImageUtil.filterHelper(image, kernel);
  }
}
