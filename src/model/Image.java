package model;

public class Image {
  private int[][] redChannel;
  private int[][] greenChannel;
  private int[][] blueChannel;

  public Image(int[][] redChannel, int[][] greenChannel, int[][] blueChannel) {
    this.redChannel = redChannel;
    this.greenChannel = greenChannel;
    this.blueChannel = blueChannel;
  }

  public int[][] getRedChannel() {
    return redChannel;
  }

  public int[][] getGreenChannel() {
    return greenChannel;
  }

  public int[][] getBlueChannel() {
    return blueChannel;
  }

  public void setRedChannel(int[][] redChannel) {
    this.redChannel = redChannel;
  }

  public void setGreenChannel(int[][] greenChannel) {
    this.greenChannel = greenChannel;
  }

  public void setBlueChannel(int[][] blueChannel) {
    this.blueChannel = blueChannel;
  }

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
