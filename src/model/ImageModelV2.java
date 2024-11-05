package model;

public interface ImageModelV2 extends ImageModel{

  void applyCompression( String imageName, String newImageName, double percent);

  void applyHistogramVisualization(String imageName, String newImageName);

  void applyColorCorrection(String imageName, String newImageName);

  void applyLevelsAdjustment(int black, int mid, int white, String imageName, String newImageName);

  void blurImageSplit(String imageName, String newImageName, double percentage);
}
