package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;
import model.NothingThereException;

import static model.ImageUtil.getDimensions;

/**
 * Utility class for handling image file operations, such as reading and saving
 * images in various formats (PPM, PNG, JPEG, JPG).
 */
public class ImageFileUtils {
  /**
   * This functions reads the ppm image file.
   *
   * @param filename the filename of the image.
   * @return returns the loaded image.
   * @throws FileNotFoundException throws exception if file not found.
   */
  protected static Image readPPM(String filename) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(filename));

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.startsWith("#")) {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());
    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxPixel = sc.nextInt();

    if (height == 0 && width == 0) {
      throw new NothingThereException("Empty File");
    }

    int[][] redChannel = new int[height][width];
    int[][] greenChannel = new int[height][width];
    int[][] blueChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redChannel[i][j] = sc.nextInt();
        greenChannel[i][j] = sc.nextInt();
        blueChannel[i][j] = sc.nextInt();
      }
    }

    return new Image(redChannel, greenChannel, blueChannel);
  }

  /**
   * THis function saves a loaded image as a ppm file.
   *
   * @param filename the output filename.
   * @param image    the image to be saved.
   * @throws IOException throws error if file is not found.
   */
  protected static void savePPM(String filename, Image image) throws IOException {
    FileOutputStream fos = new FileOutputStream(filename);
    StringBuilder sb = new StringBuilder();

    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    sb.append("P3\n").append(width).append(" ").append(height).append("\n")
            .append(255).append("\n");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sb.append(image.getRedChannel()[i][j]).append(" ").append(image.getGreenChannel()[i][j])
                .append(" ").append(image.getBlueChannel()[i][j]).append(" ");
      }
      sb.append("\n");
    }

    fos.write(sb.toString().getBytes());
    fos.close();
  }

  /**
   * This functions reads the jpg, jpeg or png image file.
   *
   * @param filename the filename of the image.
   * @return returns the loaded image.
   * @throws FileNotFoundException throws exception if file not found.
   */
  protected static Image readOther(String filename) throws IOException {
    File inputFile = new File(filename);
    BufferedImage image = ImageIO.read(inputFile);

    int height = image.getHeight();
    int width = image.getWidth();

    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = image.getRGB(x, y);

        newRedChannel[y][x] = (rgb >> 16) & 0xFF;
        newGreenChannel[y][x] = (rgb >> 8) & 0xFF;
        newBlueChannel[y][x] = rgb & 0xFF;
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  /**
   * This function saves a loaded image as a jpg, jpeg or png file.
   *
   * @param filename the output filename.
   * @param image    the image to be saved.
   * @throws IOException throws error if file is not found.
   */
  protected static void saveOther(String filename, Image image) throws IOException {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = redChannel[y][x];
        int green = greenChannel[y][x];
        int blue = blueChannel[y][x];

        int rgb = (red << 16) | (green << 8) | blue;
        outputImage.setRGB(x, y, rgb);
      }
    }

    String ext = filename.contains(".") ? filename.substring(filename
            .lastIndexOf('.') + 1) : "png";
    File outputFile = new File(filename);
    ImageIO.write(outputImage, ext, outputFile);
  }
}
