package controller.commands;

import java.io.IOException;

import model.ImageModel;
import view.ConsoleBasedView;
import view.ImageView;

public class SaveCommand implements Command {
  private final ImageModel imageModel;
  private final String destinationPath;
  private final String imageName;

  public SaveCommand(ImageModel imageModel, String destinationPath, String imageName) {
    this.imageModel = imageModel;
    this.destinationPath = destinationPath;
    this.imageName = imageName;
  }

  @Override
  public void execute() {
    ImageView imageView = new ConsoleBasedView();

    try {
      imageModel.saveImage(destinationPath, imageName);

      imageView.printStatements("Saved image to " + destinationPath);
    } catch (IOException e) {
      imageView.printStatements("Error saving image: " + e.getMessage());
    }
  }
}
