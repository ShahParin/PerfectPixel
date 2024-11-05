package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
      {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f}, {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}};

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
        int newPixelValue = Math.max(thisRedChannel[i][j],
                Math.max(thisGreenChannel[i][j], thisBlueChannel[i][j]));

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
        int newPixelIntensity = (thisRedChannel[i][j]
                + thisGreenChannel[i][j] + thisBlueChannel[i][j]) / 3;

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
        int newPixelIntensity = (int) (0.2126 * thisRedChannel[i][j] + 0.7152
                * thisGreenChannel[i][j] + 0.0722 * thisBlueChannel[i][j]);

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
    return new Image[]{extractRedComponent(image), extractGreenComponent(image),
            extractBlueComponent(image)};
  }

  /**
   * This functions the individual red, green, and blue components into a single image.
   *
   * @param redImage   image with the red component.
   * @param greenImage image with the green component.
   * @param blueImage  image with the blue component.
   * @return returns the combined image.
   */
  protected static Image combineRGB(Image redImage, Image greenImage,
                                    Image blueImage) {
    return new Image(redImage.getRedChannel(), greenImage.getGreenChannel(),
            blueImage.getBlueChannel());
  }

  private static List<Double> transform(List<Double> sequence) {
    List<Double> avg = new ArrayList<>();
    List<Double> diff = new ArrayList<>();

    for (int i = 0; i < sequence.size(); i += 2) {
      double thisAvg = (sequence.get(i) + sequence.get(i + 1)) / Math.sqrt(2);
      double thisDiff = (sequence.get(i) - sequence.get(i + 1)) / Math.sqrt(2);

      avg.add(thisAvg);
      diff.add(thisDiff);
    }

    List<Double> result = new ArrayList<>(avg);
    result.addAll(diff);
    return result;
  }

  private static List<Double> invert(List<Double> sequence) {
    List<Double> result = new ArrayList<>();
    int middle = sequence.size() / 2;

    List<Double> avgSequence = sequence.subList(0, middle);
    List<Double> diffSequence = sequence.subList(middle, sequence.size());

    for (int i = 0; i < avgSequence.size(); i++) {
      double thisAvg = (avgSequence.get(i) + diffSequence.get(i)) / Math.sqrt(2);
      double thisDiff = (avgSequence.get(i) - diffSequence.get(i)) / Math.sqrt(2);

      result.add(thisAvg);
      result.add(thisDiff);
    }

    return result;
  }

  private static double[][] convertIntToDouble(int[][] intArray) {
    int rows = intArray.length;
    int cols = intArray[0].length;
    double[][] doubleArray = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        doubleArray[i][j] = (double) intArray[i][j];
      }
    }

    return doubleArray;
  }

  private static int[][] convertDoubleToInt(double[][] doubleArray) {
    int rows = doubleArray.length;
    int cols = doubleArray[0].length;
    int[][] intArray = new int[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        intArray[i][j] = (int) doubleArray[i][j]; // Cast double to int (truncation)
      }
    }

    return intArray;
  }

  protected static int[][] thresholdChannel(double[][] channel, double percent) {
    Set<Double> uniqueIntensities = new HashSet<>();
    for (int i = 0; i < channel.length; i++) {
      for (int j = 0; j < channel[i].length; j++) {
        if (channel[i][j] != 0.0) {
          uniqueIntensities.add(channel[i][j]);
        }
      }
    }
    double[] sortedIntensities = uniqueIntensities.stream().mapToDouble(Double::doubleValue).toArray();
    Arrays.sort(sortedIntensities);

    double thresholdIntensity;
    int resetCount = (int) (sortedIntensities.length * (percent / 100));
    if (resetCount < 1) {
      thresholdIntensity = 0;
    } else {
      thresholdIntensity = sortedIntensities[resetCount - 1];
    }

    for (int i = 0; i < channel.length; i++) {
      for (int j = 0; j < channel[i].length; j++) {
        if (Math.abs(channel[i][j]) < thresholdIntensity) {
          channel[i][j] = 0.0;
        }
      }
    }

    return convertDoubleToInt(channel);
  }

  protected static double[][] haar(int[][] channel) {
    int rows = channel.length;
    int cols = channel[0].length;
    int size = Math.max(rows, cols);

    int padSize = 1;
    while (padSize < size) {
      padSize *= 2;
    }

    double[][] doubleChannel = convertIntToDouble(channel);
    double[][] squareArray = new double[padSize][padSize];
    for (int i = 0; i < rows; i++) {
      System.arraycopy(doubleChannel[i], 0, squareArray[i], 0, cols);
    }

    int currentPadSize = padSize;
    while (currentPadSize > 1) {
      for (int i = 0; i < currentPadSize; i++) {
        List<Double> eachRow = new ArrayList<>();
        for (int j = 0; j < currentPadSize; j++) {
          eachRow.add(squareArray[i][j]);
        }
        List<Double> transformedRow = transform(eachRow);
        for (int j = 0; j < currentPadSize; j++) {
          squareArray[i][j] = transformedRow.get(j);
        }
      }

      for (int j = 0; j < currentPadSize; j++) {
        List<Double> eachCol = new ArrayList<>();
        for (int i = 0; i < currentPadSize; i++) {
          eachCol.add(squareArray[i][j]);
        }
        List<Double> transformedCol = transform(eachCol);
        for (int i = 0; i < currentPadSize; i++) {
          squareArray[i][j] = transformedCol.get(i);
        }
      }

      currentPadSize /= 2;
    }

    double[][] originalChannel = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      System.arraycopy(squareArray[i], 0, originalChannel[i], 0, cols);
    }
    return originalChannel;
  }

  protected static int[][] invertHaar(int[][] channel) {
    int rows = channel.length;
    int cols = channel[0].length;
    int size = Math.max(rows, cols);

    int padSize = 1;
    while (padSize < size) {
      padSize *= 2;
    }

    double[][] doubleChannel = convertIntToDouble(channel);
    double[][] squareArray = new double[padSize][padSize];
    for (int i = 0; i < rows; i++) {
      System.arraycopy(doubleChannel[i], 0, squareArray[i], 0, cols);
    }

    int currentPadSize = 2;
    while (currentPadSize <= padSize) {
      for (int j = 0; j < currentPadSize; j++) {
        List<Double> eachCol = new ArrayList<>();
        for (int i = 0; i < currentPadSize; i++) {
          eachCol.add(squareArray[i][j]);
        }
        List<Double> invertedCol = invert(eachCol);
        for (int i = 0; i < currentPadSize; i++) {
          squareArray[i][j] = invertedCol.get(i);
        }
      }

      for (int i = 0; i < currentPadSize; i++) {
        List<Double> eachRow = new ArrayList<>();
        for (int j = 0; j < currentPadSize; j++) {
          eachRow.add(squareArray[i][j]);
        }
        List<Double> invertedRow = invert(eachRow);
        for (int j = 0; j < currentPadSize; j++) {
          squareArray[i][j] = invertedRow.get(j);
        }
      }

      currentPadSize *= 2;
    }

    double[][] originalChannel = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      System.arraycopy(squareArray[i], 0, originalChannel[i], 0, cols);
    }
    return convertDoubleToInt(originalChannel);
  }

  protected static Image histogramVisualization(Image image) {
    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    int width = getDimensions(image)[1];
    int height = getDimensions(image)[0];

    int[] redHist = new int[256];
    int[] greenHist = new int[256];
    int[] blueHist = new int[256];

    // Populate histogram arrays
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redHist[redChannel[i][j]]++;
        greenHist[greenChannel[i][j]]++;
        blueHist[blueChannel[i][j]]++;
      }
    }

    // Find the maximum frequency across all histograms for consistent scaling
    int max = Math.max(Arrays.stream(redHist).max().orElse(1),
            Math.max(Arrays.stream(greenHist).max().orElse(1),
                    Arrays.stream(blueHist).max().orElse(1)));

    // Create BufferedImage for histogram visualization
    BufferedImage histImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = histImage.createGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 256, 256);

    // Draw histograms for each channel
    drawHistogramLine(g, redHist, Color.RED,max);
    drawHistogramLine(g, greenHist, Color.GREEN,max);
    drawHistogramLine(g, blueHist, Color.BLUE,max);

    g.dispose();

    // Convert the BufferedImage to Image and return it
    return bufferedImageToImage(histImage);
  }

  // Helper function to convert BufferedImage to Image
  private static Image bufferedImageToImage(BufferedImage bufferedImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int[][] redChannel = new int[height][width];
    int[][] greenChannel = new int[height][width];
    int[][] blueChannel = new int[height][width];

    // Extract RGB values and populate channels
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = bufferedImage.getRGB(x, y);
        redChannel[y][x] = (rgb >> 16) & 0xFF;   // Extract red component
        greenChannel[y][x] = (rgb >> 8) & 0xFF;  // Extract green component
        blueChannel[y][x] = rgb & 0xFF;           // Extract blue component
      }
    }
    return new Image(redChannel, greenChannel, blueChannel); // Create Image from channels
  }

  // Helper function for drawing histogram lines
  private static void drawHistogramLine(Graphics2D g, int[] hist, Color color, int max) {
    g.setColor(color);
//    int max = Arrays.stream(hist).max().orElse(1);  // Max frequency for scaling

    for (int i = 0; i < hist.length - 1; i++) {
      int y1 = 256 - (hist[i] * 256 / max);       // Scale frequency to image height
      int y2 = 256 - (hist[i + 1] * 256 / max);
//      g.drawLine(i, y1, i + 1, y2);
      g.drawLine(i, Math.max(0, y1), i + 1, Math.max(0, y2));

    }
  }

  protected static Image colorCorrect(Image image) {
    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    // Find the weighted average peak of each channel in the range 10–245
    int redPeak = findWeightedPeak(redChannel, 10, 245);
    int greenPeak = findWeightedPeak(greenChannel, 10, 245);
    int bluePeak = findWeightedPeak(blueChannel, 10, 245);

    // Calculate the average peak value for alignment
    int avgPeak = (redPeak + greenPeak + bluePeak) / 3;

    // Calculate offsets to align each peak with the average, scaling down to prevent over-adjustment
    // Calculate scaled offsets to avoid over-adjustment
    int redOffset = (avgPeak - redPeak) / 2;    // Scale down by dividing by 2
    int greenOffset = (avgPeak - greenPeak) / 2;
    int blueOffset = (avgPeak - bluePeak) / 2;


    // Adjust the image colors using the scaled offsets
    Image correctedImage = adjustImageColors(image, redOffset, greenOffset, blueOffset);

    // Create and return the histogram image for the corrected image
    return histogramVisualization(correctedImage);
  }

  // Helper function to find a weighted peak intensity in a specific range (10–245)
  private static int findWeightedPeak(int[][] channel, int min, int max) {
    int[] histogram = new int[256];
    int total = 0;
    int weightedSum = 0;

    // Populate histogram for the color channel within the specified range
    for (int[] row : channel) {
      for (int value : row) {
        if (value >= min && value <= max) {
          histogram[value]++;
          weightedSum += value * histogram[value];
          total += histogram[value];
        }
      }
    }

    // Calculate the weighted average of the histogram within the range as the "peak"
    return total == 0 ? min : weightedSum / total;
  }

  // Adjust color channels based on scaled offsets
  private static Image adjustImageColors(Image image, int redOffset, int greenOffset, int blueOffset) {
    int width = getDimensions(image)[1];
    int height = getDimensions(image)[0];

    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    // Adjust each pixel's color component, applying offsets and clamping
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][j] = clamp(image.getRedChannel()[i][j] + redOffset);
        newGreenChannel[i][j] = clamp(image.getGreenChannel()[i][j] + greenOffset);
        newBlueChannel[i][j] = clamp(image.getBlueChannel()[i][j] + blueOffset);
      }
    }

    // Return the new image with adjusted color channels
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }


  protected static Image levelsAdjust(Image image, int black, int mid, int white) {
    // Check if black, mid, white values are within 0–255 and ordered correctly
    if (black >= mid || mid >= white || black < 0 || white > 255) {
      throw new IllegalArgumentException("Invalid levels adjustment values.");
    }

    double[] curve = createLevelsCurve(black, mid, white);

    int[][] redChannel = adjustChannelWithCurve(image.getRedChannel(), curve);
    int[][] greenChannel = adjustChannelWithCurve(image.getGreenChannel(), curve);
    int[][] blueChannel = adjustChannelWithCurve(image.getBlueChannel(), curve);

    return histogramVisualization(new Image(redChannel, greenChannel, blueChannel));
  }

  // Helper function to create a quadratic curve for levels adjustment
  private static double[] createLevelsCurve(int black, int mid, int white) {
    double[] curve = new double[256];

    double slope1 = 128.0 / (mid - black);
    double slope2 = 127.0 / (white - mid);

    for (int i = 0; i < 256; i++) {
      if (i < black) {
        curve[i] = 0;
      } else if (i <= mid) {
        curve[i] = slope1 * (i - black);
      } else if (i <= white) {
        curve[i] = 128 + slope2 * (i - mid);
      } else {
        curve[i] = 255;
      }
    }

    return curve;
  }

  // Helper function to apply levels curve to a channel
  private static int[][] adjustChannelWithCurve(int[][] channel, double[] curve) {
    int height = channel.length;
    int width = channel[0].length;
    int[][] adjustedChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        adjustedChannel[i][j] = clamp(curve[channel[i][j]]);
      }
    }
    return adjustedChannel;
  }

  // Clamping function to ensure values stay within the valid range (0–255)
  private static int clamp(double value) {
    return (int) Math.max(0, Math.min(255, value));
  }

  // Applies blur effect up to a specified percentage of the image width.
  protected static Image applyBlurSplit(Image original, double percentage) {
    int width = getDimensions(original)[1];
    int height = getDimensions(original)[0];

    // Calculate the split line based on the given percentage
    int splitLine = (int) (width * (percentage / 100));

    // Create the blurred version of the original image
    Image blurredImage = blur(original);

    // Initialize new channels for the output image
    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    // Apply the blur effect up to the split line, and keep the original pixels beyond it
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (j < splitLine) {
          // Apply blurred values on the left side of the split line
          newRedChannel[i][j] = blurredImage.getRedChannel()[i][j];
          newGreenChannel[i][j] = blurredImage.getGreenChannel()[i][j];
          newBlueChannel[i][j] = blurredImage.getBlueChannel()[i][j];
        } else {
          // Keep original values on the right side of the split line
          newRedChannel[i][j] = original.getRedChannel()[i][j];
          newGreenChannel[i][j] = original.getGreenChannel()[i][j];
          newBlueChannel[i][j] = original.getBlueChannel()[i][j];
        }
      }
    }

    // Create and return the new image with the split blur effect applied
    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }





}
