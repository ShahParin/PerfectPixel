import java.io.File;

import controller.ImageController;
import controller.TextBasedController;
import model.ImageModel;
import model.ImageModelImpl;
import view.ConsoleBasedView;
import view.ImageView;

public class Main {
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    ImageView view = new ConsoleBasedView();
    ImageController controller = new TextBasedController(model,view);
    if (args.length > 0) {
      String scriptPath = new File(System.getProperty("user.dir")) + File.separator + args[0];
//      String scriptPath = new File(System.getProperty("user.dir")).getParent() + File.separator + args[0];
      System.out.println(scriptPath);
      controller.runScript(scriptPath);
    }
    else {
      String scriptPath= new File(System.getProperty("user.dir")) + File.separator + "DefaultScript.txt";
//      String scriptPath= new File(System.getProperty("user.dir")).getParent() + File.separator + "DefaultScript.txt";
      controller.runScript(scriptPath);
    }
  }
}
