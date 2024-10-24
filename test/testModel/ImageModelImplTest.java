package testModel;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;
import model.ImageModel;
import model.ImageModelImpl;
import util.NothingThereException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ImageModelImplTest {
  private final ImageModel imageModel = new ImageModelImpl();

  private Scanner getImageScanner(String filePath, String imageName) throws IOException {
    Scanner sc = null;
    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + filePath;
    imageModel.loadImage(filePath, imageName);

    try {
      sc = new Scanner(new FileInputStream(pathRelative));
    } catch (FileNotFoundException e) {
      fail("Failed to load image file in load ppm." + e.getMessage());
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.startsWith("#")) {
        builder.append(s).append(System.lineSeparator());
      }
    }

    return new Scanner(builder.toString());
  }


  /**
   * Test loading a medium size ppm image.
   *
   * @throws IOException throws if file not found.
   */
  @Test
  public void testLoadMediumPPM() throws IOException {
    Scanner sc = getImageScanner("input/sample.ppm", "sample");
    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxPixel = sc.nextInt();

    Image image = imageModel.getImage("sample");

    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(redChannel[i][j], sc.nextInt());
        assertEquals(greenChannel[i][j], sc.nextInt());
        assertEquals(blueChannel[i][j], sc.nextInt());
      }
    }
  }

  /**
   * Test loading a small size image.
   */
  @Test
  public void testLoadSmallPPM() throws IOException {
    Scanner sc = getImageScanner("input/single.ppm", "single");

    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxPixel = sc.nextInt();

    Image image = imageModel.getImage("single");

    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(redChannel[i][j], sc.nextInt());
        assertEquals(greenChannel[i][j], sc.nextInt());
        assertEquals(blueChannel[i][j], sc.nextInt());
      }
    }
  }

  /**
   * Test loading different file format, JPG.
   */
  @Test
  public void testLoadJPG() throws IOException {
    imageModel.loadImage("input/sample.jpg", "sample");
    Image expectedImage = imageModel.getImage("sample");
    int[][] redChannel = expectedImage.getRedChannel();
    int[][] greenChannel = expectedImage.getGreenChannel();
    int[][] blueChannel = expectedImage.getBlueChannel();

    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + "input/sample.jpg";
    File inputFile = new File(pathRelative);
    BufferedImage actualImage = ImageIO.read(inputFile);

    int height = actualImage.getHeight();
    int width = actualImage.getWidth();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = actualImage.getRGB(x, y);

        // Extract RGB components
        redChannel[y][x] = (rgb >> 16) & 0xFF;
        greenChannel[y][x] = (rgb >> 8) & 0xFF;
        blueChannel[y][x] = rgb & 0xFF;

        assertEquals(redChannel[y][x], (rgb >> 16) & 0xFF);
        assertEquals(greenChannel[y][x], (rgb >> 8) & 0xFF);
        assertEquals(blueChannel[y][x], rgb & 0xFF);
      }
    }
  }

  /**
   * Test loading different file format, JPEG.
   */
  @Test
  public void testLoadJPEG() throws IOException {
    imageModel.loadImage("input/sample.jpeg", "sample");
    Image expectedImage = imageModel.getImage("sample");
    int[][] redChannel = expectedImage.getRedChannel();
    int[][] greenChannel = expectedImage.getGreenChannel();
    int[][] blueChannel = expectedImage.getBlueChannel();

    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + "input/sample.jpeg";
    File inputFile = new File(pathRelative);
    BufferedImage actualImage = ImageIO.read(inputFile);

    int height = actualImage.getHeight();
    int width = actualImage.getWidth();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = actualImage.getRGB(x, y);

        // Extract RGB components
        assertEquals(redChannel[y][x], (rgb >> 16) & 0xFF);
        assertEquals(greenChannel[y][x], (rgb >> 8) & 0xFF);
        assertEquals(blueChannel[y][x], rgb & 0xFF);
      }
    }
  }


  /**
   * Test loading different file format, PNG.
   */
  @Test
  public void testLoadPNG() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    Image expectedImage = imageModel.getImage("sample");
    int[][] redChannel = expectedImage.getRedChannel();
    int[][] greenChannel = expectedImage.getGreenChannel();
    int[][] blueChannel = expectedImage.getBlueChannel();

    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + "input/sample.png";
    File inputFile = new File(pathRelative);
    BufferedImage actualImage = ImageIO.read(inputFile);

    int height = actualImage.getHeight();
    int width = actualImage.getWidth();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = actualImage.getRGB(x, y);

        // Extract RGB components
        assertEquals(redChannel[y][x], (rgb >> 16) & 0xFF);
        assertEquals(greenChannel[y][x], (rgb >> 8) & 0xFF);
        assertEquals(blueChannel[y][x], rgb & 0xFF);
      }
    }
  }

  /**
   * Test Non-existing invalid path.
   */
  @Test(expected = IOException.class)
  public void testLoadNonExistingPath() throws IOException {
    imageModel.loadImage("sourceimage/sample.png", "sample");
  }

  /**
   * Test Corrupted File.
   */
  @Test(expected = NoSuchElementException.class)
  public void testCorruptedImage() throws IOException {
    imageModel.loadImage("input/sampleCorrupted.ppm", "sample");
  }

  /**
   * Test Unsupported file format .txt.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUnsupportedImage() throws IOException {
    imageModel.loadImage("input/sample.txt", "sample");
  }

  /**
   * Test on empty file.
   */
  @Test(expected = NothingThereException.class)
  public void testNoImage() throws IOException {
    imageModel.loadImage("input/sampleEmpty.ppm", "sample");
  }


  /**
   * Saving ppm
   */
  @Test
  public void testSavingPPM() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    Image expectedImage = imageModel.getImage("sample");
    imageModel.saveImage("output/sample.ppm", "sample");
    imageModel.loadImage("output/sample.ppm", "sampleOutput");
    Image actualImage = imageModel.getImage("sampleOutput");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }


  /**
   * Saving png
   */
  @Test
  public void testSavingPNG() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    Image expectedImage = imageModel.getImage("sample");
    imageModel.saveImage("output/sample.png", "sample");
    imageModel.loadImage("output/sample.png", "sampleOutput");
    Image actualImage = imageModel.getImage("sampleOutput");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Saving jpeg
   */
  @Test
  public void testSavingJPEG() throws IOException {
    imageModel.loadImage("input/sample.jpeg", "sample");
    imageModel.saveImage("output/sample.jpeg", "sample");
    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + "output/sample.jpeg";
    File file = new File(pathRelative);
    assertTrue(file.exists());
  }

  /**
   * Saving jpg
   */
  @Test
  public void testSavingJPG() throws IOException {
    imageModel.loadImage("input/sample.jpg", "sample");
    imageModel.saveImage("output/sample.jpg", "sample");
    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + "output/sample.jpg";
    File file = new File(pathRelative);
    assertTrue(file.exists());
  }

  /**
   * ppm after one operation * all operation.
   */
  @Test
  public void testPPMBlur() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.blurImage("sample", "sample-blur");
    Image actualImage = imageModel.getImage("sample-blur");

    imageModel.loadImage("output/sample-blur-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  @Test
  public void testPPMRedComponent() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applyRedComponent("sample", "sample-red-component");
    Image actualImage = imageModel.getImage("sample-red-component");


    imageModel.loadImage("output/sample-red-component-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMGreenComponent() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applyGreenComponent("sample", "sample-green-component");
    Image actualImage = imageModel.getImage("sample-green-component");


    imageModel.loadImage("output/sample-green-component-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMBlueComponent() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applyBlueComponent("sample", "sample-blue-component");
    Image actualImage = imageModel.getImage("sample-blue-component");


    imageModel.loadImage("output/sample-blue-component-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMValue() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applyValue("sample", "sample-value");
    Image actualImage = imageModel.getImage("sample-value");


    imageModel.loadImage("output/sample-value-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMIntensity() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applyIntensity("sample", "sample-intensity");
    Image actualImage = imageModel.getImage("sample-intensity");


    imageModel.loadImage("output/sample-intensity-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMLuma() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applyLuma("sample", "sample-luma");
    Image actualImage = imageModel.getImage("sample-luma");


    imageModel.loadImage("output/sample-luma-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMRGBSplit() throws IOException {

    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.rgbSplit("sample", "sample-red", "sample-green", "sample-blue");

    Image actualImageRed = imageModel.getImage("sample-red");
    Image actualImageGreen = imageModel.getImage("sample-green");
    Image actualImageBlue = imageModel.getImage("sample-blue");

    imageModel.loadImage("output/sample-red-expected.ppm", "expectedImageRed");
    imageModel.loadImage("output/sample-green-expected.ppm", "expectedImageGreen");
    imageModel.loadImage("output/sample-blue-expected.ppm", "expectedImageBlue");

    Image expectedImageRed = imageModel.getImage("expectedImageRed");
    Image expectedImageGreen = imageModel.getImage("expectedImageGreen");
    Image expectedImageBlue = imageModel.getImage("expectedImageBlue");

    assertEquals(expectedImageRed.getRedChannel(), actualImageRed.getRedChannel());
    assertEquals(expectedImageGreen.getRedChannel(), actualImageGreen.getRedChannel());
    assertEquals(expectedImageBlue.getRedChannel(), actualImageBlue.getRedChannel());


  }

  @Test
  public void testPPMRGBCombine() throws IOException {
    imageModel.loadImage("input/sample-red.ppm", "sample-red");
    imageModel.loadImage("input/sample-green.ppm", "sample-green");
    imageModel.loadImage("input/sample-blue.ppm", "sample-blue");

    imageModel.rgbCombine("sample-rgb-combine","sample-red", "sample-green", "sample-blue");

    Image actualImage = imageModel.getImage("sample-rgb-combine");

    imageModel.loadImage("output/sample.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  @Test
  public void testPPMBrighten() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.brightenImage(20,"sample", "sample-brighten");
    Image actualImage = imageModel.getImage("sample-brighten");

    imageModel.loadImage("output/sample-brighten-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMDarken() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.brightenImage(-40,"sample", "sample-darken");
    Image actualImage = imageModel.getImage("sample-darken");

    imageModel.loadImage("output/sample-darken-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMHorizontal() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.flipHorizontally("sample", "sample-horizontal");
    Image actualImage = imageModel.getImage("sample-horizontal");


    imageModel.loadImage("output/sample-horizontal-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMVertical() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.flipVertically("sample", "sample-vertical");
    Image actualImage = imageModel.getImage("sample-vertical");



    imageModel.loadImage("output/sample-vertical-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMSepia() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applySepia("sample", "sample-sepia");
    Image actualImage = imageModel.getImage("sample-sepia");


    imageModel.loadImage("output/sample-sepia-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPPMSharpen() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.sharpenImage("sample", "sample-sharpen");
    Image actualImage = imageModel.getImage("sample-sharpen");


    imageModel.loadImage("output/sample-sharpen-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }


  /**
   * Saving png after one operation * all operation.
   */
  @Test
  public void testPNGBlur() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.blurImage("sample", "sample-blur");
    Image actualImage = imageModel.getImage("sample-blur");


    imageModel.loadImage("output/sample-blur-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  @Test
  public void testPNGRedComponent() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applyRedComponent("sample", "sample-red-component");
    Image actualImage = imageModel.getImage("sample-red-component");


    imageModel.loadImage("output/sample-red-component-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGGreenComponent() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applyGreenComponent("sample", "sample-green-component");
    Image actualImage = imageModel.getImage("sample-green-component");


    imageModel.loadImage("output/sample-green-component-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGBlueComponent() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applyBlueComponent("sample", "sample-blue-component");
    Image actualImage = imageModel.getImage("sample-blue-component");



    imageModel.loadImage("output/sample-blue-component-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGValue() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applyValue("sample", "sample-value");
    Image actualImage = imageModel.getImage("sample-value");


    imageModel.loadImage("output/sample-value-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGIntensity() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applyIntensity("sample", "sample-intensity");
    Image actualImage = imageModel.getImage("sample-intensity");


    imageModel.loadImage("output/sample-intensity-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGLuma() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applyLuma("sample", "sample-luma");
    Image actualImage = imageModel.getImage("sample-luma");



    imageModel.loadImage("output/sample-luma-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGRGBSplit() throws IOException {

    imageModel.loadImage("input/sample.png", "sample");
    imageModel.rgbSplit("sample", "sample-red", "sample-green", "sample-blue");

    Image actualImageRed = imageModel.getImage("sample-red");
    Image actualImageGreen = imageModel.getImage("sample-green");
    Image actualImageBlue = imageModel.getImage("sample-blue");



    imageModel.loadImage("output/sample-red-expected.png", "expectedImageRed");
    imageModel.loadImage("output/sample-green-expected.png", "expectedImageGreen");
    imageModel.loadImage("output/sample-blue-expected.png", "expectedImageBlue");

    Image expectedImageRed = imageModel.getImage("expectedImageRed");
    Image expectedImageGreen = imageModel.getImage("expectedImageGreen");
    Image expectedImageBlue = imageModel.getImage("expectedImageBlue");

    assertEquals(expectedImageRed.getRedChannel(), actualImageRed.getRedChannel());
    assertEquals(expectedImageGreen.getRedChannel(), actualImageGreen.getRedChannel());
    assertEquals(expectedImageBlue.getRedChannel(), actualImageBlue.getRedChannel());


  }

  @Test
  public void testPNGRGBCombine() throws IOException {

    imageModel.loadImage("input/sample-red.png", "sample-red");
    imageModel.loadImage("input/sample-green.png", "sample-green");
    imageModel.loadImage("input/sample-blue.png", "sample-blue");

    imageModel.rgbCombine("sample-rgb-combine","sample-red", "sample-green", "sample-blue");

    Image actualImage = imageModel.getImage("sample-rgb-combine");



    imageModel.loadImage("output/sample.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  @Test
  public void testPNGHorizontal() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.flipHorizontally("sample", "sample-horizontal");
    Image actualImage = imageModel.getImage("sample-horizontal");



    imageModel.loadImage("output/sample-horizontal-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGVertical() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.flipVertically("sample", "sample-vertical");
    Image actualImage = imageModel.getImage("sample-vertical");



    imageModel.loadImage("output/sample-vertical-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }

  @Test
  public void testPNGSepia() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applySepia("sample", "sample-sepia");
    Image actualImage = imageModel.getImage("sample-sepia");

    imageModel.loadImage("output/sample-sepia-expected.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());

  }


  /**
   * Saving ppm after multiple operation * all operation.
   */
  @Test
  public void testPPMMultiple() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.applySepia("sample", "sample-sepia");
    imageModel.flipVertically("sample-sepia", "sample-vertical");
    imageModel.blurImage("sample-vertical", "sample-blur");
    imageModel.applyLuma("sample-blur", "sample-luma");
    Image actualImage = imageModel.getImage("sample-luma");

    imageModel.loadImage("output/sample-multiple.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Saving png after multiple operation * all operation.
   */
  @Test
  public void testPNGMultiple() throws IOException {
    imageModel.loadImage("input/sample.png", "sample");
    imageModel.applySepia("sample", "sample-sepia");
    imageModel.flipVertically("sample-sepia", "sample-vertical");
    imageModel.blurImage("sample-vertical", "sample-blur");
    imageModel.applyLuma("sample-blur", "sample-luma");
    Image actualImage = imageModel.getImage("sample-luma");

    imageModel.loadImage("output/sample-multiple.png", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Brigthen by maximum increment, 500.
   */
  @Test
  public void testBrigthenMaximum() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.brightenImage(500, "sample", "sample-brighten");
    Image actualImage = imageModel.getImage("sample-brighten");

    imageModel.loadImage("output/sample-brighten-max-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Darken by maximum negative value, -500.
   */
  @Test
  public void testDarkenMaximum() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.brightenImage(-500, "sample", "sample-darken");
    Image actualImage = imageModel.getImage("sample-darken");

    imageModel.loadImage("output/sample-darken-max-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Horizontally flip a rectangle image that is horizontally longer.
   */
  @Test
  public void testHorizontalFlipHLong() throws IOException {
    imageModel.loadImage("input/sampleHLong.ppm", "sample");
    imageModel.flipHorizontally("sample", "sample-hLong");
    Image actualImage = imageModel.getImage("sample-hLong");

    imageModel.loadImage("output/sample-hLong-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }


  /**
   * Horizontally flip a rectangle that is vertically longer.
   */
  @Test
  public void testHorizontalFlipVLong() throws IOException {
    imageModel.loadImage("input/sampleVLong.ppm", "sample");
    imageModel.flipHorizontally("sample", "sample-vLong");
    Image actualImage = imageModel.getImage("sample-vLong");

    imageModel.loadImage("output/sample-vLong-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Horizontally flip a 1 pixel image.
   */
  @Test
  public void testHorizontalSingle() throws IOException {
    imageModel.loadImage("input/single.ppm", "single");
    imageModel.flipHorizontally("single", "single-hFlip");
    Image actualImage = imageModel.getImage("single-hFlip");

    imageModel.loadImage("output/single-hFlip-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Vertically flip a rectangle image that is horizontally longer.
   */
  @Test
  public void testVerticalFlipHLong() throws IOException {
    imageModel.loadImage("input/sampleHLong.ppm", "sample");
    imageModel.flipVertically("sample", "sample-hLong");
    Image actualImage = imageModel.getImage("sample-hLong");

    imageModel.loadImage("output/sample-vertical-hLong-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Vertically flip a rectangle that is vertically longer.
   */
  @Test
  public void testVerticalFlipVLong() throws IOException {
    imageModel.loadImage("input/sampleVLong.ppm", "sample");
    imageModel.flipVertically("sample", "sample-vLong");
    Image actualImage = imageModel.getImage("sample-vLong");

    imageModel.loadImage("output/sample-vertical-vLong-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Vertically flip a 1 pixel image.
   */
  @Test
  public void testVerticalSingle() throws IOException {
    imageModel.loadImage("input/single.ppm", "single");
    imageModel.flipHorizontally("single", "single-vFlip");
    Image actualImage = imageModel.getImage("single-vFlip");

    imageModel.loadImage("output/single-vFlip-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Apply multiple blur operations.
   */
  @Test
  public void testMultipleBlur() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.blurImage("sample", "sample-blur1");
    imageModel.blurImage("sample-blur1", "sample-blur2");
    imageModel.blurImage("sample-blur2", "sample-blur3");
    imageModel.blurImage("sample-blur3", "sample-blur4");
    Image actualImage = imageModel.getImage("sample-blur4");

    imageModel.loadImage("output/sample-multiple-blur-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Test blur on a 1 pixel image.
   */
  @Test
  public void testBlurSingle() throws IOException {
    imageModel.loadImage("input/single.ppm", "single");
    imageModel.flipHorizontally("single", "single-blur");
    Image actualImage = imageModel.getImage("single-blur");

    imageModel.loadImage("output/single-blur-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Apply multiple sharpen operations.
   */
  @Test
  public void testMultipleSharpen() throws IOException {
    imageModel.loadImage("input/sample.ppm", "sample");
    imageModel.sharpenImage("sample", "sample-sharpen1");
    imageModel.sharpenImage("sample-sharpen1", "sample-sharpen2");
    imageModel.sharpenImage("sample-sharpen2", "sample-sharpen3");
    imageModel.sharpenImage("sample-sharpen3", "sample-sharpen4");
    Image actualImage = imageModel.getImage("sample-sharpen4");

    imageModel.saveImage("output/sample-multiple-sharpen-expected.ppm", "sample-sharpen4");
    imageModel.loadImage("output/sample-multiple-sharpen-expected.ppm", "expectedImage");
    Image expectedImage = imageModel.getImage("expectedImage");

    assertEquals(expectedImage.getRedChannel(), actualImage.getRedChannel());
    assertEquals(expectedImage.getGreenChannel(), actualImage.getGreenChannel());
    assertEquals(expectedImage.getBlueChannel(), actualImage.getBlueChannel());
  }

  /**
   * Memory limit check, image size limit.
   */
}
