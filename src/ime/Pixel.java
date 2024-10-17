package ime;

/**
 * This class represents a single pixel of an image. A pixel has 3 components - red, green,
 * and blue.
 */
class Pixel {
  protected int red;
  protected int green;
  protected int blue;

  /**
   * A constructor for Pixel that initializes the values for red, green and blue components of the
   * Pixel object.
   *
   * @param red   the red component of the pixel object
   * @param green the green component of the pixel object
   * @param blue  the blue component of the pixel object
   */
  public Pixel(int red, int green, int blue) {
    // Throwing error for invalid input for pixel red.
    if (red > 255 || red < -1) {
      throw new IllegalArgumentException("Pixel red must be between 0 and 255");
    }

    // Throwing error for invalid input for pixel red.
    if (green > 255 || green < -1) {
      throw new IllegalArgumentException("Pixel green must be between 0 and 255");
    }

    // Throwing error for invalid input for pixel red.
    if (blue > 255 || blue < -1) {
      throw new IllegalArgumentException("Pixel blue must be between 0 and 255");
    }

    // Initializing this pixel with the values of red, green, and blue.
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * A getter function to fetch the value of the red component.
   *
   * @return returns the value of red component.
   */
  protected int getRed() {
    return red;
  }

  /**
   * A getter function to fetch the value of the green component.
   *
   * @return returns the value of green component.
   */
  protected int getGreen() {
    return green;
  }

  /**
   * A getter function to fetch the value of the blue component.
   *
   * @return returns the value of blue component.
   */
  protected int getBlue() {
    return blue;
  }

  /**
   * A function to return the brightness "value" of pixel.
   *
   * @return returns the "value" of pixel.
   */
  protected int pixelValue() {
    return Math.max(red, Math.max(green, blue));
  }

  /**
   * A function to return the intensity of the pixel.
   *
   * @return returns the intensity of the pixel.
   */
  protected double pixelIntensity() {
    return (double) (red + green + blue) / 3;
  }

  /**
   * A function to return the Luma of the pixel.
   *
   * @return returns the Luma of the pixel.
   */
  protected double pixelLuma() {
    return 0.2126 * red + 0.7152 * green + 0.0722 * blue;
  }
}
