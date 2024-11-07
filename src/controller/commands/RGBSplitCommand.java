package controller.commands;

import model.ImageModelV2;

/**
 * Command class to split the specified image into Red, Green, Blue components.
 */
public class RGBSplitCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String imageName;
  private final String redImage;
  private final String greenImage;
  private final String blueImage;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel the image model object.
   * @param imageName  the name of the image.
   * @param redImage   the name of red component of image.
   * @param greenImage the name of green component of image.
   * @param blueImage  the name of blue component of image.
   */
  public RGBSplitCommand(ImageModelV2 imageModel, String imageName, String redImage,
                         String greenImage, String blueImage) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
  }

  @Override
  public void execute() {
    imageModel.rgbSplit(imageName, redImage, greenImage, blueImage);
  }
}
