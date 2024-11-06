package controller.commands;

import model.ImageModelV2;

public class BlurCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String imageName;
  private final String newImageName;
  private final Double percent;


  public BlurCommand(ImageModelV2 imageModel, String imageName, String newImageName, Double percent) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
    this.percent = percent;


  }

  @Override
  public void execute() {
    if (percent != null) {
      imageModel.blurImageSplit(imageName, newImageName, percent);
    }
    else{
      imageModel.blurImage(imageName, newImageName);
    }
  }

}
