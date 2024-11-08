package model;

/**
 * Interface for image effect processors that require additional parameters, black point, mid point,
 * and white point to apply effects on an image.
 */
public interface ParameterizedImageEffectProcessor {
  /**
   * Applies the specified image effect using the provided black point, mid point,
   * and white point parameters.
   *
   * @param image the image to apply the effect on.
   * @param b     the black point.
   * @param m     the mid point.
   * @param w     the white point.
   * @return an image after applying the specified effect.
   */
  Image applyEffect(Image image, int b, int m, int w);
}
