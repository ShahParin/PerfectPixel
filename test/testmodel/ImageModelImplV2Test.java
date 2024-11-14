package testmodel;


import org.junit.Test;

import java.io.IOException;

import controller.ImageService;
import model.Image;
import model.ImageModelImplV2;
import model.ImageModelV2;

import static org.junit.Assert.assertEquals;

/**
 * JUnit class for testing ImageModelImplV2.
 */
public class ImageModelImplV2Test extends ImageModelImplTest {

  private final ImageModelV2 imageModel = new ImageModelImplV2();
  private final ImageService imageService = new ImageService(imageModel);


  @Test
  public void testCompressionZero() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyCompression("sample", "sample-compress-zero", 0);
    Image actualImage = imageModel.getImage("sample-compress-zero");


    int[][] expectedRedChannel = {{122, 12}, {253, 127}};
    int[][] expectedGreenChannel = {{45, 198}, {253, 126}};
    int[][] expectedBlueChannel = {{66, 149}, {0, 123}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test
  public void testCompressionHundred() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyCompression("sample", "sample-compress-hundred", 100);
    Image actualImage = imageModel.getImage("sample-compress-hundred");


    int[][] expectedRedChannel = {{129, 129}, {129, 129}};
    int[][] expectedGreenChannel = {{156, 156}, {156, 156}};
    int[][] expectedBlueChannel = {{84, 84}, {84, 84}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }


  @Test
  public void testCompressionSeventyFive() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyCompression("sample", "sample-compress-seventy-five", 99);
    Image actualImage = imageModel.getImage("sample-compress-seventy-five");


    int[][] expectedRedChannel = {{126, 8}, {249, 131}};
    int[][] expectedGreenChannel = {{45, 198}, {253, 126}};
    int[][] expectedBlueChannel = {{56, 159}, {10, 113}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCompressionNegativeInteger() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyCompression("sample", "sample-compress-negative",
            -9.9);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testCompressionNegativeInteger2() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyCompression("sample", "sample-compress-negative-2",
            150);
  }


  @Test
  public void testBlurHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.blurImageSplit("sample", "sample-blur-half", 50);
    Image actualImage = imageModel.getImage("sample-blur-half");


    int[][] expectedRedChannel = {{0, 12}, {0, 128}};
    int[][] expectedGreenChannel = {{0, 200}, {0, 128}};
    int[][] expectedBlueChannel = {{0, 150}, {0, 125}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());


  }

  @Test
  public void testSepiaHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.sepiaImageSplit("sample", "sample-sepia-half", 50);
    Image actualImage = imageModel.getImage("sample-sepia-half");


