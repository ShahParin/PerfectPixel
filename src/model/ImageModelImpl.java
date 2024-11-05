package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static model.ImageOperations.applyBlurSplit;
import static model.ImageOperations.blur;
import static model.ImageOperations.brighten;
import static model.ImageOperations.colorCorrect;
import static model.ImageOperations.combineRGB;
import static model.ImageOperations.extractBlueComponent;
import static model.ImageOperations.extractGreenComponent;
import static model.ImageOperations.extractRedComponent;
import static model.ImageOperations.histogramVisualization;
import static model.ImageOperations.levelsAdjust;
import static model.ImageOperations.pixelIntensity;
import static model.ImageOperations.pixelLuma;
import static model.ImageOperations.pixelValue;
import static model.ImageOperations.readOther;
import static model.ImageOperations.readPPM;
import static model.ImageOperations.saveOther;
import static model.ImageOperations.savePPM;
import static model.ImageOperations.sepia;
import static model.ImageOperations.sharpen;
import static model.ImageOperations.splitRGB;

/**
 * This class provides functionality to manage and manipulate images. It supports operations like
 * loading, saving, applying color filters, transforming, and combining images.
 * The images can be loaded and saved in different formats, and the class offers various methods to
 * extract and manipulate specific color components (red, green, blue) or grayscale versions of
 * images (based on value, intensity, and luma).
 */
public class ImageModelImpl implements ImageModel {
  /*
  This class uses a Map to store images by their names and applies different operations
  such as flipping, brightening, and applying filters on these images.
  */
  private final Map<String, Image> images;

  /**
   * This constructor initializes the HashMap.
   */
  public ImageModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void loadImage(String path, String imageName) throws IOException {
    try {
      String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images"
              + File.separator + path;
      Image image;
      if (path.contains("ppm")) {
        image = readPPM(pathRelative);
      } else if (path.contains("png") || path.contains(("jpeg")) || path.contains("jpg")) {
        image = readOther(pathRelative);
      } else {
        throw new IllegalArgumentException("Unsupported image type: " + path);
      }
      images.put(imageName, image);
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  @Override
  public void saveImage(String path, String imageName) throws IOException {
    String pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images"
            + File.separator + path;

    Image image = images.get(imageName);
    if (image == null) {
      throw new IllegalArgumentException("No image found with name: " + imageName);
    }
    try {
      if (path.contains("ppm")) {
        savePPM(pathRelative, image);
      } else {
        saveOther(pathRelative, image);
      }
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  @Override
  public void applyRedComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image redImage = extractRedComponent(original);
      images.put(newImageName, redImage);
    }
  }

  @Override
  public void applyGreenComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image greenImage = extractGreenComponent(original);
      images.put(newImageName, greenImage);
    }
  }

