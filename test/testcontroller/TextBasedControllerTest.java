package testcontroller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageService;
import controller.TextBasedController;
import controller.TextBasedControllerV3;
import testmodel.MockImageModelV2;
import testmodel.MockImageModelV3;
import testview.MockImageView;

import static org.junit.Assert.assertEquals;

/**
 * This class is created for testing the Controller.
 */
public class TextBasedControllerTest {
  private MockImageView mockView;
  private MockImageModelV3 mockModelV3;
  private MockImageModelV2 mockModel;
  private TextBasedControllerV3 controller;

  @Before
  public void setUp() {
    mockView = new MockImageView();
    mockModelV3 = new MockImageModelV3();
    mockModel = new MockImageModelV2();
    ImageService imageService = new ImageService(mockModel);
    controller = new TextBasedControllerV3(mockModelV3,mockModel, mockView, imageService);
  }

  @Test
  public void testApplyRedComponent() throws IOException {
    String command = "red-component sample redImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyComponent for RED called with imageName: sample and newImageName: redImage\n";
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
            "applyComponent for GREEN called with imageName: sample and newImageName: greenImage\n";
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
            "applyComponent for BLUE called with imageName: sample and newImageName: blueImage\n";
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
            "applyComponent for VALUE called with imageName: sample and newImageName: valueImage\n";
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
            "applyComponent for INTENSITY called with imageName: sample and "
                    + "newImageName: intensityImage\n";
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
            "applyComponent for LUMA called with imageName: sample and newImageName: lumaImage\n";
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

  @Test
  public void testApplyCompression() throws IOException {
    String command = "compress 50 sample compressedImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyCompression called with imageName: sample and newImageName: compressedImage"
                    + " with percent: 50.0\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyHistogramVisualization() throws IOException {
    String command = "histogram sample histImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyHistogramVisualization called with imageName: sample and newImageName:"
                    + " histImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyColorCorrection() throws IOException {
    String command = "color-correct sample colorCorrectedImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyColorCorrection called with imageName: sample and newImageName: "
                    + "colorCorrectedImage\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testApplyLevelsAdjustment() throws IOException {
    String command = "levels-adjust 0 128 255 sample adjustedImage";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "applyLevelsAdjustment called with imageName: sample and newImageName: adjustedImage"
                    + " with black: 0, mid: 128, white: 255\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testBlurImageSplit() throws IOException {
    String command = "blur sample blurredImage split 50";

    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "blurImageSplit called with imageName: sample and newImageName: blurredImage"
                    + " with percentage: 50.0\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testSharpenImageSplit() throws IOException {
    String command = "sharpen sample sharpenedImage split 75";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "sharpenImageSplit called with imageName: sample and newImageName: sharpenedImage"
                    + " with percentage: 75.0\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testSepiaImageSplit() throws IOException {
    String command = "sepia sample sepiaImage split 50";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "sepiaImageSplit called with imageName: sample and newImageName: sepiaImage"
                    + " with percentage: 50.0\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testValueComponentImageSplit() throws IOException {
    String command = "value-component sample valueImage split 60";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "valueComponentImageSplit called with imageName: sample and newImageName: valueImage"
                    + " with percentage: 60.0\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testLumaComponentImageSplit() throws IOException {
    String command = "luma-component sample lumaImage split 80";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "lumaComponentImageSplit called with imageName: sample and newImageName: lumaImage"
                    + " with percentage: 80.0\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }

  @Test
  public void testIntensityComponentImageSplit() throws IOException {
    String command = "intensity-component sample intensityImage split 90";
    controller.execute(command);

    String expectedViewLog =
            "Successfully executed command: " + command + "\n\n";
    assertEquals(expectedViewLog, mockView.getLog());

    String expectedModelLog =
            "intensityComponentImageSplit called with imageName: sample and newImageName: "
                    + "intensityImage with percentage: 90.0\n";
    assertEquals(expectedModelLog, mockModel.getLog());
  }


}