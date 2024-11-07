package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    double[][] sepiaKernel = new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
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
    float[][] kernel = {{1 / 16f, 1 / 8f, 1 / 16f},
            {1 / 8f, 1 / 4f, 1 / 8f}, {1 / 16f, 1 / 8f, 1 / 16f}};

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
        int newPixelValue = Math.max(thisRedChannel[i][j], Math.max(thisGreenChannel[i][j],
                thisBlueChannel[i][j]));

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
        int newPixelIntensity = (thisRedChannel[i][j] + thisGreenChannel[i][j]
                + thisBlueChannel[i][j]) / 3;

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
                + 0.7152 * thisGreenChannel[i][j]
                + 0.0722 * thisBlueChannel[i][j]);

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
  protected static Image combineRGB(Image redImage, Image greenImage, Image blueImage) {
    return new Image(redImage.getRedChannel(), greenImage.getGreenChannel(),
            blueImage.getBlueChannel());
  }

  /**
   * Transforms a list by splitting it into average and difference pairs, as part of
   * the Haar compression.
   *
   * @param sequence the list to transform.
   * @return the transformed list.
   */
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

  /**
   * Inverts a transformed list back into its original order.
   *
   * @param sequence the transformed list.
   * @return the inverted list.
   */
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

  /**
   * Converts a 2D array of integers to a 2D array of doubles.
   *
   * @param intArray the 2D array of integers to convert.
   * @return converted 2D as doubles.
   */
  private static double[][] convertIntToDouble(int[][] intArray) {
    int rows = intArray.length;
    int cols = intArray[0].length;
    double[][] doubleArray = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        doubleArray[i][j] = intArray[i][j];
      }
    }

    return doubleArray;
  }

  /**
   * Converts a 2D array of doubles to a 2D array of integers.
   *
   * @param doubleArray the 2D array of doubles to convert.
   * @return converted 2D as integers.
   */
  private static int[][] convertDoubleToInt(double[][] doubleArray) {
    int rows = doubleArray.length;
    int cols = doubleArray[0].length;
    int[][] intArray = new int[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        intArray[i][j] = (int) doubleArray[i][j];
      }
    }

    return intArray;
  }

  /**
   * Pads a 2D array with zeros to make it a square of size power of 2.
   *
   * @param original the original 2D array.
   * @return padded 2D array.
   */
  private static double[][] padArrayToSquare(int[][] original) {
    int rows = original.length;
    int cols = original[0].length;
    int padSize = 1;

    while (padSize < Math.max(rows, cols)) {
      padSize *= 2;
    }

    double[][] squareArray = new double[padSize][padSize];
    for (int i = 0; i < rows; i++) {
      System.arraycopy(convertIntToDouble(original)[i], 0, squareArray[i], 0, cols);
    }

    return squareArray;
  }

  /**
   * Copies a sub-array from a larger padded array to match original dimensions.
   *
   * @param array the padded 2D array.
   * @param rows  original row count.
   * @param cols  original column count.
   * @return sub-array with original dimensions.
   */
  private static double[][] copySubArray(double[][] array, int rows, int cols) {
    double[][] subArray = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      System.arraycopy(array[i], 0, subArray[i], 0, cols);
    }
    return subArray;
  }

  /**
   * Sets the pixel values to 0 based on the provided threshold.
   *
   * @param channel the image channel to apply thresholding on.
   * @param percent the percentage of thresholding to apply.
   * @return the thresholded channel.
   */
  protected static int[][] thresholdChannel(double[][] channel, double percent) {
    Set<Double> uniqueIntensities = new HashSet<>();
    for (int i = 0; i < channel.length; i++) {
      for (int j = 0; j < channel[i].length; j++) {
        if (channel[i][j] != 0.0) {
          uniqueIntensities.add(channel[i][j]);
        }
      }
    }
    double[] sortedIntensities = uniqueIntensities.stream().mapToDouble(Double::doubleValue)
            .toArray();
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

  /**
   * Applies the transformation on each row or column within the specified pad size.
   *
   * @param array     The array to transform.
   * @param padSize   The current size of the padded section to transform.
   * @param isForward Whether to apply the forward (true) or inverse (false) transform.
   * @param isRow     Whether to apply the transformation on rows (true) or columns (false).
   */
  private static void applyTransform(double[][] array, int padSize, boolean isForward,
                                     boolean isRow) {
    for (int index = 0; index < padSize; index++) {
      double[] elements = new double[padSize];

      for (int inner = 0; inner < padSize; inner++) {
        elements[inner] = isRow ? array[index][inner] : array[inner][index];
      }

      List<Double> elementsList = new ArrayList<>();
      for (double value : elements) {
        elementsList.add(value);
      }

      List<Double> transformedList = isForward ? transform(elementsList) : invert(elementsList);

      for (int inner = 0; inner < padSize; inner++) {
        elements[inner] = transformedList.get(inner);
      }

      for (int inner = 0; inner < padSize; inner++) {
        if (isRow) {
          array[index][inner] = elements[inner];
        } else {
          array[inner][index] = elements[inner];
        }
      }
    }
  }

  /**
   * Applies the Haar transformation on the provided channel.
   *
   * @param channel the channel to apply the Haar transform on.
   * @return the transformed channel.
   */
  protected static double[][] haar(int[][] channel) {
    double[][] squareArray = padArrayToSquare(channel);

    int currentPadSize = squareArray.length;
    while (currentPadSize > 1) {
      applyTransform(squareArray, currentPadSize, true, true);
      applyTransform(squareArray, currentPadSize, true, false);

      currentPadSize /= 2;
    }

    return copySubArray(squareArray, channel.length, channel[0].length);
  }

  /**
   * Inverts the Haar transformation on the provided channel.
   *
   * @param channel the channel to apply the inversion on.
   * @return the inverted channel.
   */
  protected static int[][] invertHaar(int[][] channel) {
    double[][] squareArray = padArrayToSquare(channel);

    int currentPadSize = 2;
    while (currentPadSize <= squareArray.length) {
      applyTransform(squareArray, currentPadSize, false, false);
      applyTransform(squareArray, currentPadSize, false, true);

      currentPadSize *= 2;
    }

    return convertDoubleToInt(copySubArray(squareArray, channel.length, channel[0].length));
  }

  /**
   * Creates the histogram of the provided image.
   *
   * @param image the image whose histogram is to be created.
   * @return the image with the histogram.
   */
  protected static Image histogramVisualization(Image image) {
    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    int width = getDimensions(image)[1];
    int height = getDimensions(image)[0];

    int[] redHist = new int[256];
    int[] greenHist = new int[256];
    int[] blueHist = new int[256];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redHist[redChannel[i][j]]++;
        greenHist[greenChannel[i][j]]++;
        blueHist[blueChannel[i][j]]++;
      }
    }

    int max = Math.max(Arrays.stream(redHist).max().orElse(1),
            Math.max(Arrays.stream(greenHist).max().orElse(1),
                    Arrays.stream(blueHist).max().orElse(1)));

    BufferedImage histImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = histImage.createGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 256, 256);

    drawHistogramLine(g, redHist, Color.RED, max);
    drawHistogramLine(g, greenHist, Color.GREEN, max);
    drawHistogramLine(g, blueHist, Color.BLUE, max);

    g.dispose();

    return bufferedImageToImage(histImage);
  }

  /**
   * Helper function to convert BufferedImage to Image.
   *
   * @param bufferedImage the buffered image to convert.
   * @return the image of type Image.
   */
  private static Image bufferedImageToImage(BufferedImage bufferedImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int[][] redChannel = new int[height][width];
    int[][] greenChannel = new int[height][width];
    int[][] blueChannel = new int[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = bufferedImage.getRGB(x, y);
        redChannel[y][x] = (rgb >> 16) & 0xFF;
        greenChannel[y][x] = (rgb >> 8) & 0xFF;
        blueChannel[y][x] = rgb & 0xFF;
      }
    }
    return new Image(redChannel, greenChannel, blueChannel);
  }

  /**
   * Helper function for drawing histogram lines.
   *
   * @param g     the Graphics2D object used to draw the histogram line.
   * @param hist  the histogram data representing frequency values for each intensity level.
   * @param color the color used to draw the histogram line.
   * @param max   the maximum frequency value for the histogram height
   */
  private static void drawHistogramLine(Graphics2D g, int[] hist, Color color, int max) {
    g.setColor(color);

    for (int i = 0; i < hist.length - 1; i++) {
      int y1 = 256 - (hist[i] * 256 / max);
      int y2 = 256 - (hist[i + 1] * 256 / max);
      g.drawLine(i, Math.max(0, y1), i + 1, Math.max(0, y2));
    }
  }

  /**
   * Performs color correction on an image by adjusting each color channel.
   *
   * @param image the image to color correct.
   * @return returns the color corrected image.
   */
  protected static Image colorCorrect(Image image) {
    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    int redPeak = findWeightedPeak(redChannel, 10, 245);
    int greenPeak = findWeightedPeak(greenChannel, 10, 245);
    int bluePeak = findWeightedPeak(blueChannel, 10, 245);

    int avgPeak = (redPeak + greenPeak + bluePeak) / 3;

    int redOffset = (avgPeak - redPeak) / 2;
    int greenOffset = (avgPeak - greenPeak) / 2;
    int blueOffset = (avgPeak - bluePeak) / 2;


    Image correctedImage = adjustImageColors(image, redOffset, greenOffset, blueOffset);

    return histogramVisualization(correctedImage);
  }

  /**
   * Finds the weighted peak intensity of a color channel within a specified range.
   *
   * @param channel each of the color channels for red, green or blue.
   * @param min     the minimum intensity value in the range.
   * @param max     the maximum intensity value in the range
   * @return the weighted average intensity, representing the peak.
   */
  private static int findWeightedPeak(int[][] channel, int min, int max) {
    int[] histogram = new int[256];
    int total = 0;
    int weightedSum = 0;

    for (int[] row : channel) {
      for (int value : row) {
        if (value >= min && value <= max) {
          histogram[value]++;
          weightedSum += value * histogram[value];
          total += histogram[value];
        }
      }
    }

    return total == 0 ? min : weightedSum / total;
  }

  /**
   * Adjusts the color channels of an image by applying specified offsets to each channel.
   *
   * @param image       the image too adjust.
   * @param redOffset   the offset to be applied to the red channel.
   * @param greenOffset the offset to be applied to the green channel.
   * @param blueOffset  the offset to be applied to the blue channel.
   * @return image with adjusted colors.
   */
  private static Image adjustImageColors(Image image, int redOffset, int greenOffset,
                                         int blueOffset) {
    int width = getDimensions(image)[1];
    int height = getDimensions(image)[0];

    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newRedChannel[i][j] = clamp(image.getRedChannel()[i][j] + redOffset);
        newGreenChannel[i][j] = clamp(image.getGreenChannel()[i][j] + greenOffset);
        newBlueChannel[i][j] = clamp(image.getBlueChannel()[i][j] + blueOffset);
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }

  /**
   * Adjusts the levels of an image by applying a curve to map each color channel to specified
   * black, midtone, and white points.
   *
   * @param original the image to adjust.
   * @param b        the black point for clamping pixels.
   * @param m        the mid value for clamping pixels.
   * @param w        the white value for clamping pixels.
   * @return the adjusted image.
   * @throws IllegalArgumentException if the black, mid, or white values are invalid.
   */
  protected static Image levelsAdjust(Image original, int b, int m, int w) {

    int[][] redChannel = original.getRedChannel();
    int[][] greenChannel = original.getGreenChannel();
    int[][] blueChannel = original.getBlueChannel();

    double A = Math.pow(b, 2) * (m - w) - b * (Math.pow(m, 2) - Math.pow(w, 2))
            + w * Math.pow(m, 2) - m * Math.pow(w, 2);

    double Aa = -b * (128 - 255) + 128 * w - 255 * m;
    double Ab = Math.pow(b, 2) * (128 - 255) + 255 * Math.pow(m, 2) - 128 * Math.pow(w, 2);
    double Ac = Math.pow(b, 2) * (255 * m - 128 * w) - b * (255 * Math.pow(m, 2)
            - 128 * Math.pow(w, 2));

    double a = Aa / A;
    double bCoeff = Ab / A;
    double c = Ac / A;

    int[][] adjustedRed = adjustChannel(redChannel, a, bCoeff, c, b, w);
    int[][] adjustedGreen = adjustChannel(greenChannel, a, bCoeff, c, b, w);
    int[][] adjustedBlue = adjustChannel(blueChannel, a, bCoeff, c, b, w);


    return histogramVisualization(new Image(adjustedRed, adjustedGreen, adjustedBlue));
//    return new Image(adjustedRed, adjustedGreen, adjustedBlue);
  }

  /**
   * Adjusts a color channel by applying the quadratic transformation to each pixel value.
   *
   * @param channel each of the color channels (red, green, blue).
   * @param a       quadratic coefficient.
   * @param bCoeff  linear coefficient.
   * @param c       constant coefficient.
   * @param b       black point threshold.
   * @param w       white point threshold.
   * @return the adjusted color channel.
   */
  private static int[][] adjustChannel(int[][] channel, double a, double bCoeff, double c, int b,
                                       int w) {
    int height = channel.length;
    int width = channel[0].length;
    int[][] adjustedChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int value = channel[i][j];
        double result;

        if (value <= b) {
          result = 0;
        } else if (value >= w) {
          result = 255;
        } else {
          result = a * Math.pow(value, 2) + bCoeff * value + c;
          result = clamp((int) result);
        }

        adjustedChannel[i][j] = (int) Math.round(result);
      }
    }
    return adjustedChannel;
  }

  /**
   * Clamps a value to ensure it is between 0 and 255.
   */
  private static int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }

  /**
   * Applies a specified image effect to the left portion of an image up to a given percentage
   * of the image width, while keeping the remaining portion unchanged.
   *
   * @param original   the image to modify.
   * @param percentage the percentage of the image width to apply the effect to (0–100).
   * @param operation  the image effect processor that applies the desired effect.
   * @return image with the effect applied up to the specified percentage.
   */
  protected static Image applyOperationSplit(Image original, double percentage,
                                             ImageEffectProcessor operation) {
    int width = getDimensions(original)[1];
    int height = getDimensions(original)[0];

    int splitLine = (int) (width * (percentage / 100));

    Image modifiedImage = operation.applyEffect(original);

    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (j < splitLine) {
          newRedChannel[i][j] = modifiedImage.getRedChannel()[i][j];
          newGreenChannel[i][j] = modifiedImage.getGreenChannel()[i][j];
          newBlueChannel[i][j] = modifiedImage.getBlueChannel()[i][j];
        } else {
          newRedChannel[i][j] = original.getRedChannel()[i][j];
          newGreenChannel[i][j] = original.getGreenChannel()[i][j];
          newBlueChannel[i][j] = original.getBlueChannel()[i][j];
        }
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }
  protected static Image applyOperationSplit(Image original, double percentage,
                                             ParameterizedImageEffectProcessor operation, Integer b, Integer m, Integer w) {
    int width = getDimensions(original)[1];
    int height = getDimensions(original)[0];
    int splitLine = (int) (width * (percentage / 100));

    Image modifiedImage = operation.applyEffect(original, b, m, w);


    // Remainder of the split logic
    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (j < splitLine) {
          newRedChannel[i][j] = modifiedImage.getRedChannel()[i][j];
          newGreenChannel[i][j] = modifiedImage.getGreenChannel()[i][j];
          newBlueChannel[i][j] = modifiedImage.getBlueChannel()[i][j];
        } else {
          newRedChannel[i][j] = original.getRedChannel()[i][j];
          newGreenChannel[i][j] = original.getGreenChannel()[i][j];
          newBlueChannel[i][j] = original.getBlueChannel()[i][j];
        }
      }
    }

    return new Image(newRedChannel, newGreenChannel, newBlueChannel);
  }
}
