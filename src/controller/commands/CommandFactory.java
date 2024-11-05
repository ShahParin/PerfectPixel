package controller.commands;

public interface CommandFactory {
  Command create(String[] args);
}
