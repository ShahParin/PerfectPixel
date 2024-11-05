package controller.commands;



import model.ImageModel;
import view.ConsoleBasedView;
import view.ImageView;

import java.io.IOException;

public class LoadCommand implements Command {
  private final ImageModel imageModel;
  private final ImageView view;
  private final String sourcePath;
  private final String imageName;

  public LoadCommand(ImageModel imageModel, ImageView view, String sourcePath, String imageName) {
    this.imageModel = imageModel;
    this.view = view;
    this.sourcePath = sourcePath;
    this.imageName = imageName;
  }

  @Override
  public void execute() {
    try {
      imageModel.loadImage(sourcePath, imageName);

      view.printStatements("Loaded image from " + sourcePath);
    } catch (IOException e) {
      view.printStatements("Error loading image: " + e.getMessage());
    }
  }
}

