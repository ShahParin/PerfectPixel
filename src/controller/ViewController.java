package controller;

import java.io.IOException;

import controller.commands.BlueComponentCommand;
import controller.commands.BlurCommand;
import controller.commands.ColorCorrectCommand;
import controller.commands.Command;
import controller.commands.CompressCommand;
import controller.commands.GreenComponentCommand;
import controller.commands.HorizontalFlipCommand;
import controller.commands.LevelsAdjustCommand;
import controller.commands.LoadCommand;
import controller.commands.LumaComponentCommand;
import controller.commands.RedComponentCommand;
import controller.commands.SaveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.VerticalFlipCommand;
import model.Image;
import model.ImageModelV2;
import view.ImageView;

/**
 * This class is responsible for coordinating the actions of the image manipulation
 * operations in the graphical user interface (GUI).
 */
public class ViewController implements GUIFeatures {
  private final ImageModelV2 imageModel;
  private final ImageView view;
  private final ImageService imageService;

  /**
   * Constructs a ViewController with the specified model, view, and image service.
   *
   * @param imageModel   the image model that stores and manipulates the image data.
   * @param view         the view that displays the image data and messages.
   * @param imageService the image service responsible for loading and saving images.
   */
  public ViewController(ImageModelV2 imageModel, ImageView view, ImageService imageService) {
    this.imageModel = imageModel;
    this.view = view;
    this.imageService = imageService;
  }

  /**
   * Loads an image from the specified file path and adds it to the model.
   *
   * @param filePath  the path to the image file.
   * @param imageName the name to assign to the loaded image.
   * @throws IOException if an error occurs during the loading process.
   */
  @Override
  public void load(String filePath, String imageName) throws IOException {
    Command loadCommand = new LoadCommand(imageService, filePath, imageName);
    loadCommand.execute();
    view.printStatements("Image loaded: " + imageName);
  }

  /**
   * Saves the image to the specified path.
   *
   * @param path      the path to save the image to.
   * @param imageName the name of the image to save.
   * @throws IOException if an error occurs during the saving process.
   */
  @Override
  public void save(String path, String imageName) throws IOException {
    Command saveCommand = new SaveCommand(imageService, path, imageName);
    saveCommand.execute();
    view.printStatements("Image saved: " + imageName);
  }

  /**
   * Applies a blur effect to the specified image and saves the result with a new name.
   *
   * @param imageName    the name of the image to apply the blur effect to.
   * @param newImageName the name of the new image after the blur effect.
   * @throws IOException if an error occurs during the blurring process.
   */
  @Override
  public void blur(String imageName, String newImageName) throws IOException {
    Command blurCommand = new BlurCommand(imageModel, imageName, newImageName);
    blurCommand.execute();
    view.printStatements("Image blurred: " + newImageName);
  }

  /**
   * Applies a sharpen effect to the specified image and saves the result with a new name.
   *
   * @param imageName    the name of the image to apply the sharpen effect to.
   * @param newImageName the name of the new image after the sharpen effect.
   * @throws IOException if an error occurs during the sharpening process.
   */
  @Override
  public void sharpen(String imageName, String newImageName) throws IOException {
    Command sharpenCommand = new SharpenCommand(imageModel, imageName, newImageName);
    sharpenCommand.execute();
    view.printStatements("Image sharpened: " + newImageName);
  }

  /**
   * Flips the specified image horizontally and saves the result with a new name.
   *
   * @param imageName    the name of the image to flip horizontally.
   * @param newImageName the name of the new image after the horizontal flip.
   * @throws IOException if an error occurs during the horizontal flip process.
   */
  @Override
  public void horizontalFlip(String imageName, String newImageName) throws IOException {
    Command horizontalFlipCommand = new HorizontalFlipCommand(imageModel, imageName, newImageName);
    horizontalFlipCommand.execute();
    view.printStatements("Image flipped horizontally: " + newImageName);
  }

  /**
   * Flips the specified image vertically and saves the result with a new name.
   *
   * @param imageName    the name of the image to flip vertically.
   * @param newImageName the name of the new image after the vertical flip.
   * @throws IOException if an error occurs during the vertical flip process.
   */
  @Override
  public void verticalFlip(String imageName, String newImageName) throws IOException {
    Command verticalFlipCommand = new VerticalFlipCommand(imageModel, imageName, newImageName);
    verticalFlipCommand.execute();
    view.printStatements("Image flipped vertically: " + newImageName);
  }

