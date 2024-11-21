package testmodel;

import org.junit.Test;

import java.io.IOException;

import controller.ImageService;
import model.Image;
import model.ImageModelImplV3;
import model.ImageModelV3;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the ImageModelImplV3 class.
 */
public class ImageModeIImplV3Test extends ImageModelImplV2Test {

  private final ImageModelV3 imageModel = new ImageModelImplV3();
  private final ImageService imageService = new ImageService(imageModel);

  @Test
  public void testPartialImageManipulationBlur() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("blur", "sample",
            "sample-mask", "sample-mask-blur");

    Image actualImage = imageModel.getImage("sample-mask-blur");

    int[][] expectedRedChannel = {{0, 12}, {255, 0}};
    int[][] expectedGreenChannel = {{0, 200}, {255, 0}};
    int[][] expectedBlueChannel = {{0, 150}, {0, 0}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test
  public void testPartialImageManipulationSharpen() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("sharpen", "sample",
            "sample-mask", "sample-mask-sharpen");

    Image actualImage = imageModel.getImage("sample-mask-sharpen");

    int[][] expectedRedChannel = {{0, 12}, {255, 0}};
    int[][] expectedGreenChannel = {{0, 200}, {255, 0}};
    int[][] expectedBlueChannel = {{0, 150}, {0, 0}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testPartialImageManipulationGrayscaleLuma() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("luma-component", "sample",
            "sample-mask", "sample-mask-luma");

    Image actualImage = imageModel.getImage("sample-mask-luma");

    int[][] expectedRedChannel = {{63, 12}, {255, 127}};
    int[][] expectedGreenChannel = {{63, 200}, {255, 127}};
    int[][] expectedBlueChannel = {{63, 150}, {0, 127}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testPartialImageManipulationGrayscaleIntensity() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("intensity-component", "sample",
            "sample-mask", "sample-mask-intensity");

    Image actualImage = imageModel.getImage("sample-mask-intensity");

    int[][] expectedRedChannel = {{78, 12}, {255, 127}};
    int[][] expectedGreenChannel = {{78, 200}, {255, 127}};
    int[][] expectedBlueChannel = {{78, 150}, {0, 127}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testPartialImageManipulationGrayscaleValue() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("value-component", "sample",
            "sample-mask", "sample-mask-value");

    Image actualImage = imageModel.getImage("sample-mask-value");

    int[][] expectedRedChannel = {{123, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{123, 200}, {255, 128}};
    int[][] expectedBlueChannel = {{123, 150}, {0, 128}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testPartialImageManipulationSepia() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("sepia", "sample",
            "sample-mask", "sample-mask-sepia");

    Image actualImage = imageModel.getImage("sample-mask-sepia");

    int[][] expectedRedChannel = {{95, 12}, {255, 172}};
    int[][] expectedGreenChannel = {{85, 200}, {255, 153}};
    int[][] expectedBlueChannel = {{66, 150}, {0, 119}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testPartialImageManipulationRedComponent() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("red-component", "sample",
            "sample-mask", "sample-mask-red");

    Image actualImage = imageModel.getImage("sample-mask-red");

    int[][] expectedRedChannel = {{123, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{123, 200}, {255, 128}};
    int[][] expectedBlueChannel = {{123, 150}, {0, 128}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testPartialImageManipulationGreenComponent() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("green-component", "sample",
            "sample-mask", "sample-mask-green");

    Image actualImage = imageModel.getImage("sample-mask-green");

    int[][] expectedRedChannel = {{45, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{45, 200}, {255, 128}};
    int[][] expectedBlueChannel = {{45, 150}, {0, 128}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testPartialImageManipulationBlueComponent() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("blue-component", "sample",
            "sample-mask", "sample-mask-blue");


    Image actualImage = imageModel.getImage("sample-mask-blue");

    int[][] expectedRedChannel = {{67, 12}, {255, 125}};
    int[][] expectedGreenChannel = {{67, 200}, {255, 125}};
    int[][] expectedBlueChannel = {{67, 150}, {0, 125}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }
}