  @Override
  public void applyBlueComponent(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blueImage = extractBlueComponent(original);
      images.put(newImageName, blueImage);
    }
  }

  @Override
  public void applyValue(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image valueImage = pixelValue(original);
      valueImage.clamp();
      images.put(newImageName, valueImage);
    }
  }

  @Override
  public void applyIntensity(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image intensityImage = pixelIntensity(original);
      intensityImage.clamp();
      images.put(newImageName, intensityImage);
    }
  }

  @Override
  public void applyLuma(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image lumaImage = pixelLuma(original);
      lumaImage.clamp();
      images.put(newImageName, lumaImage);
    }
  }

  @Override
  public void flipHorizontally(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image flippedImage = ImageOperations.flipHorizontally(original);
      images.put(newImageName, flippedImage);
    }
  }

  @Override
  public void flipVertically(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image flippedImage = ImageOperations.flipVertically(original);
      images.put(newImageName, flippedImage);
    }
  }

  @Override
  public void brightenImage(int increment, String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image brightenedImage = brighten(original, increment);
      brightenedImage.clamp();
      images.put(newImageName, brightenedImage);
    }
  }

  @Override
  public void blurImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blurredImage = blur(original);
      blurredImage.clamp();
      images.put(newImageName, blurredImage);
    }
  }

  @Override
  public void sharpenImage(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image sharpenedImage = sharpen(original);
      sharpenedImage.clamp();
      images.put(newImageName, sharpenedImage);
    }
  }

  @Override
  public void applySepia(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image sepiaImage = sepia(original);
      sepiaImage.clamp();
      images.put(newImageName, sepiaImage);
    }
  }

  @Override
  public void rgbSplit(String imageName, String redImage, String greenImage, String blueImage) {
    Image original = images.get(imageName);
    if (original != null) {
      Image[] channels = splitRGB(original);
      images.put(redImage, channels[0]);
      images.put(greenImage, channels[1]);
      images.put(blueImage, channels[2]);
    }
  }

  @Override
  public void rgbCombine(String newImageName, String redImage, String greenImage,
                         String blueImage) {
    Image red = images.get(redImage);
    Image green = images.get(greenImage);
    Image blue = images.get(blueImage);
    if (red != null && green != null && blue != null) {
      Image combinedImage = combineRGB(red, green, blue);
      images.put(newImageName, combinedImage);
    }
  }

  @Override
  public Image getImage(String imgName) throws IllegalArgumentException {
    if (this.images.get(imgName) == null) {
      throw new IllegalArgumentException("Image Not Found: " + imgName);
    }
    return this.images.get(imgName);
  }

  private List<Double> transform(List<Double> sequence) {
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

  private List<Double> invert(List<Double> sequence) {
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

//  private List<Double> transformSequence(List<Double> sequence) {
//    int length = sequence.size();
//    if (length % 2 != 0) {
//      sequence.add(0.0);
//    }
//
//    int m = length;
//    while (m > 1) {
//      List<Double> transformedSequence = transform(sequence.subList(0, m));
//
//      for (int i = 0; i < m; i++) {
//        sequence.set(i, transformedSequence.get(i));
//      }
//
//      m = m / 2;
//    }
//
//    return sequence;
//  }
//
//  private List<Double> invertSequence(List<Double> sequence) {
//    int length = sequence.size();
//    if (length % 2 != 0) {
//      sequence.add(0.0);
//    }
//
//    int m = 2;
//    while (m <= length) {
//      List<Double> invertedSequence = invert(sequence.subList(0, m));
//
//      for (int i = 0; i < m; i++) {
//        sequence.set(i, invertedSequence.get(i));
//      }
//
//      m = m * 2;
//    }
//
//    return sequence;
//  }

  public double[][] convertIntToDouble(int[][] intArray) {
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

  public int[][] convertDoubleToInt(double[][] doubleArray) {
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

  private double[][] haar(int[][] channel) {
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

  private double[][] thresholdChannel(double[][] channel, double percent) {
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

    return channel;
  }

  public void compressImage(String imageName, String newImageName, double percent) {
    Image original = images.get(imageName);

    double[][] newRed = haar(original.getRedChannel());
    double[][] newGreen = haar(original.getGreenChannel());
    double[][] newBlue = haar(original.getBlueChannel());

    double[][] thresholdRed = thresholdChannel(newRed, percent);
    double[][] thresholdGreen = thresholdChannel(newGreen, percent);
    double[][] thresholdBlue = thresholdChannel(newBlue, percent);

    Image result = new Image(convertDoubleToInt(thresholdRed), convertDoubleToInt(thresholdGreen),
            convertDoubleToInt(thresholdBlue));
    images.put(newImageName, result);
  }

  private int[][] invertHaar(int[][] channel) {
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

  public void decompressImage(String imageName, String newImageName) {
    Image original = images.get(imageName);

    int[][] invertRed = invertHaar(original.getRedChannel());
    int[][] invertGreen = invertHaar(original.getGreenChannel());
    int[][] invertBlue = invertHaar(original.getBlueChannel());

    Image inverted = new Image(invertRed, invertGreen, invertBlue);
    images.put(newImageName, inverted);
  }


  @Override
  public void applyHistogramVisualization(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image histogramVisualization = histogramVisualization(original);
//      histogramVisualization.clamp();

      images.put(newImageName, histogramVisualization);
    }
  }

  @Override
  public void applyColorCorrection(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image colorCorrection = colorCorrect(original);

      images.put(newImageName, colorCorrection);

    }
  }

  @Override
  public void applyLevelsAdjustment(int black, int mid, int white, String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image levelsAdjust = levelsAdjust(original, black, mid, white);

      images.put(newImageName, levelsAdjust);
    }
  }

  @Override
  public void blurImageSplit(String imageName, String newImageName, double percentage) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blurredImagePercentage = applyBlurSplit(original, percentage);
      blurredImagePercentage.clamp();
      images.put(newImageName, blurredImagePercentage);
    }
  }
}
