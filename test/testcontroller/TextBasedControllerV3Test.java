package testcontroller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageService;
import controller.TextBasedControllerV3;
import testmodel.MockImageModelV2;
import testmodel.MockImageModelV3;
import testview.MockImageView;

import static org.junit.Assert.assertEquals;

/**
 * This class is created for testing the Controller.
 */
public class TextBasedControllerV3Test {
  private MockImageView mockView;
  private MockImageModelV3 mockModelV3;
  private TextBasedControllerV3 controller;

  @Before
  public void setUp() {
    mockView = new MockImageView();
    mockModelV3 = new MockImageModelV3();
    MockImageModelV2 mockModel = new MockImageModelV2();
    ImageService imageService = new ImageService(mockModel);
    controller = new TextBasedControllerV3(mockModelV3, mockModel, mockView, imageService);
  }

  @Test
  public void testApplyPartialImageManipulation() throws IOException {
    String command = "blur originalImage maskImage resultImage";

    controller.execute(command);

    String expectedViewLog = "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "applyPartialImageManipulation called with operation: blur, " +
            "imageName: originalImage, maskImageName: maskImage, newImageName: resultImage\n";
    assertEquals(expectedModelLog, mockModelV3.getLog());

  }

  @Test
  public void testInvalidCommandForPartialImageManipulation() throws IOException {
    String command = "mask-blur originalImage maskImage resultImage";

    controller.execute(command);

    String expectedViewLog = "Invalid command: mask-blur originalImage maskImage resultImage\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

  }


}
