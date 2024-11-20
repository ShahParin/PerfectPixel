package controller;

import java.io.IOException;

import model.Image;

public interface GUIFeatures {
  void load(String path, String imageName) throws IOException;

  void save(String path, String imageName) throws IOException;

  void blur(String imageName, String newImageName) throws IOException;

  void sharpen(String imageName, String newImageName) throws IOException;

  void horizontalFlip(String imageName, String newImageName) throws IOException;

  void verticalFlip(String imageName, String newImageName) throws IOException;

  void greyscale(String imageName, String newImageName) throws IOException;

  void sepia(String imageName, String newImageName) throws IOException;

  void redComponent(String imageName, String newImageName) throws IOException;

  void greenComponent(String imageName, String newImageName) throws IOException;

  void blueComponent(String imageName, String newImageName) throws IOException;

  void compress(String imageName, String newImageName, double percent) throws IOException;

  void levelsAdjust(String imageName, String newImageName, int black, int mid, int white) throws IOException;

  void colorCorrect(String imageName, String newImageName) throws IOException;

  Image getImage(String imageName);

  Image getHistogram(String imageName);

  void blurSplitOperation(String imageName, String newImageName, double percent) throws IOException;

  void levelsAdjustmentSplitOperation(String currentImageName, String outputImageName, double black, double mid, double white,double percent) throws IOException;

  void sharpenSplitOperation(String currentImageName, String outputImageName, double inputValue)throws IOException;

  void sepiaSplitOperation(String currentImageName, String outputImageName, double inputValue) throws IOException;

  void greyscaleSplitOperation(String currentImageName, String outputImageName, double inputValue) throws IOException;

  void colorCorrectionSplitOperation(String currentImageName, String outputImageName, double inputValue) throws IOException;
}


