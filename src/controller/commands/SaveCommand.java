package controller.commands;

import java.io.IOException;

import controller.ImageService;

/**
 * Command class to save the specified image.
 */
public class SaveCommand implements Command {
  private final ImageService imageService;
  private final String destinationPath;
  private final String imageName;

  /**
   * Constructor to initialize the class object.
   *
   * @param imageService    the imageService object to perform load.
   * @param destinationPath the filepath of image to save.
   * @param imageName       the name of the image.
   */
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
