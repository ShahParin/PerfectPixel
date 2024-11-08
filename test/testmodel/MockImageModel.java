package testmodel;

import java.util.HashMap;
import java.util.Map;

import model.ComponentType;
import model.Image;
import model.ImageModel;

/**
 * This class is created for implementing mock model for testing the controller.
 */
public class MockImageModel implements ImageModel {

  protected final StringBuilder log = new StringBuilder();
  protected final Map<String, Image> imageStorage = new HashMap<>();

  @Override
  public void applyComponent(String imageName, String newImageName, ComponentType componentType) {
    log.append("applyComponent for ").append(componentType).append(" called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void flipHorizontally(String imageName, String newImageName) {
    log.append("flipHorizontally called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void flipVertically(String imageName, String newImageName) {
    log.append("flipVertically called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void brightenImage(int increment, String imageName, String newImageName) {
    log.append("brightenImage called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void blurImage(String imageName, String newImageName) {
    log.append("blurImage called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void sharpenImage(String imageName, String newImageName) {
    log.append("sharpenImage called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void applySepia(String imageName, String newImageName) {
    log.append("applySepia called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void rgbSplit(String imageName, String redImage, String greenImage, String blueImage) {
    log.append("rgbSplit called with imageName: ").append(imageName)
            .append(" and redImageName: ").append(redImage).append(" and greenImage: ")
            .append(greenImage).append(" and blueImage: ").append(blueImage).append("\n");

  }

  @Override
  public void rgbCombine(String newImageName, String redImage, String greenImage,
                         String blueImage) {
    log.append("rgbCombine called with newImageName: ").append(newImageName)
            .append(" and redImage: ").append(redImage).append(" and greenImage: ")
            .append(greenImage).append(" and blueImage: ").append(blueImage).append("\n");
  }

  @Override
  public Image getImage(String imgName) {
    return imageStorage.get(imgName);
  }

  @Override
  public void putImage(String imageName, Image image) {
    if (image == null) {
      System.out.println("Image is null for " + imageName);
    }
    imageStorage.put(imageName, image);
    log.append("Image with name: ").append(imageName).append(" stored.\n");
  }

  public String getLog() {
    return log.toString();
  }
}
