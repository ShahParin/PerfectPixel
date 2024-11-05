package controller.commands;

import model.ImageModelV2;
import view.ConsoleBasedView;
import view.ImageView;

public class CompressCommand implements Command {
  private final ImageModelV2 imageModel;
  private final double percent;
  private final String imageName;
  private final String newImageName;

  public CompressCommand(ImageModelV2 imageModel, double percent, String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.percent = percent;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyCompression(imageName, newImageName, percent);

    ImageView imageView = new ConsoleBasedView();
    imageView.printStatements("Applied " + percent + "% compression to " + imageName);
  }
}