  /**
   * Converts the specified image to greyscale and saves the result with a new name.
   *
   * @param imageName    the name of the image to convert to greyscale.
   * @param newImageName the name of the new greyscale image.
   * @throws IOException if an error occurs during the greyscale conversion process.
   */
  @Override
  public void greyscale(String imageName, String newImageName) throws IOException {
    Command greyscaleCommand = new LumaComponentCommand(imageModel, imageName, newImageName);
    greyscaleCommand.execute();
    view.printStatements("Image converted to greyscale: " + newImageName);
  }

  /**
   * Applies a sepia tone filter to the specified image and saves the result with a new name.
   *
   * @param imageName    the name of the image to apply the sepia tone filter to.
   * @param newImageName the name of the new image after the sepia tone filter.
   * @throws IOException if an error occurs during the sepia conversion process.
   */
  @Override
  public void sepia(String imageName, String newImageName) throws IOException {
    Command sepiaCommand = new SepiaCommand(imageModel, imageName, newImageName);
    sepiaCommand.execute();
    view.printStatements("Image converted to sepia tone: " + newImageName);
  }

  /**
   * Extracts the red component from the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to extract the red component from.
   * @param newImageName the name of the new image containing the red component.
   * @throws IOException if an error occurs during the extraction process.
   */
  @Override
  public void redComponent(String imageName, String newImageName) throws IOException {
    Command redComponentCommand = new RedComponentCommand(imageModel, imageName, newImageName);
    redComponentCommand.execute();
    view.printStatements("Red component extracted: " + newImageName);
  }

  /**
   * Extracts the green component from the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to extract the green component from.
   * @param newImageName the name of the new image containing the green component.
   * @throws IOException if an error occurs during the extraction process.
   */
  @Override
  public void greenComponent(String imageName, String newImageName) throws IOException {
    Command greenComponentCommand = new GreenComponentCommand(imageModel, imageName, newImageName);
    greenComponentCommand.execute();
    view.printStatements("Green component extracted: " + newImageName);
  }

  /**
   * Extracts the blue component from the specified image and saves it with a new name.
   *
   * @param imageName    the name of the image to extract the blue component from.
   * @param newImageName the name of the new image containing the blue component.
   * @throws IOException if an error occurs during the extraction process.
   */
  @Override
  public void blueComponent(String imageName, String newImageName) throws IOException {
    Command blueComponentCommand = new BlueComponentCommand(imageModel, imageName, newImageName);
    blueComponentCommand.execute();
    view.printStatements("Blue component extracted: " + newImageName);
  }

  /**
   * Compresses the specified image by a given percentage and saves the result with a new name.
   *
   * @param imageName    the name of the image to compress.
   * @param newImageName the name of the new compressed image.
   * @param percent      the percentage to compress the image by.
   * @throws IOException if an error occurs during the compression process.
   */
  @Override
  public void compress(String imageName, String newImageName, double percent) throws IOException {
    Command compressCommand = new CompressCommand(imageModel, percent, imageName, newImageName);
    compressCommand.execute();
    view.printStatements("Image compressed: " + newImageName);
  }

  /**
   * Adjusts the black, mid, and white points of the specified image and saves the
   * result with a new name.
   *
   * @param imageName    the name of the image to adjust.
   * @param newImageName the name of the new image after the adjustment.
   * @param black        the new black point.
   * @param mid          the new mid point.
   * @param white        the new white point.
   * @throws IOException if an error occurs during the levels adjustment process.
   */
  @Override
  public void levelsAdjust(String imageName, String newImageName, int black, int mid,
                           int white) throws IOException {
    Command levelsAdjustCommand = new LevelsAdjustCommand(imageModel, black, mid, white,
            imageName, newImageName);
    levelsAdjustCommand.execute();
    view.printStatements("Levels adjusted: " + newImageName);
  }

  /**
   * Applies color correction to the specified image and saves the result with a new name.
   *
   * @param imageName    the name of the image to apply color correction to.
   * @param newImageName the name of the new image after the color correction.
   * @throws IOException if an error occurs during the color correction process.
   */
  @Override
  public void colorCorrect(String imageName, String newImageName) throws IOException {
    Command colorCorrectCommand = new ColorCorrectCommand(imageModel, imageName, newImageName);
    colorCorrectCommand.execute();
    view.printStatements("Image color corrected: " + newImageName);
  }

  /**
   * Retrieves the image with the specified name from the model.
   *
   * @param imageName the name of the image to retrieve.
   * @return the image with the specified name.
   */
  @Override
  public Image getImage(String imageName) {
    return imageModel.getImage(imageName);
  }

  /**
   * Generates a histogram for the specified image and retrieves it from the model.
   *
   * @param imageName the name of the image to generate a histogram for.
   * @return the image representing the histogram.
   */
  @Override
  public Image getHistogram(String imageName) {
    String histImageName = imageName + "_histogram";
    imageModel.applyHistogramVisualization(imageName, histImageName);
    return imageModel.getImage(histImageName);
  }

