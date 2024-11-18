import controller.ImageService;
import controller.ViewController;
import model.ImageModelImplV2;
import model.ImageModelV2;
import view.GUIBasedView;

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
    GUIBasedView view = new GUIBasedView();
    ImageModelV2 model = new ImageModelImplV2();
    ImageService imageService = new ImageService(model);
    ViewController controller = new ViewController(model, view, imageService);

    view.setFeatures(controller);

//    if (args.length > 1 && args[0].equals("-file")) {
//      // Get the script file path from args[1]
//      String scriptPath = System.getProperty("user.dir") + File.separator + args[1];
//      File scriptFile = new File(scriptPath);
//
//      if (scriptFile.exists() && scriptFile.isFile()) {
//        // Run the script and exit if the file is valid
//        controller.runScript(scriptPath);
//      } else {
//        System.err.println("Invalid file path: " + scriptPath);
//      }
//    } else {
//      // Interactive mode
//      controller.runScript(null);
//    }
  }
}

//import java.io.File;
//
//import controller.ImageController;
//import controller.ImageService;
//import controller.TextBasedController;
//import model.ImageModelImplV2;
//import model.ImageModelV2;
//import view.ConsoleBasedView;
//import view.ImageView;
//
//public class Main {
//  public static void main(String[] args) {
//    ImageView view = new ConsoleBasedView();
//    ImageModelV2 model = new ImageModelImplV2();
//    ImageService imageService = new ImageService(model);
//    ImageController controller = new TextBasedController(model, view, imageService);
//
//    if (args.length > 1 && args[0].equals("-file")) {
//      // Get the script file path from args[1]
//      String scriptPath = System.getProperty("user.dir") + File.separator + args[1];
//      File scriptFile = new File(scriptPath);
//
//      if (scriptFile.exists() && scriptFile.isFile()) {
//        // Run the script and exit if the file is valid
//        controller.runScript(scriptPath);
//      } else {
//        System.err.println("Invalid file path: " + scriptPath);
//      }
//    } else {
//      // Interactive mode
//      controller.runScript(null);
//    }
//    if (args.length > 1 && args[0].equals("-file")) {
//      // Get the script file path from args[1]
//      String scriptPath = System.getProperty("user.dir") + File.separator + args[1];
//      File scriptFile = new File(scriptPath);
//
//      if (scriptFile.exists() && scriptFile.isFile()) {
//        // Run the script and exit if the file is valid
//        controller.runScript(scriptPath);
//      } else {
//        System.err.println("Invalid file path: " + scriptPath);
//      }
//    } else {
//      // Interactive mode
//      controller.runScript(null);
//    }
//  }
//}