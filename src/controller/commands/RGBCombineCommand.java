package controller.commands;

import model.ImageModelV2;

/**
 * Command class to combine 3images representing Red, Green, and BLue component into a single image.
 */
public class RGBCombineCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String newImageName;
  private final String redImage;
  private final String greenImage;
  private final String blueImage;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel   the image model object.
   * @param newImageName the name of the new image.
   * @param redImage     the name of red component of image.
   * @param greenImage   the name of green component of image.
   * @param blueImage    the name of blue component of image.
   */
  public RGBCombineCommand(ImageModelV2 imageModel, String newImageName, String redImage,
                           String greenImage, String blueImage) {
    this.imageModel = imageModel;
    this.newImageName = newImageName;
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
  }

  @Override
  public void execute() {
    imageModel.rgbCombine(newImageName, redImage, greenImage, blueImage);
  }
}
