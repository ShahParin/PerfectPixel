package controller.commands;

import model.ImageModelV2;

public class GreenComponentCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String imageName;
  private final String newImageName;

  public GreenComponentCommand(ImageModelV2 imageModel, String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyGreenComponent(imageName, newImageName);
  }
}
