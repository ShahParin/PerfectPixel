package controller;

import java.io.IOException;

import controller.commands.*;
import model.Image;
import model.ImageModelV2;
import view.ImageView;

public class ViewController implements GUIFeatures {
  private final ImageModelV2 imageModel;
  private final ImageView view;
  private final ImageService imageService;

  public ViewController(ImageModelV2 imageModel, ImageView view, ImageService imageService) {
    this.imageModel = imageModel;
    this.view = view;
    this.imageService = imageService;
  }

  @Override
  public void load(String filePath, String imageName) throws IOException {
    Command loadCommand = new LoadCommand(imageService, filePath, imageName);
    loadCommand.execute();
    view.printStatements("Image loaded: " + imageName);
  }

  @Override
  public void save(String path, String imageName) throws IOException {
    Command saveCommand = new SaveCommand(imageService, path, imageName);
    saveCommand.execute();
    view.printStatements("Image saved: " + imageName);
  }

  @Override
  public void blur(String imageName, String newImageName) throws IOException {
    Command blurCommand = new BlurCommand(imageModel, imageName, newImageName);
    blurCommand.execute();
    view.printStatements("Image blurred: " + newImageName);
  }

  @Override
  public void sharpen(String imageName, String newImageName) throws IOException {
    Command sharpenCommand = new SharpenCommand(imageModel, imageName, newImageName);
    sharpenCommand.execute();
    view.printStatements("Image sharpened: " + newImageName);
  }

  @Override
  public void horizontalFlip(String imageName, String newImageName) throws IOException {
    Command horizontalFlipCommand = new HorizontalFlipCommand(imageModel, imageName, newImageName);
    horizontalFlipCommand.execute();
    view.printStatements("Image flipped horizontally: " + newImageName);
  }

  @Override
  public void verticalFlip(String imageName, String newImageName) throws IOException {
    Command verticalFlipCommand = new VerticalFlipCommand(imageModel, imageName, newImageName);
    verticalFlipCommand.execute();
    view.printStatements("Image flipped vertically: " + newImageName);
  }

  @Override
  public void greyscale(String imageName, String newImageName) throws IOException {
    Command greyscaleCommand = new LumaComponentCommand(imageModel, imageName, newImageName);
    greyscaleCommand.execute();
    view.printStatements("Image converted to greyscale: " + newImageName);
  }

  @Override
  public void sepia(String imageName, String newImageName) throws IOException {
    Command sepiaCommand = new SepiaCommand(imageModel, imageName, newImageName);
    sepiaCommand.execute();
    view.printStatements("Image converted to sepia tone: " + newImageName);
  }

  @Override
  public void redComponent(String imageName, String newImageName) throws IOException {
    Command redComponentCommand = new RedComponentCommand(imageModel, imageName, newImageName);
    redComponentCommand.execute();
    view.printStatements("Red component extracted: " + newImageName);
  }

  @Override
  public void greenComponent(String imageName, String newImageName) throws IOException {
    Command greenComponentCommand = new GreenComponentCommand(imageModel, imageName, newImageName);
    greenComponentCommand.execute();
    view.printStatements("Green component extracted: " + newImageName);
  }

  @Override
  public void blueComponent(String imageName, String newImageName) throws IOException {
    Command blueComponentCommand = new BlueComponentCommand(imageModel, imageName, newImageName);
    blueComponentCommand.execute();
    view.printStatements("Blue component extracted: " + newImageName);
  }

  @Override
  public void compress(String imageName, String newImageName, double percent) throws IOException {
    Command compressCommand = new CompressCommand(imageModel, percent, imageName, newImageName);
    compressCommand.execute();
    view.printStatements("Image compressed: " + newImageName);
  }

  @Override
  public void levelsAdjust(String imageName, String newImageName, int black,
                           int mid, int white) throws IOException {
    Command levelsAdjustCommand = new LevelsAdjustCommand(imageModel, black, mid, white
            ,imageName, newImageName);
    levelsAdjustCommand.execute();
    view.printStatements("Levels adjusted: " + newImageName);
  }

  @Override
  public void colorCorrect(String imageName, String newImageName) throws IOException {
    Command colorCorrectCommand = new ColorCorrectCommand(imageModel, imageName, newImageName);
    colorCorrectCommand.execute();
    view.printStatements("Image color corrected: " + newImageName);
  }

  @Override
  public Image getImage(String imageName) {
    return imageModel.getImage(imageName);
  }

  @Override
  public Image getHistogram(String imageName) {
    String histImageName = imageName+"_histogram";
    imageModel.applyHistogramVisualization(imageName, histImageName);
    return imageModel.getImage(histImageName);
  }

  @Override
  public void blurSplitOperation(String imageName, String newImageName, double percent ) throws IOException {
    Command blurSplitCommand = new BlurCommand(imageModel, imageName, newImageName, percent);
    blurSplitCommand.execute();
    view.printStatements("Image blurred: " + newImageName);
//    imageModel.blurImageSplit(imageName, imageName,percent);
  }

  @Override
  public void levelsAdjustmentSplitOperation(String currentImageName, String outputImageName, double black, double mid, double white,double percent) throws IOException {
    Command levelsAdjustCommand = new LevelsAdjustCommand(imageModel, (int) black, (int) mid, (int) white,currentImageName,outputImageName,percent);
    levelsAdjustCommand.execute();
    view.printStatements("Levels adjusted: " + currentImageName);
  }

  @Override
  public void sharpenSplitOperation(String currentImageName, String outputImageName, double inputValue) throws IOException {
    Command sharpenSplitCommand = new SharpenCommand(imageModel, currentImageName, outputImageName, inputValue);
    sharpenSplitCommand.execute();
    view.printStatements("Image sharpened: " + currentImageName);
  }

  @Override
  public void sepiaSplitOperation(String currentImageName, String outputImageName, double inputValue) throws IOException {
    Command sepiaSplitCommand = new SepiaCommand(imageModel, currentImageName, outputImageName, inputValue);
    sepiaSplitCommand.execute();
    view.printStatements("Image sepia extracted: " + currentImageName);
  }

  @Override
  public void greyscaleSplitOperation(String currentImageName, String outputImageName, double inputValue) throws IOException {
    Command greyscaleSplitCommand = new LumaComponentCommand(imageModel, currentImageName, outputImageName, inputValue);
    greyscaleSplitCommand.execute();
    view.printStatements("Greyscale extracted: " + currentImageName);
  }

  @Override
  public void colorCorrectionSplitOperation(String currentImageName, String outputImageName, double inputValue) throws IOException {
    Command colorCorrectionSplitCommand = new ColorCorrectCommand(imageModel, currentImageName, outputImageName, inputValue);
    colorCorrectionSplitCommand.execute();
    view.printStatements("Color correction extracted: " + currentImageName);
  }
}
