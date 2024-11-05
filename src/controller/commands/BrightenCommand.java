package controller.commands;

import model.ImageModelV2;
import view.ConsoleBasedView;
import view.ImageView;

public class BrightenCommand implements Command {
  private final ImageModelV2 imageModel;
  private final int increment;
  private final String imageName;
  private final String newImageName;

  public BrightenCommand(ImageModelV2 imageModel, int increment, String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.increment = increment;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.brightenImage(increment, imageName, newImageName);

    ImageView imageView = new ConsoleBasedView();
    if (increment > 0) {
      imageView.printStatements("Image " + imageName + " brightened by " + increment);
    } else {
      imageView.printStatements("Image " + imageName + " darkened by " + (-increment));
    }
  }
}
