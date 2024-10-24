package util;


import model.Image;

public class ImageUtil {


  public static int[] getDimensions(Image image) {
    int height = image.getRedChannel().length;
    int width = image.getRedChannel()[0].length;
    return new int[]{height, width};
  }

  public static int[][] getNewComponent(int height, int width) {
    return new int[height][width];
  }


  //greyscale and sepia
  public static Image transformationHelper(Image image, double[][] kernel) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][j] = (int) (kernel[0][0] * image.getRedChannel()[i][j] + kernel[0][1] * image.getGreenChannel()[i][j] + kernel[0][2] * image.getBlueChannel()[i][j]);
        newGreenChannel[i][j] = (int) (kernel[1][0] * image.getRedChannel()[i][j] + kernel[1][1] * image.getGreenChannel()[i][j] + kernel[1][2] * image.getBlueChannel()[i][j]);
        newBlueChannel[i][j] = (int) (kernel[2][0] * image.getRedChannel()[i][j] + kernel[2][1] * image.getGreenChannel()[i][j] + kernel[2][2] * image.getBlueChannel()[i][j]);
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image filterHelper(Image image, float[][] kernel) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int x = 1; x < height - 1; x++) {
      for (int y = 1; y < width - 1; y++) {
        float r = 0, g = 0, b = 0;

        // Convolve kernel with the image
        for (int ky = -1; ky <= 1; ky++) {
          for (int kx = -1; kx <= 1; kx++) {
            newRedChannel[x][y] += (int) (thisRedChannel[x + kx][y + ky] * kernel[ky + 1][kx + 1]);
            newGreenChannel[x][y] += (int) (thisGreenChannel[x + kx][y + ky] * kernel[ky + 1][kx + 1]);
            newBlueChannel[x][y] += (int) (thisBlueChannel[x + kx][y + ky] * kernel[ky + 1][kx + 1]);
          }
        }

        // Clamp values to be within the [0, 255] range
//        newRedChannel[x][y] = Math.max((int) r, 0);
//        newGreenChannel[x][y] = Math.max((int) g, 0);
//        newBlueChannel[x][y] = Math.max((int) b, 0);
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }


//  /**
//   * Contains logic for clamping Pixel values between 0 and 255.
//   */
//  public static Image clamp(Image image) {
//    int height = getDimensions(image)[0];
//    int width = getDimensions(image)[1];
//
//    int[][] newRedChannel = getNewComponent(height, width);
//    int[][] newGreenChannel = getNewComponent(height, width);
//    int[][] newBlueChannel = getNewComponent(height, width);
//
//    int[][] thisRedChannel = image.getRedChannel();
//    int[][] thisGreenChannel = image.getGreenChannel();
//    int[][] thisBlueChannel = image.getBlueChannel();
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        if (thisRedChannel[i][j] > 255) {
//          newRedChannel[i][j] = 255;
//        }
//        if (thisGreenChannel[i][j] > 255) {
//          newGreenChannel[i][j] = 255;
//        }
//        if (thisBlueChannel[i][j] > 255) {
//          newBlueChannel[i][j] = 255;
//        }
//        if (thisRedChannel[i][j] < 0) {
//          newRedChannel[i][j] = 0;
//        }
//        if (thisGreenChannel[i][j] < 0) {
//          newGreenChannel[i][j] = 0;
//        }
//        if (thisBlueChannel[i][j] < 0) {
//          newBlueChannel[i][j] = 0;
//        }
//      }
//    }
//    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
//
//  }

}