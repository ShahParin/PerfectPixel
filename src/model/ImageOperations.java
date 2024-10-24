package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import static model.ImageUtil.getDimensions;
import static model.ImageUtil.getNewComponent;

/**
 * This class contains the logic for all the operations that should be performed on the images.
 * Some of the supported operations are filters, transforms, splitting and combining RGB images.
 */
public class ImageOperations {
  /**
   * This function will apply the Sepia transformation to the image.
   *
   * @param image the image to be transformed.
   * @return returns the transformed image.
   */
  protected static Image sepia(Image image) {
    double[][] sepiaKernel = new double[][]{{0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    return ImageUtil.transformationHelper(image, sepiaKernel);
  }

  /**
   * This functions flips the image horizontally.
   *
   * @param image the image to be flipped.
   * @return returns the flipped image.
   */
  protected static Image flipHorizontally(Image image) {
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


  /**
   * This functions flips the image vertically.
   *
   * @param image the image to be flipped.
   * @return returns the flipped image.
   */
  protected static Image flipVertically(Image image) {
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
        newRedChannel[height - i - 1][j] = thisRedChannel[i][j];
        newGreenChannel[height - i - 1][j] = thisGreenChannel[i][j];
        newBlueChannel[height - i - 1][j] = thisBlueChannel[i][j];
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  /**
   * This function brightens/darkens the image.
   *
   * @param image the image to brighten/darken.
   * @param value the increment/decrement to be applied at pixel level.
   * @return returns the transformed image.
   */
  protected static Image brighten(Image image, int value) {
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

  /**
   * This function blurs the image.
   *
   * @param image the image to be blurred.
   * @return returns the blurred image.
   */
  protected static Image blur(Image image) {
    float[][] kernel = {{1 / 16f, 1 / 8f, 1 / 16f}, {1 / 8f, 1 / 4f, 1 / 8f},
            {1 / 16f, 1 / 8f, 1 / 16f}};

    return ImageUtil.filterHelper(image, kernel);
  }

  /**
   * This function sharpens the image.
   *
   * @param image the image to be sharpened.
   * @return returns the sharpened image.
   */
  protected static Image sharpen(Image image) {
    float[][] kernel = {{-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1f, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}};

    return ImageUtil.filterHelper(image, kernel);
  }

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

    sb.append("P3\n").append(width).append(" ").append(height)
            .append("\n").append(255).append("\n");

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

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = image.getRedChannel()[y][x];
        int green = image.getGreenChannel()[y][x];
        int blue = image.getBlueChannel()[y][x];

        int rgb = (red << 16) | (green << 8) | blue;
        outputImage.setRGB(x, y, rgb);
      }
    }

    // Fetch the file extension
    String[] parts = filename.split("\\.");
    String ext = parts[parts.length - 1];
    File outputFile = new File(filename);
    ImageIO.write(outputImage, ext, outputFile);
  }

  /**
   * A function to return the brightness "value" of pixel.
   *
   * @return returns an Image with the "value" of each pixel.
   */
  protected static Image pixelValue(Image image) {
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
  protected static Image pixelIntensity(Image image) {
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
  protected static Image pixelLuma(Image image) {
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

  /**
   * THis function extracts the red component of the image.
   *
   * @param image the image whose red component is to be extracted.
   * @return returns the red component of the image.
   */
  protected static Image extractRedComponent(Image image) {
    return new Image(image.getRedChannel(), image.getRedChannel(), image.getRedChannel());
  }

  /**
   * THis function extracts the green component of the image.
   *
   * @param image the image whose green component is to be extracted.
   * @return returns the green component of the image.
   */
  protected static Image extractGreenComponent(Image image) {
    return new Image(image.getGreenChannel(), image.getGreenChannel(), image.getGreenChannel());
  }

  /**
   * THis function extracts the blue component of the image.
   *
   * @param image the image whose blue component is to be extracted.
   * @return returns the blue component of the image.
   */
  protected static Image extractBlueComponent(Image image) {
    return new Image(image.getBlueChannel(), image.getBlueChannel(), image.getBlueChannel());
  }

  /**
   * THis function extracts the red, green, and blue components of the image.
   *
   * @param image the image whose individual components are to be extracted.
   * @return returns the red, green, and blue components of the image.
   */
  protected static Image[] splitRGB(Image image) {
    return new Image[]{
            extractRedComponent(image),
            extractGreenComponent(image),
            extractBlueComponent(image)
    };
  }

  /**
   * This functions the individual red, green, and blue components into a single image.
   *
   * @param redImage   image with the red component.
   * @param greenImage image with the green component.
   * @param blueImage  image with the blue component.
   * @return returns the combined image.
   */
  protected static Image combineRGB(Image redImage, Image greenImage, Image blueImage) {
    return new Image(redImage.getRedChannel(),
            greenImage.getGreenChannel(),
            blueImage.getBlueChannel());
  }
}
