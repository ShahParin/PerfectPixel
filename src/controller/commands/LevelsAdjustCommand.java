package controller.commands;

import model.ImageModelV2;

/**
 * Command class to adjust the Reg, Green, and Blue peaks.
 */
public class LevelsAdjustCommand implements Command {
  private final ImageModelV2 imageModel;
  private final int black;
  private final int mid;
  private final int white;
  private final String imageName;
  private final String newImageName;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageModel   the image model object.
   * @param black        the black point for clamping pixels.
   * @param mid          the mid value for clamping pixels.
   * @param white        the white value for clamping pixels.
   * @param imageName    the name of the image.
   * @param newImageName the name of the new image.
   */
  public LevelsAdjustCommand(ImageModelV2 imageModel, int black, int mid, int white,
                             String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.black = black;
    this.mid = mid;
    this.white = white;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyLevelsAdjustment(black, mid, white, imageName, newImageName);
  }
}
