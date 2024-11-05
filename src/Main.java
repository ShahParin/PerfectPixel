import java.io.File;

import controller.ImageController;
import controller.TextBasedController;
import model.ImageModel;
import model.ImageModelImpl;
import model.ImageModelImplV2;
import model.ImageModelV2;
import view.ConsoleBasedView;
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
//    ImageModel model = new ImageModelImpl();
    ImageView view = new ConsoleBasedView();
    ImageModelV2 model = new ImageModelImplV2();
    ImageController controller = new TextBasedController(model, view);
    if (args.length > 0) {
      String scriptPath = new File(System.getProperty("user.dir")) + File.separator + args[0];
      controller.runScript(scriptPath);
    } else {
//      String scriptPath = new File(System.getProperty("user.dir")) + File.separator
//              + "DefaultScript.txt";
      String scriptPath = new File(System.getProperty("user.dir")) + File.separator + "RunScript2.txt";
      controller.runScript(scriptPath);
    }

//    try {
//      controller.execute("load /input/sample.png image1");
////      controller.execute("red-component image1 red_image");
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }
}
