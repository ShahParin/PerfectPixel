package model;

/**
 * This is a utilities class for the ImageModel. It abstracts some of the operations to be performed
 * on the images, like fetching the image dimensions, abstracting logic for filters and
 * transformations.
 */
public class ImageUtil {

  /**
   * This function calculates the height and width of the image provided.
   *
   * @param image the image whose width and height are to be calculated.
   * @return returns the width and height of the image.
   */
  public static int[] getDimensions(Image image) {
    int height = image.getRedChannel().length;
    int width = image.getRedChannel()[0].length;
    return new int[]{height, width};
  }

  /**
   * Creates an empty 2D array for the R,G,B channels of the new image being created.
   *
   * @param height the number of rows of the 2D array.
   * @param width  the number of columns of the 2D array.
   * @return returns an empty 2D array.
   */
  protected static int[][] getNewComponent(int height, int width) {
    return new int[height][width];
  }

  /**
   * A helper function for the transformations. This function will perform a matrix multiplication
   * of the image with the provided kernel. This function is used for the sepia transform.
   *
   * @param image  the image to transform.
   * @param kernel the kernel to multiply the image with.
   * @return returns the transformed image.
   */
  protected static Image transformationHelper(Image image, double[][] kernel) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][j] = (int) (kernel[0][0] * image.getRedChannel()[i][j]
                + kernel[0][1] * image.getGreenChannel()[i][j]
                + kernel[0][2] * image.getBlueChannel()[i][j]);
        newGreenChannel[i][j] = (int) (kernel[1][0] * image.getRedChannel()[i][j]
                + kernel[1][1] * image.getGreenChannel()[i][j]
                + kernel[1][2] * image.getBlueChannel()[i][j]);
        newBlueChannel[i][j] = (int) (kernel[2][0] * image.getRedChannel()[i][j]
                + kernel[2][1] * image.getGreenChannel()[i][j]
                + kernel[2][2] * image.getBlueChannel()[i][j]);
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  /**
   * A helper function for the filter operations. This function will apply a filter based on the
   * provided kernel. This function is used for the blur and sharpen filters.
   *
   * @param image  the image on which filter is to be applied.
   * @param kernel the matrix for the filter.
   * @return returns the filtered image
   */
  protected static Image filterHelper(Image image, float[][] kernel) {
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

        for (int ky = -1; ky <= 1; ky++) {
          for (int kx = -1; kx <= 1; kx++) {
            newRedChannel[x][y] += (int) (thisRedChannel[x + kx][y + ky]
                    * kernel[ky + 1][kx + 1]);
            newGreenChannel[x][y] += (int) (thisGreenChannel[x + kx][y + ky]
                    * kernel[ky + 1][kx + 1]);
            newBlueChannel[x][y] += (int) (thisBlueChannel[x + kx][y + ky]
                    * kernel[ky + 1][kx + 1]);
          }
        }
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }
}