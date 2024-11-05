package controller.commands;

import model.ImageModelV2;
import view.ConsoleBasedView;
import view.ImageView;

public class RGBSplitCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String imageName;
  private final String redImage;
  private final String greenImage;
  private final String blueImage;

  public RGBSplitCommand(ImageModelV2 imageModel, String imageName, String redImage, String greenImage, String blueImage) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
  }

  @Override
  public void execute() {
    imageModel.rgbSplit(imageName, redImage, greenImage, blueImage);

    ImageView imageView = new ConsoleBasedView();
    imageView.printStatements("RGB split applied to " + imageName);
  }
}
