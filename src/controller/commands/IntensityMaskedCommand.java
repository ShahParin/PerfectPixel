package controller.commands;

import java.io.FileNotFoundException;

import model.ImageModelV3;

/**
 * Command class to extract the intensity component with a mask.
 */
public class IntensityMaskedCommand implements Command {
  private final ImageModelV3 imageModel;
  private final String imageName;
  private final String newImageName;
  private final String maskedImageName;
  private final String operationName;

  /**
   * Constructor to initialize the command object for masked intensity component extraction.
   *
   * @param imageModel      the image model object.
   * @param operationName   the name of the operation to apply.
   * @param imageName       the name of the image to process.
   * @param newImageName    the name of the resulting image after processing.
   * @param maskedImageName the name of the mask image defining the regions to process.
   */
  public IntensityMaskedCommand(ImageModelV3 imageModel, String operationName,
                                String imageName, String newImageName,
                                String maskedImageName) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
    this.maskedImageName = maskedImageName;
    this.operationName = operationName;
  }

  @Override
  public void execute() throws FileNotFoundException {
    System.out.println("hello from intensity masked");
    imageModel.applyPartialImageManipulation(operationName, imageName, newImageName,
            maskedImageName);
  }
}
