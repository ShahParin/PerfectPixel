package controller;

import java.io.IOException;

import controller.commands.BlueComponentCommand;
import controller.commands.BlueComponentMaskedCommand;
import controller.commands.BlurCommand;
import controller.commands.BlurMaskedCommand;
import controller.commands.GreenComponentCommand;
import controller.commands.GreenComponentMaskedCommand;
import controller.commands.IntensityComponentCommand;
import controller.commands.IntensityMaskedCommand;
import controller.commands.LumaComponentCommand;
import controller.commands.LumaMaskedCommand;
import controller.commands.RedComponentCommand;
import controller.commands.RedComponentMaskedCommand;
import controller.commands.SepiaCommand;
import controller.commands.SepiaMaskedCommand;
import controller.commands.SharpenCommand;
import controller.commands.SharpenMaskedCommand;
import controller.commands.ValueComponentCommand;
import controller.commands.ValueMaskedCommand;
import model.ImageModelV2;
import model.ImageModelV3;
import view.ImageView;

/**
 * A controller version compatible to ImageModelV3.
 */
public class TextBasedControllerV3 extends TextBasedController {
  private final ImageModelV3 imageModelV3;

  /**
   * Constructor to initialize a TextBasedController objects.
   *
   * @param imageModelV2 the image model used to store and process images.
   * @param view         the view used to display information and results to the user.
   * @param imageService the service used to manage image loading and saving operations.
   */

  public TextBasedControllerV3(ImageModelV3 imageModelV3, ImageModelV2 imageModelV2, ImageView view, ImageService imageService) {
    super(imageModelV2, view, imageService);
    this.imageModelV3 = imageModelV3;
    this.initializeCommands();
  }

  @Override
  void initializeCommands() {
    super.initializeCommands();
    commandMap.put("blur", args -> {
      if (args.length == 3) {
        return new BlurCommand(imageModel, args[1], args[2]);
      } else if (args.length == 5 && args[3].equals("split")) {
        double splitPercentage = Double.parseDouble(args[4]);
        return new BlurCommand(imageModel, args[1], args[2], splitPercentage);
      } else if (args.length == 4) {
        return new BlurMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for blur command.");
      }
    });

    commandMap.put("sharpen", args -> {
      if (args.length == 3) {
        return new SharpenCommand(imageModel, args[1], args[2]);
      } else if (args.length == 5 && args[3].equals("split")) {
        double splitPercentage = Double.parseDouble(args[4]);
        return new SharpenCommand(imageModel, args[1], args[2], splitPercentage);
      } else if (args.length == 4) {
        return new SharpenMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for sharpen command.");
      }
    });

    // Sepia Command
    commandMap.put("sepia", args -> {
      if (args.length == 3) {
        return new SepiaCommand(imageModel, args[1], args[2]);
      } else if (args.length == 5 && args[3].equals("split")) {
        double splitPercentage = Double.parseDouble(args[4]);
        return new SepiaCommand(imageModel, args[1], args[2], splitPercentage);
      } else if (args.length == 4) {
        return new SepiaMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for sepia command.");
      }
    });

    // Luma Component Command
    commandMap.put("luma-component", args -> {
      if (args.length == 3) {
        return new LumaComponentCommand(imageModel, args[1], args[2]);
      } else if (args.length == 5 && args[3].equals("split")) {
        double splitPercentage = Double.parseDouble(args[4]);
        return new LumaComponentCommand(imageModel, args[1], args[2], splitPercentage);
      } else if (args.length == 4) {
        return new LumaMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for lumaComponent command.");
      }
    });

    // Intensity Component Command
    commandMap.put("intensity-component", args -> {
      if (args.length == 3) {
        return new IntensityComponentCommand(imageModel, args[1], args[2]);
      } else if (args.length == 5 && args[3].equals("split")) {
        double splitPercentage = Double.parseDouble(args[4]);
        return new IntensityComponentCommand(imageModel, args[1], args[2], splitPercentage);
      } else if (args.length == 4) {
        return new IntensityMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for intensityComponent command.");
      }
    });

    // Value Component Command
    commandMap.put("value-component", args -> {
      if (args.length == 3) {
        return new ValueComponentCommand(imageModel, args[1], args[2]);
      } else if (args.length == 5 && args[3].equals("split")) {
        double splitPercentage = Double.parseDouble(args[4]);
        return new ValueComponentCommand(imageModel, args[1], args[2], splitPercentage);
      } else if (args.length == 4) {
        return new ValueMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for valueComponent command.");
      }
    });

    // Red Component Command
    commandMap.put("red-component", args -> {
      if (args.length == 3) {
        return new RedComponentCommand(imageModel, args[1], args[2]);
      } else if (args.length == 4) {
        return new RedComponentMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for redComponent command.");
      }
    });

    // Green Component Command
    commandMap.put("green-component", args -> {
      if (args.length == 3) {
        return new GreenComponentCommand(imageModel, args[1], args[2]);
      } else if (args.length == 4) {
        return new GreenComponentMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for greenComponent command.");
      }
    });

    // Blue Component Command
    commandMap.put("blue-component", args -> {
      if (args.length == 3) {
        return new BlueComponentCommand(imageModel, args[1], args[2]);
      } else if (args.length == 4) {
        return new BlueComponentMaskedCommand(imageModelV3, args[0], args[1], args[2], args[3]);
      } else {
        throw new IllegalArgumentException("Invalid arguments for blueComponent command.");
      }
    });
  }


  @Override
  public void runScript(String scriptFilePath) {
    super.runScript(scriptFilePath);

  }

  @Override
  public void execute(String command) throws IOException {
    super.execute(command);
  }

}
