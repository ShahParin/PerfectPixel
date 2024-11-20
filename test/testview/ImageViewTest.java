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
            + "getImage called with imageName: mockImage\n"
            + "blur called with imageName: mockImage and newImageName: mockImage_blur\n"
            + "getImage called with imageName: mockImage_blur\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleSharpenImage() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("SHARPEN");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "sharpen called with imageName: mockImage and newImageName: mockImage_sharpen\n"
            + "getImage called with imageName: mockImage_sharpen\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleHorizontalFlip() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("HORIZONTAL_FLIP");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "horizontalFlip called with imageName: mockImage and newImageName: mockImage_hflip\n"
            + "getImage called with imageName: mockImage_hflip\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleVerticalFlip() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("VERTICAL_FLIP");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "verticalFlip called with imageName: mockImage and newImageName: mockImage_vflip\n"
            + "getImage called with imageName: mockImage_vflip\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleGreyscale() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("GRAYSCALE");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "greyscale called with imageName: mockImage and newImageName: mockImage_greyscale\n"
            + "getImage called with imageName: mockImage_greyscale\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleSepia() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("SEPIA");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "sepia called with imageName: mockImage and newImageName: mockImage_sepia\n"
            + "getImage called with imageName: mockImage_sepia\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleRedComponent() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("RED_COMPONENT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "redComponent called with imageName: mockImage and newImageName: mockImage_redComponent\n"
            + "getImage called with imageName: mockImage_redComponent\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleGreenComponent() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("GREEN_COMPONENT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "greenComponent called with imageName: mockImage and newImageName: mockImage_greenComponent\n"
            + "getImage called with imageName: mockImage_greenComponent\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleBlueComponent() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("BLUE_COMPONENT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "blueComponent called with imageName: mockImage and newImageName: mockImage_blueComponent\n"
            + "getImage called with imageName: mockImage_blueComponent\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleCompress() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateActionCompress("COMPRESS", 37.543);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "compress called with imageName: mockImage and newImageName: mockImage_compress "
            + "with percent: 37.543\n"
            + "getImage called with imageName: mockImage_compress\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleLevelsAdjust() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateActionLevelsAdjust("LEVELS_ADJUST", 20, 155, 250);

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "levelsAdjust called with imageName: mockImage and newImageName: "
            + "mockImage_levelsAdjust with black: 20, mid: 155, white: 250\n"
            + "getImage called with imageName: mockImage_levelsAdjust\n";

    assertEquals(expected, mockFeatures.getLog());
  }

  @Test
  public void testHandleColorCorrect() {
    mockViewGUI.setFeatures(mockFeatures);

    mockViewGUI.simulateAction("LOAD_IMAGE");
    mockViewGUI.simulateAction("COLOR_CORRECT");

    String expected = "load called with imageName: mockImage for the path: mock/path/to/image.jpg\n"
            + "getImage called with imageName: mockImage\n"
            + "colorCorrect called with imageName: mockImage and newImageName: mockImage_colorCorrect\n"
            + "getImage called with imageName: mockImage_colorCorrect\n";

    assertEquals(expected, mockFeatures.getLog());
  }
}
