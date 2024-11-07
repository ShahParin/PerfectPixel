package controller.commands;

import model.ImageModelV2;

/**
 * Command class to create the histogram visualization to the specified image.
 */
public class HistogramVisualizationCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String imageName;
  private final String newImageName;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel   the image model object.
   * @param imageName    the name of the image.
   * @param newImageName the name of the new image.
   */
  public HistogramVisualizationCommand(ImageModelV2 imageModel, String imageName,
                                       String newImageName) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyHistogramVisualization(imageName, newImageName);
  }
}
