package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import util.ImageUtil;


import static util.ImageUtil.getDimensions;
import static util.ImageUtil.getNewComponent;

public class ImageOperations {


  public static Image sepia(Image image) {
    double[][] sepiaKernel = new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    return ImageUtil.transformationHelper(image, sepiaKernel);
  }

  public static Image flipHorizontally(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][width - j - 1] = thisRedChannel[i][j];
        newGreenChannel[i][width - j - 1] = thisGreenChannel[i][j];
        newBlueChannel[i][width - j - 1] = thisBlueChannel[i][j];
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }


  public static Image flipVertically(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    // Go through each row
    for (int i = 0; i < height; i++) {
      // Go through each column
      for (int j = 0; j < width; j++) {
        // Flip the pixel from the right side
        newRedChannel[height - i - 1][j] = thisRedChannel[i][j];
        newGreenChannel[height - i - 1][j] = thisGreenChannel[i][j];
        newBlueChannel[height - i - 1][j] = thisBlueChannel[i][j];
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image brighten(Image image, int value) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][j] = thisRedChannel[i][j] + value;
        newGreenChannel[i][j] = thisGreenChannel[i][j] + value;
        newBlueChannel[i][j] = thisBlueChannel[i][j] + value;
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image blur(Image image) {
    float[][] kernel = {{1 / 16f, 1 / 8f, 1 / 16f}, {1 / 8f, 1 / 4f, 1 / 8f}, {1 / 16f, 1 / 8f, 1 / 16f}};

    return ImageUtil.filterHelper(image, kernel);
  }

  public static Image sharpen(Image image) {
    float[][] kernel = {{-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}, {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f}, {-1 / 8f, 1 / 4f, 1f, 1 / 4f, -1 / 8f}, {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f}, {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}};

    return ImageUtil.filterHelper(image, kernel);
  }

  // Load PPM image from file
  public static Image readPPM(String filename) throws FileNotFoundException {
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

  // Save PPM image to file
  public static void savePPM(String filename, Image image) throws IOException {
    FileOutputStream fos = new FileOutputStream(filename);
    StringBuilder sb = new StringBuilder();

    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    sb.append("P3\n").append(width).append(" ").append(height).append("\n").append(255).append("\n");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sb.append(image.getRedChannel()[i][j]).append(" ")
                .append(image.getGreenChannel()[i][j]).append(" ")
                .append(image.getBlueChannel()[i][j]).append(" ");
      }
      sb.append("\n");
    }

    fos.write(sb.toString().getBytes());
    fos.close();
  }

  public static Image readOther(String filename) throws IOException {
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

        // Extract RGB components
        newRedChannel[y][x] = (rgb >> 16) & 0xFF;
        newGreenChannel[y][x] = (rgb >> 8) & 0xFF;
        newBlueChannel[y][x] = rgb & 0xFF;

      }

    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static void saveOther(String filename, Image image) throws IOException {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Rebuild the RGB values from the channels
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = image.getRedChannel()[y][x];
        int green = image.getGreenChannel()[y][x];
        int blue = image.getBlueChannel()[y][x];

        // Combine RGB values into a single int
        int rgb = (red << 16) | (green << 8) | blue;
        outputImage.setRGB(x, y, rgb);
      }
    }
    String[] parts = filename.split("\\.");
    String ext = parts[parts.length - 1];
    // Save the image as a JPEG file
    File outputFile = new File(filename);
    ImageIO.write(outputImage, ext, outputFile);

  }

  /**
   * A function to return the brightness "value" of pixel.
   *
   * @return returns an Image with the "value" of each pixel.
   */
  public static Image pixelValue(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int newPixelValue = Math.max(thisRedChannel[i][j], Math.max(thisGreenChannel[i][j], thisBlueChannel[i][j]));

        newRedChannel[i][j] = newPixelValue;
        newGreenChannel[i][j] = newPixelValue;
        newBlueChannel[i][j] = newPixelValue;
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  /**
   * A function to return the intensity of the pixel.
   *
   * @return returns an Image with the intensity of each pixel.
   */
  public static Image pixelIntensity(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int newPixelIntensity = (thisRedChannel[i][j] + thisGreenChannel[i][j] + thisBlueChannel[i][j]) / 3;

        newRedChannel[i][j] = newPixelIntensity;
        newGreenChannel[i][j] = newPixelIntensity;
        newBlueChannel[i][j] = newPixelIntensity;
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  /**
   * A function to return the Luma of the pixel.
   *
   * @return returns an Image with the Luma of each pixel.
   */
  public static Image pixelLuma(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] newRedChannel = getNewComponent(height, width);
    int[][] newGreenChannel = getNewComponent(height, width);
    int[][] newBlueChannel = getNewComponent(height, width);

    int[][] thisRedChannel = image.getRedChannel();
    int[][] thisGreenChannel = image.getGreenChannel();
    int[][] thisBlueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int newPixelIntensity = (int) (0.2126 * thisRedChannel[i][j]
                + 0.7152 * thisGreenChannel[i][j] + 0.0722 * thisBlueChannel[i][j]);

        newRedChannel[i][j] = newPixelIntensity;
        newGreenChannel[i][j] = newPixelIntensity;
        newBlueChannel[i][j] = newPixelIntensity;
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  // Various image manipulation methods
  public static Image extractRedComponent(Image image) {
    return new Image(image.getRedChannel(), image.getRedChannel(), image.getRedChannel());
  }

  public static Image extractGreenComponent(Image image) {
    return new Image(image.getGreenChannel(), image.getGreenChannel(), image.getGreenChannel());
  }

  public static Image extractBlueComponent(Image image) {
    return new Image(image.getBlueChannel(), image.getBlueChannel(), image.getBlueChannel());
  }


  public static Image[] splitRGB(Image image) {
    return new Image[]{
            extractRedComponent(image),
            extractGreenComponent(image),
            extractBlueComponent(image)
    };
  }

  public static Image combineRGB(Image redImage, Image greenImage, Image blueImage) {
    return new Image(redImage.getRedChannel(), greenImage.getGreenChannel(), blueImage.getBlueChannel());
  }
}
