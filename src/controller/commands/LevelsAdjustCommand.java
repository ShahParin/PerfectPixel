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
  private final Double percent;


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
    this.percent = null;

  }

  /**
   * Constructor to initialize the class object for split command.
   *
   * @param imageModel   the image model object.
   * @param black        the black point for clamping pixels.
   * @param mid          the mid value for clamping pixels.
   * @param white        the white value for clamping pixels.
   * @param imageName    the name of the image.
   * @param newImageName the name of the new image.
   */
  public LevelsAdjustCommand(ImageModelV2 imageModel, int black, int mid, int white,
                             String imageName, String newImageName, double splitPercentage) {
    this.imageModel = imageModel;
    this.black = black;
    this.mid = mid;
    this.white = white;
    this.imageName = imageName;
    this.newImageName = newImageName;
    this.percent = splitPercentage;

  }

  @Override
  public void execute() {
    if (percent != null) {
      imageModel.applyLevelsAdjustmentSplit(black, mid, white, imageName, newImageName,percent);
    } else {
      imageModel.applyLevelsAdjustment(black, mid, white, imageName, newImageName);
    }
  }
}
