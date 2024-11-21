package testmodel;

import model.ImageModelV3;

/**
 * A Mock implementation of ImageModelV3.
 */
public class MockImageModelV3 extends MockImageModelV2 implements ImageModelV3 {
  @Override
  public void applyPartialImageManipulation(String operationName, String imageName,
                                            String maskImageName, String newImageName) {
    // Log the details of the operation being applied
    log.append("applyPartialImageManipulation called with operation: ")
            .append(operationName).append(", imageName: ").append(imageName)
            .append(", maskImageName: ").append(maskImageName).append(", newImageName: ")
            .append(newImageName).append("\n");
  }
}
