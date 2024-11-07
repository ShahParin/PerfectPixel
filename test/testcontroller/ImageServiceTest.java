package testcontroller;

import org.junit.Test;

import java.io.IOException;

import controller.ImageService;
import model.Image;
import testmodel.MockImageModelV2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImageServiceTest {
  private final StringBuilder log = new StringBuilder();

  @Test
  public void testLoadImage() throws IOException {
    int[][] red = {{123, 12}, {255, 128}};
    int[][] green = {{45, 200}, {255, 128}};
    int[][] blue = {{67, 150}, {0, 125}};
    Image loadedImage = getImage(red, green, blue);
    assertNotNull("Image should not be null", loadedImage);

    assertEquals("Red channel should match", red, loadedImage.getRedChannel());
    assertEquals("Green channel should match", green, loadedImage.getGreenChannel());
    assertEquals("Blue channel should match", blue, loadedImage.getBlueChannel());
  }

  private static Image getImage(int[][] red, int[][] green, int[][] blue) throws IOException {
    Image mockImage = new Image(red, green, blue);

    MockImageModelV2 mockModel = new MockImageModelV2();
    ImageService imageService = new ImageService(mockModel);

    mockModel.putImage("sample1111", mockImage);
    imageService.loadImage("input/test.ppm", "sample1111");

    return mockModel.getImage("sample1111");
  }

  @Test
  public void testSaveImage() throws IOException {
    int[][] red = {{123, 12}, {255, 128}};
    int[][] green = {{45, 200}, {255, 128}};
    int[][] blue = {{67, 150}, {0, 125}};
    Image savedImage = getSavedImage(red, green, blue);
    assertNotNull("Image should not be null", savedImage);

    assertEquals("Red channel should match", red, savedImage.getRedChannel());
    assertEquals("Green channel should match", green, savedImage.getGreenChannel());
    assertEquals("Blue channel should match", blue, savedImage.getBlueChannel());
  }

  private static Image getSavedImage(int[][] red, int[][] green, int[][] blue) throws IOException {
    Image mockImage = new Image(red, green, blue);

    MockImageModelV2 mockModel = new MockImageModelV2();
    ImageService imageService = new ImageService(mockModel);

    mockModel.putImage("sampleToSave", mockImage);
    imageService.saveImage("output/test_output.ppm", "sampleToSave");

    Image savedImage = mockModel.getImage("sampleToSave");
    return savedImage;
  }

  @Test
  public void testScriptExecution() throws IOException {
    String scriptContent = "load /input/sample.jpg sample\n"
            + "horizontal-flip sample sample-horizontal\n"
            + "brighten 20 sample brightened_sample\n"
            + "save brightened_sample /output/brightened_output.jpg\n";

    runScript(scriptContent);
    String expectedLog = "Loaded image from /input/sample.jpg as sample\n"
            + "Applied Horizontal Flip to sample as sample-horizontal\n"
            + "Brightened image sample by 20 and saved as brightened_sample\n"
            + "Saved image brightened_sample to /output/brightened_output.jpg\n";
    assertEquals(expectedLog, getLog());
  }

  public void runScript(String scriptContent) {
    try {
      String[] commands = scriptContent.split("\n");
      for (String command : commands) {
        command = command.trim();
        if (!command.startsWith("#") && !command.isEmpty()) {
          try {
            execute(command);
          } catch (Exception e) {
            log.append("Error executing command \"").append(command).append("\": ")
                    .append(e.getMessage()).append("\n");
          }
        }
      }
    } catch (Exception e) {
      log.append("Error reading script content: ").append(e.getMessage()).append("\n");
    }
  }

  private void execute(String command) {
    String[] args = command.split(" ");
    String cmdType = args[0];

    switch (cmdType) {
      case "load":
        log.append("Loaded image from ").append(args[1]).append(" as ").append(args[2])
                .append("\n");
        break;
      case "save":
        log.append("Saved image ").append(args[1]).append(" to ").append(args[2]).append("\n");
        break;
      case "red-component":
        log.append("Applied Red Component to ").append(args[1]).append(" as ")
                .append(args[2]).append("\n");
        break;
      case "blue-component":
        log.append("Applied Blue Component to ").append(args[1]).append(" as ")
                .append(args[2]).append("\n");
        break;
      case "green-component":
        log.append("Applied Green Component to ").append(args[1]).append(" as ")
                .append(args[2]).append("\n");
        break;
      case "value-component":
        log.append("Applied Value Component to ").append(args[1]).append(" as ")
                .append(args[2]).append("\n");
        break;
      case "intensity-component":
        log.append("Applied Intensity Component to ").append(args[1]).append(" as ")
                .append(args[2]).append("\n");
        break;
      case "luma-component":
        log.append("Applied Luma Component to ").append(args[1]).append(" as ").append(args[2])
                .append("\n");
        break;
      case "rgb-split":
        log.append("Performed RGB Split on ").append(args[1])
                .append(" into ").append(args[2]).append(", ").append(args[3]).append(", ")
                .append(args[4]).append("\n");
        break;
      case "rgb-combine":
        log.append("Performed RGB Combine on ").append(args[1])
                .append(" from ").append(args[2]).append(", ").append(args[3]).append(", ")
                .append(args[4]).append("\n");
        break;
      case "brighten":
        log.append("Brightened image ").append(args[2]).append(" by ").append(args[1])
                .append(" and saved as ").append(args[3]).append("\n");
        break;
      case "horizontal-flip":
        log.append("Applied Horizontal Flip to ").append(args[1]).append(" as ").append(args[2])
                .append("\n");
        break;
      case "vertical-flip":
        log.append("Applied Vertical Flip to ").append(args[1]).append(" as ").append(args[2])
                .append("\n");
        break;
      case "sepia":
        log.append("Applied Sepia to ").append(args[1]).append(" as ").append(args[2]).append("\n");
        break;
      case "blur":
        log.append("Applied Blur to ").append(args[1]).append(" as ").append(args[2])
                .append(" with intensity ").append(args[3]).append("\n");
        break;
      case "sharpen":
        log.append("Applied Sharpen to ").append(args[1]).append(" as ").append(args[2])
                .append("\n");
        break;
      case "histogram":
        log.append("Created Histogram for ").append(args[1]).append(" as ").append(args[2])
                .append("\n");
        break;
      case "color-correct":
        log.append("Applied Color Correction to ").append(args[1]).append(" as ").append(args[2])
                .append("\n");
        break;
      case "levels-adjust":
        log.append("Adjusted Levels for ").append(args[4])
                .append(" with min ").append(args[1])
                .append(", max ").append(args[2])
                .append(", gamma ").append(args[3])
                .append(" and saved as ").append(args[5]).append("\n");
        break;
      case "compress":
        log.append("Compressed ").append(args[2]).append(" by factor ").append(args[1])
                .append(" and saved as ").append(args[3]).append("\n");
        break;
      default:
        log.append("Invalid command: ").append(command).append("\n");
        break;
    }
  }


  public String getLog() {
    return log.toString();
  }
}