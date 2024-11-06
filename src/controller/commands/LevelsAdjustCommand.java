package controller.commands;

import model.ImageModelV2;

public class LevelsAdjustCommand implements Command {
  private final ImageModelV2 imageModel;
  private final int black;
  private final int mid;
  private final int white;
  private final String imageName;
  private final String newImageName;

  public LevelsAdjustCommand(ImageModelV2 imageModel, int black, int mid, int white,
                             String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.black = black;
    this.mid = mid;
    this.white = white;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyLevelsAdjustment(black, mid, white, imageName, newImageName);
  }
}
