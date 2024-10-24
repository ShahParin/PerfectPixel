package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

/**
 * This class represents the Controller, acting as the mediator between the inputs from the View and
 * the different operations to be preformed from the Model.
 */
public class TextBasedController implements ImageController {
  private final ImageModel imageModel;
  private final ImageView view;

  /**
   * A constructor to initialize the Model and View objects.
   *
   * @param imageModel the ImageModel object.
   * @param view       the View object.
   */
  public TextBasedController(ImageModel imageModel, ImageView view) {
    this.imageModel = imageModel;
    this.view = view;
  }

  /**
   * THis function calls the appropriate operation from the Model as per the inputs provided.
   *
   * @param command the command/operation to execute.
   * @throws IOException throws exception if file not found.
   */
  public void execute(String command) throws IOException {
    String[] args = command.split(" ");

    switch (args[0]) {
      case "load":
        try {
          view.printStatements("Loading image from " + args[1]);
          imageModel.loadImage(args[1], args[2]);
          view.printStatements("Loaded image from " + args[1]);
          break;
        } catch (IOException e) {
          throw new IOException(e);
        }

      case "save":
        try {
          view.printStatements("Saving image to " + args[1]);
          imageModel.saveImage(args[1], args[2]);
          view.printStatements("Saved image to " + args[1]);
          break;
        } catch (IOException e) {
          throw new IOException(e);
        }

      case "red-component":
        view.printStatements("Applying Red Component to " + args[1]);
        imageModel.applyRedComponent(args[1], args[2]);
        view.printStatements("Applied Red Component to " + args[1]);
        break;

      case "blue-component":
        view.printStatements("Applying Blue Component to " + args[1]);
        imageModel.applyBlueComponent(args[1], args[2]);
        view.printStatements("Applied Blue Component to " + args[1]);
        break;

      case "green-component":
        view.printStatements("Applying Green Component to " + args[1]);
        imageModel.applyGreenComponent(args[1], args[2]);
        view.printStatements("Applied Green Component to " + args[1]);
        break;

      case "value-component":
        view.printStatements("Applying Value Component to " + args[1]);
        imageModel.applyValue(args[1], args[2]);
        view.printStatements("Applied Value Component to " + args[1]);
        break;

      case "intensity-component":
        view.printStatements("Applying Intensity Component to " + args[1]);
        imageModel.applyIntensity(args[1], args[2]);
        view.printStatements("Applied Intensity Component to " + args[1]);
        break;

      case "luma-component":
        view.printStatements("Applying Luma Component to " + args[1]);
        imageModel.applyLuma(args[1], args[2]);
        view.printStatements("Applied Luma Component to " + args[1]);
        break;

      case "rgb-split":
        view.printStatements("Applying RGB Split to " + args[1]);
        imageModel.rgbSplit(args[1], args[2], args[3], args[4]);
        view.printStatements("Applied RGB Split to " + args[1]);
        break;

      case "rgb-combine":
        view.printStatements("Applying RGB Combine to " + args[1]);
        imageModel.rgbCombine(args[1], args[2], args[3], args[4]);
        view.printStatements("Applied RGB Combine to " + args[1]);
        break;

      case "brighten":
        view.printStatements("Applying Brighten of " + args[1] + " to " + args[2]);
        int inc = Integer.parseInt(args[1]);
        imageModel.brightenImage(inc, args[2], args[3]);
        view.printStatements("Applied Brighten of " + args[1] + " to " + args[2]);
        break;

      case "horizontal-flip":
        view.printStatements("Applying Horizontal Flip to " + args[1]);
        imageModel.flipHorizontally(args[1], args[2]);
        view.printStatements("Applied Horizontal Flip to " + args[1]);
        break;

      case "vertical-flip":
        view.printStatements("Applying Vertical Flip to " + args[1]);
        imageModel.flipVertically(args[1], args[2]);
        view.printStatements("Applied Vertical Flip to " + args[1]);
        break;

      case "sepia":
        view.printStatements("Applying Sepia to " + args[1]);
        imageModel.applySepia(args[1], args[2]);
        view.printStatements("Applied Sepia to " + args[1]);
        break;

      case "blur":
        view.printStatements("Applying Blur to " + args[1]);
        imageModel.blurImage(args[1], args[2]);
        view.printStatements("Applied Blur to " + args[1]);
        break;

      case "sharpen":
        view.printStatements("Applying Sharpen to " + args[1]);
        imageModel.sharpenImage(args[1], args[2]);
        view.printStatements("Applied Sharpen to " + args[1]);
        break;

      case "run":
        view.printStatements("Executing commands in " + args[1]);
        runScript(args[1]);
        view.printStatements("Executed commands in " + args[1]);
        break;

      default:
        view.printStatements("Invalid command: " + command);
    }
  }

  /**
   * A function to accept the inputs from the view or user.
   *
   * @param scriptFilePath contains the location of the list of commands.
   */
  @Override
  public void runScript(String scriptFilePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {

      String command;
      while ((command = reader.readLine()) != null) {
        command = command.trim();
        if (!command.startsWith("#") && !command.isEmpty()) {  // Ignore comments
          try {
            execute(command.trim());
          } catch (Exception e) {
            System.err.println("Error executing command \"" + command + "\": " + e.getMessage());
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading script file: " + e.getMessage());
    }
  }
}