    int[][] expectedRedChannel = {{95, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{85, 200}, {255, 128}};
    int[][] expectedBlueChannel = {{66, 150}, {205, 125}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test
  public void testSharpenHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.sharpenImageSplit("sample", "sample-sharpen-half",
            50);
    Image actualImage = imageModel.getImage("sample-sharpen-half");


    int[][] expectedRedChannel = {{0, 12}, {0, 128}};
    int[][] expectedGreenChannel = {{0, 200}, {0, 128}};
    int[][] expectedBlueChannel = {{0, 150}, {0, 125}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test
  public void testLumaHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.lumaComponentImageSplit("sample", "sample-luma-half",
            50);
    Image actualImage = imageModel.getImage("sample-luma-half");


    int[][] expectedRedChannel = {{63, 12}, {236, 128}};
    int[][] expectedGreenChannel = {{63, 200}, {236, 128}};
    int[][] expectedBlueChannel = {{63, 150}, {236, 125}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test
  public void testIntensityHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.intensityComponentImageSplit("sample",
            "sample-intensity-half", 50);
    Image actualImage = imageModel.getImage("sample-intensity-half");


    int[][] expectedRedChannel = {{78, 12}, {170, 128}};
    int[][] expectedGreenChannel = {{78, 200}, {170, 128}};
    int[][] expectedBlueChannel = {{78, 150}, {170, 125}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test
  public void testValueHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.valueComponentImageSplit("sample",
            "sample-value-half", 50);
    Image actualImage = imageModel.getImage("sample-value-half");


    int[][] expectedRedChannel = {{123, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{123, 200}, {255, 128}};
    int[][] expectedBlueChannel = {{123, 150}, {255, 125}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }

  @Test
  public void testColorCorrectionHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyColorCorrectionSplit("sample",
            "sample-color-half", 50);
    Image actualImage = imageModel.getImage("sample-color-half");


    int[][] expectedRedChannel = {{133, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{37, 200}, {247, 128}};
    int[][] expectedBlueChannel = {{64, 150}, {0, 125}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }


  @Test
  public void testLevelAdjustHalf() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustmentSplit(10, 20, 30,
            "sample", "sample-level-half", 50);
    Image actualImage = imageModel.getImage("sample-level-half");


    int[][] expectedRedChannel = {{255, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{255, 200}, {255, 128}};
    int[][] expectedBlueChannel = {{255, 150}, {0, 125}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());

  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSplit() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustmentSplit(10, 20, 30,
            "sample", "sample-level-half", -50);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSplit1() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustmentSplit(10, 20, 30,
            "sample", "sample-level-half", 350);
  }


  @Test
  public void testLevelsAdjust() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustment(20, 128, 240,
            "sample", "sample-level-normal");
    Image actualImage = imageModel.getImage("sample-level-normal");


    int[][] expectedRedChannel = {{122, 0}, {255, 128}};
    int[][] expectedGreenChannel = {{30, 210}, {255, 128}};
    int[][] expectedBlueChannel = {{56, 153}, {0, 124}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testLevelsAdjustMin() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustment(0, 128, 255,
            "sample", "sample-level-min");
    Image actualImage = imageModel.getImage("sample-level-min");


    int[][] expectedRedChannel = {{123, 12}, {255, 128}};
    int[][] expectedGreenChannel = {{45, 200}, {255, 128}};
    int[][] expectedBlueChannel = {{67, 150}, {0, 125}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLevelsAdjustSame() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustment(100, 100, 100,
            "sample", "sample-level-same");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testLevelsAdjustRandom() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustment(50, 30, 255,
            "sample", "sample-level-same");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLevelsAdjustRandom2() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustment(50, 30, 20,
            "sample", "sample-level-same");
  }

  @Test
  public void testLevelsAdjust1() throws IOException {
    imageService.loadImage("inputImages/test.ppm", "sample");
    imageModel.applyLevelsAdjustment(0, 253, 255,
            "sample", "sample-level-1");
    Image actualImage = imageModel.getImage("sample-level-1");


    int[][] expectedRedChannel = {{0, 0}, {255, 0}};
    int[][] expectedGreenChannel = {{0, 0}, {255, 0}};
    int[][] expectedBlueChannel = {{0, 0}, {0, 0}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testColorCorrectionRedTinted() throws IOException {
    imageService.loadImage("inputImages/test-color-red.ppm", "sample");
    imageModel.applyColorCorrection("sample", "sample-corrected");
    Image actualImage = imageModel.getImage("sample-corrected");


    int[][] expectedRedChannel = {{255, 255}, {255, 255}};
    int[][] expectedGreenChannel = {{0, 0}, {0, 0}};
    int[][] expectedBlueChannel = {{0, 0}, {0, 0}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testColorCorrectionGrayscale() throws IOException {
    imageService.loadImage("inputImages/test-color-grayscale.ppm", "sample");
    imageModel.applyColorCorrection("sample", "sample-grayscale-corrected");
    Image actualImage = imageModel.getImage("sample-grayscale-corrected");


    int[][] expectedRedChannel = {{128, 128}, {128, 128}};
    int[][] expectedGreenChannel = {{128, 128}, {128, 128}};
    int[][] expectedBlueChannel = {{128, 128}, {128, 128}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }


  @Test
  public void testColorCorrectionHighPeaks() throws IOException {
    imageService.loadImage("inputImages/test-color-high-peaks.ppm", "sample");
    imageModel.applyColorCorrection("sample", "sample-corrected");
    Image actualImage = imageModel.getImage("sample-corrected");


    int[][] expectedRedChannel = {{0, 0}, {255, 255}};
    int[][] expectedGreenChannel = {{0, 0}, {255, 255}};
    int[][] expectedBlueChannel = {{0, 0}, {255, 255}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

  @Test
  public void testColorCorrectionUniformColor() throws IOException {
    imageService.loadImage("inputImages/test-color-uniform.ppm", "sample");
    imageModel.applyColorCorrection("sample", "sample-corrected");
    Image actualImage = imageModel.getImage("sample-corrected");


    int[][] expectedRedChannel = {{128, 128}, {128, 128}};
    int[][] expectedGreenChannel = {{128, 128}, {128, 128}};
    int[][] expectedBlueChannel = {{128, 128}, {128, 128}};

    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }


  @Test
  public void testColorCorrectionBrightDarkSpots() throws IOException {
    imageService.loadImage("inputImages/test-color-bright-dark-spots.ppm", "sample");
    imageModel.applyColorCorrection("sample", "sample-corrected");
    Image actualImage = imageModel.getImage("sample-corrected");


    int[][] expectedRedChannel = {{0, 255}, {128, 128}};
    int[][] expectedGreenChannel = {{0, 255}, {128, 128}};
    int[][] expectedBlueChannel = {{0, 255}, {128, 128}};


    assertEquals(expectedRedChannel, actualImage.getRedChannel());
    assertEquals(expectedGreenChannel, actualImage.getGreenChannel());
    assertEquals(expectedBlueChannel, actualImage.getBlueChannel());
  }

}