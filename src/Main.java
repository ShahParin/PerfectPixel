import java.io.File;

import controller.ImageController;
import controller.ImageService;
import controller.TextBasedControllerV3;
import controller.ViewController;
import model.ImageModelImplV2;
import model.ImageModelImplV3;
import model.ImageModelV2;
import model.ImageModelV3;
import view.ConsoleBasedView;
import view.GUIBasedView;
import view.ImageView;

/**
 * This class contains the main function of the application.
 * The controller will be triggered from here.
 */
public class Main {

  /**
   * This is the main function of the application, the controller will be triggered from here.
   *
   * @param args Input Arguments.
   */
  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("-file")) {
      initializeFileMode(args.length > 1 ? args[1] : null);
    } else if (args.length > 0 && args[0].equals("-text")) {
      initializeInteractiveMode();
    } else {
      initializeGUIMode();
    }
  }

  /**
   * Initializes the application in file mode.
   *
   * @param scriptFilePath Path to the script file.
   */
  private static void initializeFileMode(String scriptFilePath) {
    if (scriptFilePath == null) {
      System.err.println("No script file path provided for file mode.");
      return;
    }

    ImageView view = new ConsoleBasedView();
    ImageModelV3 model = new ImageModelImplV3();

    ImageModelV2 model1 = new ImageModelImplV2();
    ImageService imageService = new ImageService(model);
    ImageController controller = new TextBasedControllerV3(model, model1, view, imageService);

    String scriptPath = System.getProperty("user.dir") + File.separator + scriptFilePath;
    File scriptFile = new File(scriptPath);

    if (scriptFile.exists() && scriptFile.isFile()) {
      controller.runScript(scriptPath);
    } else {
      System.err.println("Invalid file path: " + scriptPath);
    }
  }

  /**
   * Initializes the application in interactive text-based mode.
   */
  private static void initializeInteractiveMode() {
    ImageView view = new ConsoleBasedView();
    ImageModelV3 model = new ImageModelImplV3();
    ImageModelV2 model1 = new ImageModelImplV2();
    ImageService imageService = new ImageService(model);

    ImageController controller = new TextBasedControllerV3(model, model1, view, imageService);
    controller.runScript(null);
  }

  /**
   * Initializes the application in GUI mode.
   */
  private static void initializeGUIMode() {
    GUIBasedView view = new GUIBasedView();
    ImageModelV2 model = new ImageModelImplV2();
    ImageService imageService = new ImageService(model);
    ViewController controller = new ViewController(model, view, imageService);

    view.setFeatures(controller);
  }
}