  /**
   * Applies a split operation blur effect to the specified image and saves the result
   * with a new name.
   *
   * @param imageName    the name of the image to apply the blur effect to.
   * @param newImageName the name of the new image after the blur effect.
   * @param percent      the percentage of the blur effect to apply.
   * @throws IOException if an error occurs during the blurring process.
   */
  @Override
  public void blurSplitOperation(String imageName, String newImageName, double percent)
          throws IOException {
    Command blurSplitCommand = new BlurCommand(imageModel, imageName, newImageName, percent);
    blurSplitCommand.execute();
    view.printStatements("Image blurred: " + newImageName);
  }

  /**
   * Applies a split operation levels adjustment to the specified image and saves the result
   * with a new name.
   *
   * @param currentImageName the name of the image to apply the levels adjustment to.
   * @param outputImageName  the name of the new image after the levels adjustment.
   * @param black            the new black point.
   * @param mid              the new mid point.
   * @param white            the new white point.
   * @param percent          the percentage of the adjustment to apply.
   * @throws IOException if an error occurs during the levels adjustment process.
   */
  @Override
  public void levelsAdjustmentSplitOperation(String currentImageName, String outputImageName,
                                             double black, double mid, double white, double percent) throws IOException {
    Command levelsAdjustCommand = new LevelsAdjustCommand(imageModel, (int) black, (int) mid,
            (int) white, currentImageName, outputImageName, percent);
    levelsAdjustCommand.execute();
    view.printStatements("Levels adjusted: " + currentImageName);
  }

  /**
   * Applies a split operation sharpen effect to the specified image and saves the result
   * with a new name.
   *
   * @param currentImageName the name of the image to apply the sharpen effect to.
   * @param outputImageName  the name of the new image after the sharpen effect.
   * @param inputValue       the value to apply for sharpening.
   * @throws IOException if an error occurs during the sharpening process.
   */
  @Override
  public void sharpenSplitOperation(String currentImageName, String outputImageName,
                                    double inputValue) throws IOException {
    Command sharpenSplitCommand = new SharpenCommand(imageModel, currentImageName,
            outputImageName, inputValue);
    sharpenSplitCommand.execute();
    view.printStatements("Image sharpened: " + currentImageName);
  }

  /**
   * Applies a split operation sepia tone filter to the specified image and saves the result with a new name.
   *
   * @param currentImageName the name of the image to apply the sepia tone filter to.
   * @param outputImageName  the name of the new image after the sepia tone filter.
   * @param inputValue       the value to apply for the sepia tone.
   * @throws IOException if an error occurs during the sepia conversion process.
   */
  @Override
  public void sepiaSplitOperation(String currentImageName, String outputImageName,
                                  double inputValue) throws IOException {
    Command sepiaSplitCommand = new SepiaCommand(imageModel, currentImageName, outputImageName,
            inputValue);
    sepiaSplitCommand.execute();
    view.printStatements("Image sepia extracted: " + currentImageName);
  }

  /**
   * Applies a split operation greyscale conversion to the specified image and saves the result
   * with a new name.
   *
   * @param currentImageName the name of the image to apply the greyscale conversion to.
   * @param outputImageName  the name of the new image after the greyscale conversion.
   * @param inputValue       the value to apply for the greyscale conversion.
   * @throws IOException if an error occurs during the greyscale conversion process.
   */
  @Override
  public void greyscaleSplitOperation(String currentImageName, String outputImageName,
                                      double inputValue) throws IOException {
    Command greyscaleSplitCommand = new LumaComponentCommand(imageModel, currentImageName,
            outputImageName, inputValue);
    greyscaleSplitCommand.execute();
    view.printStatements("Greyscale extracted: " + currentImageName);
  }

  /**
   * Applies a split operation color correction to the specified image and saves the result
   * with a new name.
   *
   * @param currentImageName the name of the image to apply color correction to.
   * @param outputImageName  the name of the new image after the color correction.
   * @param inputValue       the value to apply for the color correction.
   * @throws IOException if an error occurs during the color correction process.
   */
  @Override
  public void colorCorrectionSplitOperation(String currentImageName, String outputImageName,
                                            double inputValue) throws IOException {
    Command colorCorrectionSplitCommand = new ColorCorrectCommand(imageModel, currentImageName,
            outputImageName, inputValue);
    colorCorrectionSplitCommand.execute();
    view.printStatements("Color correction extracted: " + currentImageName);
  }
}
