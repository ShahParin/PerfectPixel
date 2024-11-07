package controller.commands;

/**
 * A factory interface for creating command instances based on provided arguments.
 */
public interface CommandFactory {

  /**
   * Creates a new command object based on the provided arguments.
   *
   * @param args array of strings containing the required arguments.
   * @return a command object instance based on the specified arguments.
   * @throws IllegalArgumentException if the command arguments are invalid.
   */
  Command create(String[] args);
}
