package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import controller.commands.Command;
import controller.commands.CommandFactory;
import controller.commands.LoadCommand;
import controller.commands.SaveCommand;
import model.Image;
import model.ImageModelV2;
import view.ImageView;

import static model.ImageUtil.getDimensions;

public class ViewController implements GUIFeatures {
  private final ImageModelV2 imageModel;
  private final ImageView view;
  private final ImageService imageService;
//  private final String currentImageName;
  private final Map<String, CommandFactory> commandMap;

  public ViewController(ImageModelV2 imageModel, ImageView view, ImageService imageService) {
    this.imageModel = imageModel;
    this.view = view;
    this.imageService = imageService;
//    this.currentImageName = "newImage";
    commandMap = new HashMap<>();
//    initializeCommands();
  }

//  private void initializeCommands() {
//    commandMap.put("load", args -> new LoadCommand(imageService, args[1], args[2]));
//
//    commandMap.put("save", args -> new SaveCommand(imageService, args[1], args[2]));
//  }

//  private void processCommands(Command command) throws IOException {
//    command.execute();
//  }

//  @Override
//  public void load(String path) throws IOException {
//    imageService.loadImage(path, currentImageName);
//  }
//
//  private void executeCommand(Command command, String successMessage) {
//    try {
//      command.execute();
//      view.printStatements(successMessage);
//    } catch (Exception e) {
//      view.printStatements("Error: " + e.getMessage());
//    }
//  }

  @Override
  public void load(String filePath, String imageName) throws IOException {
    Command loadCommand = new LoadCommand(imageService, filePath, imageName);
    loadCommand.execute();
    view.printStatements("Image loaded: " + imageName);
    Image image = imageModel.getImage(imageName);
    view.LoadFile(image);
  }

  @Override
  public void save(String path, String imageName) throws IOException {
    Command saveCommand = new SaveCommand(imageService, path, imageName);
    saveCommand.execute();
    view.printStatements("Image saved: " + imageName);
    view.SaveFile(path);
  }

//  @Override
//  public void imageToBufferedImage(String imageName) {
//    Image image = imageModel.getImage(imageName);
//
//    int height = getDimensions(image)[0];
//    int width = getDimensions(image)[1];
//
//    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//    for (int y = 0; y < height; y++) {
//      for (int x = 0; x < width; x++) {
//        int red = image.getRedChannel()[y][x];
//        int green = image.getGreenChannel()[y][x];
//        int blue = image.getBlueChannel()[y][x];
//
//        int rgb = (red << 16) | (green << 8) | blue;
//        bufferedImage.setRGB(x, y, rgb);
//      }
//    }
//
//    if (bufferedImage != null) {
//      ImageIcon imageIcon = new ImageIcon(bufferedImage);
//      view.displayImage(imageIcon);  // Display in the GUI
//      view.printStatements("Displaying image: " + imageName);
//    } else {
//      view.printStatements("Error: Image " + imageName + " not found.");
//    }
//  }
}
