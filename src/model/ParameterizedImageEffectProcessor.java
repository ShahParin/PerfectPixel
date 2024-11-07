package model;

public interface ParameterizedImageEffectProcessor {
  // Method specifically for effects needing `b`, `m`, and `w` parameters
  Image applyEffect(Image image, int b, int m, int w);
}
