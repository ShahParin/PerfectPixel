package controller.commands;

import model.ImageModelV2;
import view.ConsoleBasedView;
import view.ImageView;

public class BlurPercentageCommand implements Command {
  private final ImageModelV2 imageModel;
  private final double percent;
  private final String imageName;
  private final String newImageName;

  public BlurPercentageCommand(ImageModelV2 imageModel, String imageName, String newImageName,
                               double percent) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
    this.percent = percent;

  }

  @Override
  public void execute() {
    imageModel.blurImageSplit(imageName, newImageName, percent);

    ImageView imageView = new ConsoleBasedView();
    imageView.printStatements("Image " + imageName + " blurred by " + percent);
  }
}
