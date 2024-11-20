package controller.commands;

import java.io.FileNotFoundException;
import model.ImageModelV3;

/**
 * Command class to extract the luma component with a mask.
 */
public class LumaMaskedCommand implements Command {
  private final ImageModelV3 imageModel;
  private final String imageName;
  private final String newImageName;
  private final String maskedImageName;
  private final String operationName;

  public LumaMaskedCommand(ImageModelV3 imageModel, String operationName, String imageName, String newImageName, String maskedImageName) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
    this.maskedImageName = maskedImageName;
    this.operationName = operationName;
  }

  @Override
  public void execute() throws FileNotFoundException {
    System.out.println("hello from luma masked");
    imageModel.applyPartialImageManipulation(operationName, imageName, newImageName, maskedImageName);
  }
}
