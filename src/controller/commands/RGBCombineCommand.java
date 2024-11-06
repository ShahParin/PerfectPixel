package controller.commands;

import model.ImageModelV2;

public class RGBCombineCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String newImageName;
  private final String redImage;
  private final String greenImage;
  private final String blueImage;

  public RGBCombineCommand(ImageModelV2 imageModel, String newImageName, String redImage,
                           String greenImage, String blueImage) {
    this.imageModel = imageModel;
    this.newImageName = newImageName;
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
  }

  @Override
  public void execute() {
    imageModel.rgbCombine(newImageName, redImage, greenImage, blueImage);
  }
}
