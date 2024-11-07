package controller.commands;

import model.ImageModelV2;

/**
 * CommandClass to compress the specified image.
 */
public class CompressCommand implements Command {
  private final ImageModelV2 imageModel;
  private final double percent;
  private final String imageName;
  private final String newImageName;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel   the image model object.
   * @param percent      the percentage to compress the image.
   * @param imageName    the name of the image.
   * @param newImageName the name of the new image.
   */
  public CompressCommand(ImageModelV2 imageModel, double percent, String imageName,
                         String newImageName) {
    this.imageModel = imageModel;
    this.percent = percent;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyCompression(imageName, newImageName, percent);
  }
}
