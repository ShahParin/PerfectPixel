import java.io.File;

import controller.ImageController;
import model.ImageModel;
import model.ImageModelImpl;

public class Main {
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    ImageController controller = new ImageController(model);
    if (args.length > 0) {
//      System.out.println(args[0]);
      String scriptPath= System.getProperty("user.dir") + File.separator + args[0];

      controller.runScript(scriptPath);
    }
    else {
      String scriptPath= System.getProperty("user.dir") + File.separator + "DefaultScript.txt";
      controller.runScript(scriptPath);
    }
  }
}
