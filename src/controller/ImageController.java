package controller;

/**
 * This is an Interface for the Controller. This class executes the commands provided for image
 * loading, manipulation or storing.
 */
public interface ImageController {

  /**
   * A function to accept the inputs from the view or user.
   *
   * @param scriptFilePath contains the location of the list of commands.
   */
  void runScript(String scriptFilePath);
}
