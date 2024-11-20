package testview;

import org.junit.Before;
import org.junit.Test;

import testcontroller.MockGUIFeatures;

import static org.junit.Assert.assertEquals;

/**
 * This class is created for testing the View.
 */
public class ImageViewTest {
  private MockImageView mockView;
  MockGUIFeatures mockFeatures;
  MockGUIBasedView mockViewGUI;

  @Before
  public void setUp() {
    System.setProperty("java.awt.headless", "true");

    mockView = new MockImageView();
    mockFeatures = new MockGUIFeatures();
    mockViewGUI = new MockGUIBasedView();
  }

  @Test
  public void testPrintStatements() {
    String message = "Hello, World!";
    mockView.printStatements(message);
    assertEquals("Hello, World!\n", mockView.getLog());
  }

  @Test
  public void testMultiplePrintStatements() {
    mockView.printStatements("First message.");
    mockView.printStatements("Second message.");
    assertEquals("First message.\nSecond message.\n", mockView.getLog());
  }

  @Test
  public void testHandleBlurImage() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("BLUR");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "blur called with imageName: mockImage and newImageName: mockImage_blur\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_blur\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_blur\n" +
            "Mock image blurred.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSharpenImage() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("SHARPEN");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "sharpen called with imageName: mockImage and newImageName: mockImage_sharpen\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_sharpen\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_sharpen\n" +
            "Mock image sharpened.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleHorizontalFlip() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("HORIZONTAL_FLIP");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "horizontalFlip called with imageName: mockImage and newImageName: mockImage_hflip\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_hflip\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_hflip\n" +
            "Mock image flipped horizontally.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleVerticalFlip() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("VERTICAL_FLIP");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "verticalFlip called with imageName: mockImage and newImageName: mockImage_vflip\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_vflip\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_vflip\n" +
            "Mock image flipped vertically.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleGreyscale() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("GRAYSCALE");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "greyscale called with imageName: mockImage and newImageName: mockImage_greyscale\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_greyscale\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_greyscale\n" +
            "Mock image converted to greyscale.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSepia() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("SEPIA");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "sepia called with imageName: mockImage and newImageName: mockImage_sepia\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_sepia\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_sepia\n" +
            "Mock image converted to sepia.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleRedComponent() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("RED_COMPONENT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "redComponent called with imageName: mockImage and newImageName: "
            + "mockImage_redComponent\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_redComponent\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_redComponent\n" +
            "Extracted red component from mock image.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleGreenComponent() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("GREEN_COMPONENT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "greenComponent called with imageName: mockImage and newImageName: "
            + "mockImage_greenComponent\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_greenComponent\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_greenComponent\n" +
            "Extracted green component from mock image.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleBlueComponent() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("BLUE_COMPONENT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "blueComponent called with imageName: mockImage and newImageName: "
            + "mockImage_blueComponent\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_blueComponent\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_blueComponent\n" +
            "Extracted blue component from mock image.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleCompress() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateActionCompress("COMPRESS", 37.543);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "compress called with imageName: mockImage and newImageName: mockImage_compress "
            + "with percent: 37.543\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_compress\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_compress\n" +
            "Mock image compressed by 37.543%.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleLevelsAdjust() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateActionLevelsAdjust("LEVELS_ADJUST", 20, 155, 250);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "levelsAdjust called with imageName: mockImage and newImageName: "
            + "mockImage_levelsAdjust with black: 20, mid: 155, white: 250\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_levelsAdjust\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_levelsAdjust\n" +
            "Mock image levels adjusted by b = 20 m = 155 w = 250.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleColorCorrect() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("COLOR_CORRECT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "colorCorrect called with imageName: mockImage and newImageName:"
            + " mockImage_colorCorrect\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_colorCorrect\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_colorCorrect\n" +
            "Mock image color corrected.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSplitWithBlur() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateSplitOperations("BLUR", 45.54);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "blurSplitOperation called with imageName: mockImage and newImageName: "
            + "mockImage_split_blur with percent: 45.54\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_split_blur\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_blur\n" +
            "Mock image split operation for blur by 45.54%.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSplitWithSharpen() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateSplitOperations("SHARPEN", 45.54);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "sharpenSplitOperation called with imageName: mockImage and newImageName: "
            + "mockImage_split_sharpen with percent: 45.54\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_split_sharpen\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_sharpen\n" +
            "Mock image split operation for sharpen by 45.54%.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSplitWithSepia() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateSplitOperations("SEPIA", 45.54);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "sepiaSplitOperation called with imageName: mockImage and newImageName: "
            + "mockImage_split_sepia with percent: 45.54\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_split_sepia\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_sepia\n" +
            "Mock image split operation for sepia by 45.54%.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSplitWithGreyscale() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateSplitOperations("GREYSCALE", 45.54);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "greyscaleSplitOperation called with imageName: mockImage and newImageName: "
            + "mockImage_split_greyscale with percent: 45.54\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_split_greyscale\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_greyscale\n" +
            "Mock image split operation for greyscale by 45.54%.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSplitWithColorCorrect() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateSplitOperations("COLOR_CORRECT", 45.54);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "colorCorrectionSplitOperation called with imageName: mockImage and newImageName: "
            + "mockImage_split_colorCorrect with percent: 45.54\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_split_colorCorrect\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_colorCorrect\n" +
            "Mock image split operation for color correct by 45.54%.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSplitWithLevelsAdjust() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateOperationSplitLevelsAdjust("LEVELS_ADJUST", 5, 15, 255,
            45.54);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "levelsAdjustmentSplitOperation called with imageName: mockImage and newImageName: "
            + "mockImage_split_levelsAdjust with black: 5.0, mid: 15.0, white: 255.0 with "
            + "percent: 45.54\n";

    String expectedView = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n" +
            "MockGUIBasedView: Image refreshed for mockImage_split_levelsAdjust\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_levelsAdjust\n" +
            "Mock image levels adjusted by b = 5 m = 15 w = 255.\n";

    assertEquals(expected, mockFeatures.getLog());
    assertEquals(expectedView, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithBlur() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.setSaveSplitOperation("BLUR", 55.55);
    mockViewGUI.simulateAction("APPLY_SAVE_SPLIT");

    String expected ="Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n"+
            "MockGUIBasedView: Image refreshed for mockImage_split_blur\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_blur\n" +
            "Mock image split operation for blur by 55.55%.\n" +
            "Image operation saved.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithSharpen() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.setSaveSplitOperation("SHARPEN", 55.55);
    mockViewGUI.simulateAction("APPLY_SAVE_SPLIT");

    String expected = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n"+
            "MockGUIBasedView: Image refreshed for mockImage_split_sharpen\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_sharpen\n" +
            "Mock image split operation for sharpen by 55.55%.\n" +
            "Image operation saved.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithSepia() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.setSaveSplitOperation("SEPIA", 55.55);
    mockViewGUI.simulateAction("APPLY_SAVE_SPLIT");

    String expected = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n"+
            "MockGUIBasedView: Image refreshed for mockImage_split_sepia\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_sepia\n" +
            "Mock image split operation for sepia by 55.55%.\n" +
            "Image operation saved.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithGreyscale() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.setSaveSplitOperation("GREYSCALE", 55.55);
    mockViewGUI.simulateAction("APPLY_SAVE_SPLIT");

    String expected = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n"+
            "MockGUIBasedView: Image refreshed for mockImage_split_greyscale\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_greyscale\n" +
            "Mock image split operation for greyscale by 55.55%.\n" +
            "Image operation saved.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithColorCorrect() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.setSaveSplitOperation("COLOR_CORRECT", 55.55);
    mockViewGUI.simulateAction("APPLY_SAVE_SPLIT");

    String expected = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n"+
            "MockGUIBasedView: Image refreshed for mockImage_split_colorCorrect\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_colorCorrect\n" +
            "Mock image split operation for color correct by 55.55%.\n" +
            "Image operation saved.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithLevelsAdjust() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateOperationSplitLevelsAdjust("LEVELS_ADJUST", 5,15,155,
            55.55);
    mockViewGUI.simulateAction("APPLY_SAVE_SPLIT");

    String expected = "Mock image loaded successfully.\n" +
            "MockGUIBasedView: Image refreshed for mockImage\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage\n"+
            "MockGUIBasedView: Image refreshed for mockImage_split_levelsAdjust\n" +
            "MockGUIBasedView: Histogram refreshed for mockImage_split_levelsAdjust\n" +
            "Mock image levels adjusted by b = 5 m = 15 w = 155.\n" +
            "Mock image split operation failed.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithNoOperationSelected() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("APPLY_SAVE_SPLIT");

    String expected = "No image loaded or features not set.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleSaveSplitWithNoImageLoaded() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("SAVE_SPLIT");

    String expected = "Unknown command: SAVE_SPLIT\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleCloseSplitSuccess() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("APPLY_CLOSE_SPLIT");

    String expected = "No image loaded or features not set.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testHandleCloseSplitWithNoImageLoaded() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("APPLY_CLOSE_SPLIT");

    String expected = "No image loaded or features not set.\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

  @Test
  public void testWrongCommand() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("APPLY_SAVE");

    String expected = "Unknown command: APPLY_SAVE\n";

    assertEquals(expected, mockViewGUI.getOperationLog());
  }

}
