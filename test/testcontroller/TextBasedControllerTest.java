package testcontroller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageService;
import controller.TextBasedController;
import testmodel.MockImageModelV2;
import testview.MockImageView;

import static org.junit.Assert.assertEquals;

/**
 * This class is created for testing the Controller.
 */
public class TextBasedControllerTest {
  private MockImageView mockView;
  private MockImageModelV2 mockModel;
  private TextBasedController controller;
  private ImageService imageService;

  @Before
  public void setUp() {
    mockView = new MockImageView();
    mockModel = new MockImageModelV2();
    imageService = new ImageService(mockModel);
    controller = new TextBasedController(mockModel, mockView, imageService);
  }
//
//  @Test
//  public void testValidLoadCommand() throws IOException {
//    String command = "load /input/sample.jpg sample";
//    controller.execute(command);
//
//    // Verify the messages printed to the view
//    String expectedViewLog =
//            "Successfully executed command: "+command + "\n";
//    assertEquals(expectedViewLog, mockView.getLog());
//
//    // Verify the model method calls
//    String expectedModelLog = "loadImage called with path: sample.jpg and name: sample\n";
//    assertEquals(expectedModelLog, mockModel.getLog());
//
//  }
//
//  @Test
//  public void testValidSaveCommand() throws IOException {
//    String command = "load /input/sample.jpg sample \n save /output/output.jpg sample";
//    controller.execute(command);
//
//    // Verify the messages printed to the view
//    String expectedViewLog =
//            "Successfully executed command: "+command;
//    assertEquals(expectedViewLog, mockView.getLog());
//
//    // Verify the model method calls
//    String expectedModelLog = "saveImage called with path: output.jpg and name: sample\n";
//    assertEquals(expectedModelLog, mockModel.getLog());
//  }

  @Test
  public void testApplyRedComponent() throws IOException {
    String command = "red-component sample redImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyRedComponent called with imageName: sample and newImageName: redImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyGreenComponent() throws IOException {
    String command = "green-component sample greenImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";

    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyGreenComponent called with imageName: sample and newImageName: greenImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyBlueComponent() throws IOException {
    String command = "blue-component sample blueImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";

    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyBlueComponent called with imageName: sample and newImageName: blueImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyValueComponent() throws IOException {
    String command = "value-component sample valueImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyValue called with imageName: sample and newImageName: valueImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyIntensityComponent() throws IOException {
    String command = "intensity-component sample intensityImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyIntensity called with imageName: sample and newImageName: intensityImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyLumaComponent() throws IOException {
    String command = "luma-component sample lumaImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyLuma called with imageName: sample and newImageName: lumaImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testFlipHorizontally() throws IOException {
    String command = "horizontal-flip sample flippedImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "flipHorizontally called with imageName: sample and newImageName: "
            + "flippedImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testFlipVertically() throws IOException {
    String command = "vertical-flip sample flippedImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";

    String expectedModelLog = "flipVertically called with imageName: sample and newImageName: "
            + "flippedImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testBrightenImage() throws IOException {
    String command = "brighten 10 sample brightImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "brightenImage called with imageName: sample and newImageName: "
            + "brightImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testBlurImage() throws IOException {
    String command = "blur sample blurredImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "blurImage called with imageName: sample and newImageName: "
            + "blurredImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testSharpenImage() throws IOException {
    String command = "sharpen sample sharpenedImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "sharpenImage called with imageName: sample and newImageName: "
            + "sharpenedImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplySepia() throws IOException {
    String command = "sepia sample sepiaImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "applySepia called with imageName: sample and newImageName:"
            + " sepiaImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testRGBSplit() throws IOException {
    String command = "rgb-split sample redImage greenImage blueImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "rgbSplit called with imageName: sample and redImageName: "
            + "redImage and greenImage: greenImage and blueImage: blueImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testRGBCombine() throws IOException {
    String command = "rgb-combine combinedImage redImage greenImage blueImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog = "rgbCombine called with newImageName: combinedImage and "
            + "redImage: redImage and greenImage: greenImage and blueImage: blueImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testInvalidCommand() throws IOException {
    String command = "horz-flip sample sample-flipped";  // Invalid command typo
    controller.execute(command);

    // Verify the error message printed to the view
    String expectedLog = "Invalid command: horz-flip sample sample-flipped\n\n";
    assertEquals(expectedLog, mockView.getLog());

  }

  @Test
  public void testCaseInsensitiveCommands() throws IOException {
    String command = "LOAD sample.jpg sample";  // Uppercase command
    controller.execute(command);
    // Verify the messages printed to the view
    String expectedLog = "Invalid command: LOAD sample.jpg sample\n\n";
    assertEquals(expectedLog, mockView.getLog());
  }
}
