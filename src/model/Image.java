package model;

/**
 * This is the representation class of any Image that is going to be processed in the entire
 * application. It contains 3 channels, Red, Green and Blue as 2D Arrays.
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
   *
   * @param redChannel   Red Component.
   * @param greenChannel Green Component.
   * @param blueChannel  Blue Component.
   */
  public Image(int[][] redChannel, int[][] greenChannel, int[][] blueChannel) {
    this.redChannel = redChannel;
    this.greenChannel = greenChannel;
    this.blueChannel = blueChannel;
  }

  /**
   * Creates a clone of the provided 2D array(channel).
   *
   * @param originalArray the 2D array/ channel whose clone is to be created.
   * @return the clone of the 2D array/channel.
   */
  private static int[][] copyArray2D(int[][] originalArray) {
    int[][] copyArray = new int[originalArray.length][originalArray[0].length];
    for (int i = 0; i < originalArray.length; i++) {
      copyArray[i] = originalArray[i].clone();
    }

    return copyArray;
  }

  /**
   * Getter for Red Channel.
   *
   * @return Red Channel as 2D Array.
   */
  public int[][] getRedChannel() {
    return copyArray2D(redChannel);
  }

  /**
   * Getter for Green Channel.
   *
   * @return Green Channel as 2D Array.
   */
  public int[][] getGreenChannel() {
    return copyArray2D(greenChannel);
  }

  /**
   * Getter for Blue Channel.
   *
   * @return Blue Channel as 2D Array.
   */
  public int[][] getBlueChannel() {
    return copyArray2D(blueChannel);
  }

  /**
   * Contains logic for clamping Pixel values between 0 and 255.
   * CLAMPING DYNAMIC VALUES
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
}
