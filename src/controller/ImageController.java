package controller;

import java.io.IOException;

public interface ImageController {
  public void execute(String command) throws IOException;

  public void runScript (String scriptFilePath);
}
