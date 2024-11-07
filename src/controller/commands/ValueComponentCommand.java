package controller.commands;

import model.ComponentType;
import model.ImageModelV2;

/**
 * Command class to apply the value component to the specified image.
 */
public class ValueComponentCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String imageName;
  private final String newImageName;
  private final Double percent;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel   the image model object.
   * @param imageName    the name of the image.
   * @param newImageName the name of the new image.
   */
  public ValueComponentCommand(ImageModelV2 imageModel, String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
    this.percent = null;

  }
  /**
   * Constructor to initialize the class object for split command.
   *
   * @param imageModel      the image model object.
   * @param imageName       the name of the image.
   * @param newImageName    the name of the new image.
   * @param splitPercentage the percent of split.
   */
  public ValueComponentCommand(ImageModelV2 imageModel, String imageName, String newImageName,
                     double splitPercentage) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
    this.percent = splitPercentage;
  }

  @Override
  public void execute() {
    if (percent != null) {
      imageModel.valueComponentImageSplit(imageName, newImageName, percent);
    } else {
      imageModel.applyComponent(imageName, newImageName, ComponentType.VALUE);
    }
  }
}
