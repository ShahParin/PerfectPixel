package controller.commands;

import model.Image;
import model.ImageModelV2;
import view.ConsoleBasedView;
import view.ImageView;

public class ValueComponentCommand implements Command {
  private final ImageModelV2 imageModel;
  private final ImageView view;
  private final String imageName;
  private final String newImageName;

  public ValueComponentCommand(ImageModelV2 imageModel, ImageView view, String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.view = view;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyValue(imageName, newImageName);
    view.printStatements("Created Value component from " + imageName);
  }
}
