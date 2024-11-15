package controller;

import java.io.IOException;

public interface GUIFeatures {
  void load(String path, String imageName) throws IOException;

  void save(String path, String imageName) throws IOException;

//  void displayImage(String imageName);
}
