package testmodel;

import java.io.IOException;

import model.Image;
import model.ImageModel;
import model.ImageModelV2;

public class MockImageModelV2 extends MockImageModel implements ImageModelV2  {

  @Override
  public void applyCompression(String imageName, String newImageName, double percent) {

  }

  @Override
  public void applyHistogramVisualization(String imageName, String newImageName) {

  }

  @Override
  public void applyColorCorrection(String imageName, String newImageName) {

  }

  @Override
  public void applyLevelsAdjustment(int black, int mid, int white, String imageName, String newImageName) {

  }

  @Override
  public void blurImageSplit(String imageName, String newImageName, double percentage) {

  }
}
