package testmodel;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import controller.ImageService;
import model.Image;
import model.ImageModelImplV2;
import model.ImageModelImplV3;
import model.ImageModelV2;
import model.ImageModelV3;

import static org.junit.Assert.assertEquals;

public class ImageModeIImplV3Test extends ImageModelImplV2Test{

  private final ImageModelV3 imageModel = new ImageModelImplV3();
  private final ImageService imageService = new ImageService(imageModel);

  @Test
  public void testPartialImageManipulationBlur() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageService.loadImage("inputImages/test-mask.ppm", "sample-mask");
    imageModel.applyPartialImageManipulation("blur","sample","sample-mask","sample-mask-blur");

    imageService.saveImage("inputImages/test-blur.ppm", "sample-mask-blur");
    Image expectedImage = imageModel.getImage("sample-mask-blur");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("sharpen","sample","sample-mask","sample-mask-sharpen");

    imageService.saveImage("inputImages/test-sharpen.ppm", "sample-mask-sharpen");
    Image expectedImage = imageModel.getImage("sample-mask-sharpen");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("luma-component","sample","sample-mask","sample-mask-luma");

    imageService.saveImage("inputImages/test-luma.ppm", "sample-mask-luma");
    Image expectedImage = imageModel.getImage("sample-mask-luma");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("intensity-component","sample","sample-mask","sample-mask-intensity");

    imageService.saveImage("inputImages/test-intensity.ppm", "sample-mask-intensity");
    Image expectedImage = imageModel.getImage("sample-mask-intensity");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("value-component","sample","sample-mask","sample-mask-value");

    imageService.saveImage("inputImages/test-value.ppm", "sample-mask-value");
    Image expectedImage = imageModel.getImage("sample-mask-value");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("sepia","sample","sample-mask","sample-mask-sepia");

    imageService.saveImage("inputImages/test-sepia.ppm", "sample-mask-sepia");
    Image expectedImage = imageModel.getImage("sample-mask-sepia");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("red-component","sample","sample-mask","sample-mask-red");

//    imageService.saveImage("inputImages/test-red.ppm", "sample-mask-red");
    Image expectedImage = imageModel.getImage("sample-mask-red");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("green-component","sample","sample-mask","sample-mask-green");

//    imageService.saveImage("inputImages/test-green-.ppm", "sample-mask-green");
    Image expectedImage = imageModel.getImage("sample-mask-green");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

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
    imageModel.applyPartialImageManipulation("blue-component","sample","sample-mask","sample-mask-blue");

//    imageService.saveImage("inputImages/test-blue-mask.ppm", "sample-mask-blue");
    Image expectedImage = imageModel.getImage("sample-mask-blue");
    System.out.println("red"+ Arrays.deepToString(expectedImage.getRedChannel()));
    System.out.println("green"+ Arrays.deepToString(expectedImage.getGreenChannel()));
    System.out.println("blue"+ Arrays.deepToString(expectedImage.getBlueChannel()));

    Image actualImage = imageModel.getImage("sample-mask-blue");

    int[][] expectedRedChannel = {{67, 12}, {255, 125}};
    int[][] expectedGreenChannel = {{67, 200}, {255, 125}};
    int[][] expectedBlueChannel = {{67, 150}, {0, 125}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }
}
