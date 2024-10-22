package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;

public class ImageUtil {

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
    int maxValue = sc.nextInt();

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

    int width = image.getRedChannel()[0].length;
    int height = image.getRedChannel().length;

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

  public static Image readJPG(String filename) throws IOException {
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

  public static void saveJPG(String filename, Image image) throws IOException {
    int width = image.getRedChannel()[0].length;
    int height = image.getRedChannel().length;

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

    // Save the image as a JPEG file
    File outputFile = new File(filename);
    ImageIO.write(outputImage, "jpg", outputFile);

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

  public static Image greyscale(Image image) {
    int[][] newRedChannel = new int[image.getRedChannel().length][image.getRedChannel()[0].length];
    int[][] newGreenChannel = new int[image.getGreenChannel().length][image.getGreenChannel()[0].length];
    int[][] newBlueChannel = new int[image.getBlueChannel().length][image.getBlueChannel()[0].length];

    double[][] greyscaleKernel = new double[][]{{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};

    for (int i = 0; i < image.getRedChannel().length; i++) {
      for (int j = 0; j < image.getRedChannel()[i].length; j++) {
        newRedChannel[i][j] = (int) (greyscaleKernel[0][0] * image.getRedChannel()[i][j]
                + greyscaleKernel[0][1] * image.getGreenChannel()[i][j]
                + greyscaleKernel[0][2] * image.getBlueChannel()[i][j]);
        newGreenChannel[i][j] = (int) (greyscaleKernel[1][0] * image.getRedChannel()[i][j]
                + greyscaleKernel[1][1] * image.getGreenChannel()[i][j]
                + greyscaleKernel[1][2] * image.getBlueChannel()[i][j]);
        newBlueChannel[i][j] = (int) (greyscaleKernel[2][0] * image.getRedChannel()[i][j]
                + greyscaleKernel[2][1] * image.getGreenChannel()[i][j]
                + greyscaleKernel[2][2] * image.getBlueChannel()[i][j]);
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image sepia(Image image) {
    int[][] newRedChannel = new int[image.getRedChannel().length][image.getRedChannel()[0].length];
    int[][] newGreenChannel = new int[image.getGreenChannel().length][image.getGreenChannel()[0].length];
    int[][] newBlueChannel = new int[image.getBlueChannel().length][image.getBlueChannel()[0].length];

    double[][] sepiaKernel = new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};

    for (int i = 0; i < image.getRedChannel().length; i++) {
      for (int j = 0; j < image.getRedChannel()[i].length; j++) {
        newRedChannel[i][j] = (int) (sepiaKernel[0][0] * image.getRedChannel()[i][j]
                + sepiaKernel[0][1] * image.getGreenChannel()[i][j]
                + sepiaKernel[0][2] * image.getBlueChannel()[i][j]);
        newGreenChannel[i][j] = (int) (sepiaKernel[1][0] * image.getRedChannel()[i][j]
                + sepiaKernel[1][1] * image.getGreenChannel()[i][j]
                + sepiaKernel[1][2] * image.getBlueChannel()[i][j]);
        newBlueChannel[i][j] = (int) (sepiaKernel[2][0] * image.getRedChannel()[i][j]
                + sepiaKernel[2][1] * image.getGreenChannel()[i][j]
                + sepiaKernel[2][2] * image.getBlueChannel()[i][j]);
      }
    }
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image flipHorizontally(Image image) {
    int height = image.getRedChannel().length;
    int width = image.getRedChannel()[0].length;

    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    // Go through each row
    for (int i = 0; i < height; i++) {
      // Go through each column
      for (int j = 0; j < width; j++) {
        // Flip the pixel from the right side
        newRedChannel[i][width - j - 1] = image.getRedChannel()[i][j];
        newGreenChannel[i][width - j - 1] = image.getGreenChannel()[i][j];
        newBlueChannel[i][width - j - 1] = image.getBlueChannel()[i][j];
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }


  public static Image flipVertically(Image image) {
    int height = image.getRedChannel().length;
    int width = image.getRedChannel()[0].length;

    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    // Go through each row
    for (int i = 0; i < height; i++) {
      // Go through each column
      for (int j = 0; j < width; j++) {
        // Flip the pixel from the right side
        newRedChannel[height-i-1][j] = image.getRedChannel()[i][j];
        newGreenChannel[height-i-1][j] = image.getGreenChannel()[i][j];
        newBlueChannel[height-i-1][j] = image.getBlueChannel()[i][j];
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image brighten(Image image, int value) {
    int height = image.getRedChannel().length;
    int width = image.getRedChannel()[0].length;

    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][j] = Math.min(image.getRedChannel()[i][j] + value, 255);
        newGreenChannel[i][j] = Math.min(image.getGreenChannel()[i][j] + value, 255);
        newBlueChannel[i][j] = Math.min(image.getBlueChannel()[i][j] + value, 255);
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image blur(Image image) {
    int width = image.getRedChannel().length;
    int height = image.getRedChannel()[0].length;

    // Gaussian blur kernel (3x3)
    float[][] kernel = {
            {1 / 16f, 1 / 8f, 1 / 16f},
            {1 / 8f, 1 / 4f, 1 / 8f},
            {1 / 16f, 1 / 8f, 1 / 16f}
    };

    int[][] newRedChannel = new int[width][height];
    int[][] newGreenChannel = new int[width][height];
    int[][] newBlueChannel = new int[width][height];

    // Apply blur to each channel
    for (int y = 1; y < height - 1; y++) {
      for (int x = 1; x < width - 1; x++) {
        float r = 0, g = 0, b = 0;

        // Convolve kernel with the image
        for (int ky = -1; ky <= 1; ky++) {
          for (int kx = -1; kx <= 1; kx++) {
            r += image.getRedChannel()[x + kx][y + ky] * kernel[ky + 1][kx + 1];
            g += image.getGreenChannel()[x + kx][y + ky] * kernel[ky + 1][kx + 1];
            b += image.getBlueChannel()[x + kx][y + ky] * kernel[ky + 1][kx + 1];
          }
        }

        // Clamp values to be within the [0, 255] range
        newRedChannel[x][y] = Math.min(Math.max((int) r, 0), 255);
        newGreenChannel[x][y] = Math.min(Math.max((int) g, 0), 255);
        newBlueChannel[x][y] = Math.min(Math.max((int) b, 0), 255);
      }
    }

    // Return a new Image object with blurred channels
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  public static Image sharpen(Image image) {
    int width = image.getRedChannel().length;
    int height = image.getRedChannel()[0].length;

    // Gaussian blur kernel (3x3)
    float[][] kernel = {
            {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1f, 1 / 4f, -1 / 8f},
            {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
            {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}
    };

    int[][] newRedChannel = new int[width][height];
    int[][] newGreenChannel = new int[width][height];
    int[][] newBlueChannel = new int[width][height];

    // Apply blur to each channel
    for (int y = 1; y < height - 1; y++) {
      for (int x = 1; x < width - 1; x++) {
        float r = 0, g = 0, b = 0;

        // Convolve kernel with the image
        for (int ky = -1; ky <= 1; ky++) {
          for (int kx = -1; kx <= 1; kx++) {
            r += image.getRedChannel()[x + kx][y + ky] * kernel[ky + 1][kx + 1];
            g += image.getGreenChannel()[x + kx][y + ky] * kernel[ky + 1][kx + 1];
            b += image.getBlueChannel()[x + kx][y + ky] * kernel[ky + 1][kx + 1];
          }
        }

        // Clamp values to be within the [0, 255] range
        newRedChannel[x][y] = Math.min(Math.max((int) r, 0), 255);
        newGreenChannel[x][y] = Math.min(Math.max((int) g, 0), 255);
        newBlueChannel[x][y] = Math.min(Math.max((int) b, 0), 255);
      }
    }

    // Return a new Image object with blurred channels
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);  // Placeholder
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
