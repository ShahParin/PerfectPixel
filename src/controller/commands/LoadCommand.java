package controller.commands;


import controller.ImageService;

import java.io.IOException;

public class LoadCommand implements Command {
  private final ImageService imageService;
  private final String sourcePath;
  private final String imageName;

  public LoadCommand(ImageService imageService, String sourcePath, String imageName) {
    this.imageService = imageService;
    this.sourcePath = sourcePath;
    this.imageName = imageName;
  }

  @Override
  public void execute() throws IOException {
    try {
      imageService.loadImage(sourcePath, imageName);
    } catch (IOException e) {
      throw new IOException("Error loading image: " + e.getMessage());
    }
  }
}

