package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import controller.commands.BlueComponentCommand;
import controller.commands.BlurCommand;
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

/**
 * A controller for handling text-based inputs for image processing. This class interprets commands
 * for image manipulations, such as loading, saving, applying filters, etc.
 * It can read commands either from a script file or as individual inputs.
 */
public class TextBasedController implements ImageController {
  private final ImageModelV2 imageModel;
  private final ImageView view;
  private final Map<String, CommandFactory> commandMap;
  private final ImageService imageService;

  /**
   * Constructor to initialize a TextBasedController objects.
   *
   * @param imageModel   the image model used to store and process images.
   * @param view         the view used to display information and results to the user.
   * @param imageService the service used to manage image loading and saving operations.
   */
  public TextBasedController(ImageModelV2 imageModel, ImageView view, ImageService imageService) {
    this.imageModel = imageModel;
    this.view = view;
    this.imageService = imageService;
    this.commandMap = new HashMap<>();
    initializeCommands();
  }

  /**
   * Initializes the available commands in the command map.
   * Each command is associated with a string keyword and a factory method that creates
   * the command instance with the provided arguments.
   */
  private void initializeCommands() {
    commandMap.put("load", args -> new LoadCommand(imageService, args[1], args[2]));
    commandMap.put("save", args -> new SaveCommand(imageService, args[1], args[2]));
    commandMap.put("red-component", args -> new RedComponentCommand(imageModel, args[1], args[2]));
    commandMap.put("blue-component", args -> new BlueComponentCommand(imageModel, args[1],
            args[2]));
    commandMap.put("green-component", args -> new GreenComponentCommand(imageModel, args[1],
            args[2]));
    commandMap.put("value-component", args -> new ValueComponentCommand(imageModel, args[1],
            args[2]));
    commandMap.put("intensity-component", args -> new IntensityComponentCommand(imageModel,
            args[1], args[2]));
    commandMap.put("luma-component", args -> new LumaComponentCommand(imageModel, args[1],
            args[2]));
    commandMap.put("rgb-split", args -> new RGBSplitCommand(imageModel, args[1], args[2], args[3],
            args[4]));
    commandMap.put("rgb-combine", args -> new RGBCombineCommand(imageModel, args[1], args[2],
            args[3], args[4]));
    commandMap.put("brighten", args -> new BrightenCommand(imageModel, Integer.parseInt(args[1]),
            args[2], args[3]));
    commandMap.put("horizontal-flip", args -> new HorizontalFlipCommand(imageModel, args[1],
            args[2]));
    commandMap.put("vertical-flip", args -> new VerticalFlipCommand(imageModel, args[1], args[2]));
    commandMap.put("sepia", args -> new SepiaCommand(imageModel, args[1], args[2]));
    commandMap.put("blur", args -> {
      if (args.length == 3) {
        // Normal blur operation
        return new BlurCommand(imageModel, args[1], args[2]);
      } else if (args.length == 5 && args[3].equals("split")) {
        // Split blur
        double splitPercentage = Double.parseDouble(args[4]);
        return new BlurCommand(imageModel, args[1], args[2], splitPercentage);
      } else {
        throw new IllegalArgumentException("Invalid arguments for blur command.");
      }
    });
    commandMap.put("sharpen", args -> new SharpenCommand(imageModel, args[1], args[2]));
    commandMap.put("histogram", args -> new HistogramVisualizationCommand(imageModel, args[1],
            args[2]));
    commandMap.put("color-correct", args -> new ColorCorrectCommand(imageModel, args[1], args[2]));
    commandMap.put("levels-adjust", args -> new LevelsAdjustCommand(imageModel,
            Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),
            args[4], args[5]));
    commandMap.put("compress", args -> new CompressCommand(imageModel, Double.parseDouble(args[1]),
            args[2], args[3]));
//    commandMap.put("run", args -> new RunScriptCommand());
  }

  @Override
  public void execute(String command) throws IOException {
    String[] args = command.trim().split("\\s+");
    CommandFactory factory = commandMap.get(args[0]);
    try {
      if (factory != null) {
        Command cmd = factory.create(args);
        cmd.execute();
        view.printStatements("Successfully executed command: " + String.join(" ", args) + "\n");
      } else {
        view.printStatements("Invalid command: " + command+"\n");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void runScript(String scriptFilePath) {
    if (scriptFilePath != null) {
      // Script file mode
      try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
        String command;
        while ((command = reader.readLine()) != null) {
          command = command.trim();
          if (!command.startsWith("#") && !command.isEmpty()) {
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
    } else {
      // Interactive mode
      view.printStatements("Entering interactive mode. Type your commands below " +
              "(type 'exit' to quit):\n");

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
        String command;
        while (true) {
          view.printStatements("> ");
          command = reader.readLine();
          if (command == null || command.equalsIgnoreCase("exit")) {
            view.printStatements("Exiting interactive mode.\n");
            break;
          }
          command = command.trim();
          if (!command.isEmpty() && !command.startsWith("#")) {
            try {
              execute(command);
            } catch (Exception e) {
              System.err.println("Error executing command \"" + command + "\": " + e.getMessage());
            }
          }
        }
      } catch (IOException e) {
        System.err.println("Error reading interactive input: " + e.getMessage());
      }
    }
  }
}
