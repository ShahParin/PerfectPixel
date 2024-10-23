package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.ImageModel;

public class ImageController {

  private final ImageModel imageModel;

  public ImageController(ImageModel imageModel) {
    this.imageModel = imageModel;
  }

  public void execute(String command) {
    String[] args = command.split(" ");

    switch (args[0]) {
      case "load":
        System.out.println("Loading image from " + args[1]);
        imageModel.loadImage(args[1], args[2]);
        System.out.println("Loaded image from " + args[1]);
        break;
      case "save":
        System.out.println("Saving image to " + args[1]);
        imageModel.saveImage(args[1], args[2]);
        System.out.println("Saved image to " + args[1]);
        break;
      case "red-component":
        System.out.println("Applying Red Component to " + args[1]);
        imageModel.applyRedComponent(args[1], args[2]);
        System.out.println("Applied Red Component to " + args[1]);
        break;
      case "blue-component":
        System.out.println("Applying Blue Component to " + args[1]);
        imageModel.applyBlueComponent(args[1], args[2]);
        System.out.println("Applied Blue Component to " + args[1]);
        break;
      case "green-component":
        System.out.println("Applying Green Component to " + args[1]);
        imageModel.applyGreenComponent(args[1], args[2]);
        System.out.println("Applied Green Component to " + args[1]);
        break;
      case "value-component":
        System.out.println("Applying Value Component to " + args[1]);

        imageModel.applyValue(args[1], args[2]);
        System.out.println("Applied Value Component to " + args[1]);
        break;
      case "intensity-component":
        System.out.println("Applying Intensity Component to " + args[1]);
        imageModel.applyIntensity(args[1], args[2]);
        System.out.println("Applied Intensity Component to " + args[1]);
        break;
      case "luma-component":
        System.out.println("Applying Luma Component to " + args[1]);
        imageModel.applyLuma(args[1], args[2]);
        System.out.println("Applied Luma Component to " + args[1]);
        break;
      case "rgb-split":
        System.out.println("Applying RGB Split to " + args[1]);
        imageModel.rgbSplit(args[1], args[2], args[3], args[4]);
        System.out.println("Applied RGB Split to " + args[1]);
        break;
      case "rgb-combine":
        System.out.println("Applying RGB Combine to " + args[1]);
        imageModel.rgbCombine(args[1], args[2], args[3], args[4]);
        System.out.println("Applied RGB Combine to " + args[1]);
        break;
      case "brighten":
        System.out.println("Applying Brighten of " + args[1] + " to " + args[2]);
        int inc = Integer.parseInt(args[1]);
        imageModel.brightenImage(inc, args[2], args[3]);
        System.out.println("Applied Brighten of " + args[1] + " to " + args[2]);
        break;
      case "horizontal-flip":
        System.out.println("Applying Horizontal Flip to " + args[1]);
        imageModel.flipHorizontally(args[1], args[2]);
        System.out.println("Applied Horizontal Flip to " + args[1]);
        break;
      case "vertical-flip":
        System.out.println("Applying Vertical Flip to " + args[1]);
        imageModel.flipVertically(args[1], args[2]);
        System.out.println("Applied Vertical Flip to " + args[1]);
        break;
      case "sepia":
        System.out.println("Applying Sepia to " + args[1]);
        imageModel.applySepia(args[1], args[2]);
        System.out.println("Applied Sepia to " + args[1]);
        break;
      case "greyscale":
        System.out.println("Applying Greyscale to " + args[1]);
        imageModel.applyGreyscale(args[1], args[2]);
        System.out.println("Applied Greyscale to " + args[1]);
        break;
      case "blur":
        System.out.println("Applying Blur to " + args[1]);
        imageModel.blurImage(args[1], args[2]);
        System.out.println("Applied Blur to " + args[1]);
        break;
      case "sharpen":
        System.out.println("Applying Sharpen to " + args[1]);
        imageModel.sharpenImage(args[1], args[2]);
        System.out.println("Applied Sharpen to " + args[1]);
        break;
      case "run":
        System.out.println("Executing commands in " + args[1]);
        runScript(args[1]);
        System.out.println("Executed commands in " + args[1]);
      default:
        System.out.println("Invalid command: " + command);
    }
  }

  public void runScript(String scriptFilePath) {
    System.out.println("Running script " + scriptFilePath);
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
      String command;
      while ((command = reader.readLine()) != null) {
        command = command.trim();
        if (!command.startsWith("#") && !command.isEmpty()) {  // Ignore comments
          execute(command.trim());
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading script file: " + e.getMessage());
    }
  }

}


