package model;

/**
 * Interface for applying image effects to an image.
 */
public interface ImageEffectProcessor {

  /**
   * Applies the specified effect to the given image.
   *
   * @param original the image to which the effect will be applied.
   * @return an image with the effect applied.
   */
  Image applyEffect(Image original);
}
