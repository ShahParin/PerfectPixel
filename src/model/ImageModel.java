package model;

public interface ImageModel {
  void loadImage(String path, String imageName);
  void saveImage(String path, String imageName);

  void applyRedComponent(String imageName, String newImageName);
  void applyGreenComponent(String imageName, String newImageName);
  void applyBlueComponent(String imageName, String newImageName);

  void flipHorizontally(String imageName, String newImageName);
  void flipVertically(String imageName, String newImageName);

  void brightenImage(int increment, String imageName, String newImageName);

  void blurImage(String imageName, String newImageName);
  void sharpenImage(String imageName, String newImageName);

  void applySepia(String imageName, String newImageName);
  void applyGreyscale(String imageName, String newImageName);

  void rgbSplit(String imageName, String redImage, String greenImage, String blueImage);
  void rgbCombine(String newImageName, String redImage, String greenImage, String blueImage);
}
