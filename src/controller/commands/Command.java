package controller.commands;

import java.io.IOException;

/**
 * Represents a command that can be executed to perform numerous image processing operations.
 */
public interface Command {

  /**
   * Executes the command to perform the specified operation.
   *
   * @throws IOException for any errors during the I\O operations.
   */
  void execute() throws IOException;
}