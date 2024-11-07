package controller.commands;

import model.ImageModelV2;

/**
 * Command class to brighten the specified image.
 */
public class BrightenCommand implements Command {
  private final ImageModelV2 imageModel;
  private final int increment;
  private final String imageName;
  private final String newImageName;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel   the image model object.
   * @param imageName    the name of the image.
   * @param newImageName the name of the new image.
   */
  public BrightenCommand(ImageModelV2 imageModel, int increment, String imageName,
                         String newImageName) {
    this.imageModel = imageModel;
    this.increment = increment;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.brightenImage(increment, imageName, newImageName);
  }
}
