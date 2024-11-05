package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.commands.BlueComponentCommand;
import controller.commands.BlurCommand;
import controller.commands.BlurPercentageCommand;
import controller.commands.BrightenCommand;
import controller.commands.ColorCorrectCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
import controller.commands.CompressCommand;
import controller.commands.GreenComponentCommand;
import controller.commands.HistogramVisualizationCommand;
import controller.commands.HorizontalFlipCommand;
import controller.commands.IntensityComponentCommand;
import controller.commands.LevelsAdjustCommand;
import controller.commands.LoadCommand;
import controller.commands.LumaComponentCommand;
import controller.commands.RGBCombineCommand;
import controller.commands.RGBSplitCommand;
import controller.commands.RedComponentCommand;
import controller.commands.SaveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.ValueComponentCommand;
import controller.commands.VerticalFlipCommand;
import model.ImageModelV2;
import view.ImageView;

public class TextBasedController implements ImageController {
  //  private final ImageModel imageModel;
  private final ImageModelV2 imageModel;
  private final ImageView view;
  private final Map<String, CommandFactory> commandMap;

  public TextBasedController(ImageModelV2 imageModel, ImageView view) {
    this.imageModel = imageModel;
    this.view = view;
    this.commandMap = new HashMap<>();
    initializeCommands();
  }

  private void initializeCommands() {
    commandMap.put("load", args -> new LoadCommand(imageModel, view, args[1], args[2]));
    commandMap.put("save", args -> new SaveCommand(imageModel, args[1], args[2]));
    commandMap.put("red-component", args -> new RedComponentCommand(imageModel, args[1], args[2]));
    commandMap.put("blue-component", args -> new BlueComponentCommand(imageModel, args[1], args[2]));
    commandMap.put("green-component", args -> new GreenComponentCommand(imageModel, args[1], args[2]));
    commandMap.put("value-component", args -> new ValueComponentCommand(imageModel, view, args[1], args[2]));
    commandMap.put("intensity-component", args -> new IntensityComponentCommand(imageModel, args[1], args[2]));
    commandMap.put("luma-component", args -> new LumaComponentCommand(imageModel, args[1], args[2]));
    commandMap.put("rgb-split", args -> new RGBSplitCommand(imageModel, args[1], args[2], args[3], args[4]));
    commandMap.put("rgb-combine", args -> new RGBCombineCommand(imageModel, args[1], args[2], args[3], args[4]));
    commandMap.put("brighten", args -> new BrightenCommand(imageModel, Integer.parseInt(args[1]), args[2], args[3]));
    commandMap.put("horizontal-flip", args -> new HorizontalFlipCommand(imageModel, args[1], args[2]));
    commandMap.put("vertical-flip", args -> new VerticalFlipCommand(imageModel, args[1], args[2]));
    commandMap.put("sepia", args -> new SepiaCommand(imageModel, args[1], args[2]));
    commandMap.put("blur", args -> new BlurCommand(imageModel, args[1], args[2]));
    commandMap.put("sharpen", args -> new SharpenCommand(imageModel, args[1], args[2]));
    commandMap.put("histogram-vis", args -> new HistogramVisualizationCommand(imageModel, args[1], args[2]));
    commandMap.put("color-correct", args -> new ColorCorrectCommand(imageModel, args[1], args[2]));
    commandMap.put("levels-adjust", args -> new LevelsAdjustCommand(imageModel, Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4], args[5]));
    commandMap.put("blur-per", args -> new BlurPercentageCommand(imageModel, args[1], args[2], Double.parseDouble(args[3])));
    commandMap.put("compress", args -> new CompressCommand(imageModel, Double.parseDouble(args[1]), args[2], args[3]));
  }

  public void execute(String command) throws IOException {
    String[] args = command.trim().split("\\s+");
    CommandFactory factory = commandMap.get(args[0]);

    if (factory != null) {
      Command cmd = factory.create(args);
      cmd.execute();
    } else {
      view.printStatements("Invalid command: " + command);
    }
  }

  @Override
  public void runScript(String scriptFilePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
      String command;
      while ((command = reader.readLine()) != null) {
        command = command.trim();
        if (!command.startsWith("#") && !command.isEmpty()) {  // Ignore comments
          try {
            execute(command);
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
