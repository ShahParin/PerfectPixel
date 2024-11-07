package controller.commands;

import java.io.IOException;

import controller.ImageService;

/**
 * Command class to load an image.
 */
public class LoadCommand implements Command {
  private final ImageService imageService;
  private final String sourcePath;
  private final String imageName;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageService the imageService object to perform load.
   * @param sourcePath   the filepath of image to load.
   * @param imageName    the name of the image.
   */
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
