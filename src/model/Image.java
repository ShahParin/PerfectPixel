package model;

/**
 * This is the representation class of any Image that is going to be processed in the entire application.
 * It contains 3 channels, Red, Green and Blue as 2D Arrays.
 * It also contains logic for intensity, luma and value for a particular pixel.
 */
public class Image {

  /**
   * This represents each of the channel, namely R G B.
   * Represented as final, so that channels can't be modified later on anyhow.
   */
  private final int[][] redChannel;
  private final int[][] greenChannel;
  private final int[][] blueChannel;

  /**
   * Initiates a new image object given R G B values.
   * @param redChannel Red Component.
   * @param greenChannel Green Component.
   * @param blueChannel Blue Component.
   */
  public Image(int[][] redChannel, int[][] greenChannel, int[][] blueChannel) {
    this.redChannel = redChannel;
    this.greenChannel = greenChannel;
    this.blueChannel = blueChannel;
  }

  /**
   * Getter for Red Channel.
   * @return Red Channel as 2D Array.
   */
  public int[][] getRedChannel() {
    return redChannel;
  }

  /**
   * Getter for Green Channel.
   * @return Green Channel as 2D Array.
   */
  public int[][] getGreenChannel() {
    return greenChannel;
  }

  /**
   * Getter for Blue Channel.
   * @return Blue Channel as 2D Array.
   */
  public int[][] getBlueChannel() {
    return blueChannel;
  }

  /**
   * Contains logic for clamping Pixel values between 0 and 255.
   */
  protected void clamp() {
    for (int i = 0; i < redChannel.length; i++) {
      for (int j = 0; j < redChannel[i].length; j++) {
        if (redChannel[i][j] > 255) {
          redChannel[i][j] = 255;
        }
        if (greenChannel[i][j] > 255) {
          greenChannel[i][j] = 255;
        }
        if (blueChannel[i][j] > 255) {
          blueChannel[i][j] = 255;
        }
        if (redChannel[i][j] < 0) {
          redChannel[i][j] = 0;
        }
        if (greenChannel[i][j] < 0) {
          greenChannel[i][j] = 0;
        }
        if (blueChannel[i][j] < 0) {
          blueChannel[i][j] = 0;
        }
      }
    }
  }

  /**
   * A function to return the brightness "value" of pixel.
   *
   * @return returns an Image with the "value" of each pixel.
   */
  protected Image pixelValue() {
    Image valueImage = new Image(redChannel, greenChannel, blueChannel);

    for (int i = 0; i < redChannel.length; i++) {
      for (int j = 0; j < redChannel[i].length; j++) {
        int newPixelValue = Math.max(this.redChannel[i][j], Math.max(this.greenChannel[i][j], this.blueChannel[i][j]));

        valueImage.redChannel[i][j] = newPixelValue;
        valueImage.greenChannel[i][j] = newPixelValue;
        valueImage.blueChannel[i][j] = newPixelValue;
      }
    }
    return valueImage;
  }

  /**
   * A function to return the intensity of the pixel.
   *
   * @return returns an Image with the intensity of each pixel.
   */
  protected Image pixelIntensity() {
    Image pixelImage = new Image(this.redChannel, this.greenChannel, this.blueChannel);

    for (int i = 0; i < redChannel.length; i++) {
      for (int j = 0; j < redChannel[i].length; j++) {
        int newPixelIntensity = (this.redChannel[i][j] + this.greenChannel[i][j]
                + this.blueChannel[i][j]) / 3;

        pixelImage.redChannel[i][j] = newPixelIntensity;
        pixelImage.greenChannel[i][j] = newPixelIntensity;
        pixelImage.blueChannel[i][j] = newPixelIntensity;
      }
    }
    return pixelImage;
  }

  /**
   * A function to return the Luma of the pixel.
   *
   * @return returns an Image with the Luma of each pixel.
   */
  protected Image pixelLuma() {
    Image lumaImage = new Image(this.redChannel, this.greenChannel, this.blueChannel);

    for (int i = 0; i < redChannel.length; i++) {
      for (int j = 0; j < redChannel[i].length; j++) {
        int newPixelIntensity = (int) (0.2126 * this.redChannel[i][j]
                + 0.7152 * this.greenChannel[i][j] + 0.0722 * this.blueChannel[i][j]);

        lumaImage.redChannel[i][j] = newPixelIntensity;
        lumaImage.greenChannel[i][j] = newPixelIntensity;
        lumaImage.blueChannel[i][j] = newPixelIntensity;
      }
    }
    return lumaImage;
  }
}
