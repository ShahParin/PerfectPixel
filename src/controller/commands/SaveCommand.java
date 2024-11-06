package controller.commands;

import java.io.IOException;

import controller.ImageService;

public class SaveCommand implements Command {
  private final ImageService imageService;
  private final String destinationPath;
  private final String imageName;

  public SaveCommand(ImageService imageService, String destinationPath, String imageName) {
    this.imageService = imageService;
    this.destinationPath = destinationPath;
    this.imageName = imageName;
  }

  @Override
  public void execute() throws IOException {
    try {
      imageService.saveImage(destinationPath, imageName);
    } catch (IOException e) {
      throw new IOException("Error saving image: " + e.getMessage());
    }
  }
}
