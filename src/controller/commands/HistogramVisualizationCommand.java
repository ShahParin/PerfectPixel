package controller.commands;

import model.ImageModelV2;
import view.ConsoleBasedView;
import view.ImageView;

public class HistogramVisualizationCommand implements Command {
  private final ImageModelV2 imageModel;
  private final String imageName;
  private final String newImageName;

  public HistogramVisualizationCommand(ImageModelV2 imageModel, String imageName, String newImageName) {
    this.imageModel = imageModel;
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void execute() {
    imageModel.applyHistogramVisualization(imageName, newImageName);

    ImageView imageView = new ConsoleBasedView();
    imageView.printStatements("Applied histogram visualization to " + imageName);
  }
}
