package testview;

import java.awt.event.ItemEvent;
import java.io.IOException;

import controller.GUIFeatures;
import model.Image;
import view.ImageView;

public class MockGUIBasedView implements ImageView {

  private GUIFeatures features;
  private String currentImageName;
  private final StringBuilder operationLog;

  public MockGUIBasedView() {
    operationLog = new StringBuilder();
  }

  //  @Override
  public void setFeatures(GUIFeatures features) {
    this.features = features;
  }

  @Override
  public void printStatements(String message) {
    operationLog.append("MockGUIBasedView: ").append(message);
  }

  //  @Override
  public void refreshImagePlaceholder() {
    if (features != null && currentImageName != null) {
      Image image = features.getImage(currentImageName);
      operationLog.append("MockGUIBasedView: Image refreshed for ").append(currentImageName);
    } else {
      operationLog.append("MockGUIBasedView: No image to refresh.");
    }
  }

  //  @Override
  public void refreshHistogram() {
    if (features != null && currentImageName != null) {
      Image histogram = features.getHistogram(currentImageName);
      operationLog.append("MockGUIBasedView: Histogram refreshed for ").append(currentImageName);
    } else {
      operationLog.append("MockGUIBasedView: No histogram to refresh.");
    }
  }

  public void simulateAction(String command) {
    try {
      switch (command) {
        case "LOAD_IMAGE":
          features.load("mock/path/to/image.jpg", "mockImage");
          currentImageName = "mockImage";
          operationLog.append("Mock image loaded successfully.");
          refreshImagePlaceholder();
          break;
        case "SAVE_IMAGE":
          features.save("mock/path/to/save/image.jpg", currentImageName);
          operationLog.append("Mock image saved successfully.");
          break;
        case "BLUR":
          features.blur(currentImageName, "mockImage_blur");
          currentImageName = "mockImage_blur";
          refreshImagePlaceholder();
          operationLog.append("Mock image blurred.");
          break;
        case "SHARPEN":
          features.sharpen(currentImageName, "mockImage_sharpen");
          currentImageName = "mockImage_sharpen";
          refreshImagePlaceholder();
          operationLog.append("Mock image sharpened.");
          break;
        case "HORIZONTAL_FLIP":
          features.horizontalFlip(currentImageName, "mockImage_hflip");
          currentImageName = "mockImage_hflip";
          refreshImagePlaceholder();
          operationLog.append("Mock image flipped horizontally.");
          break;
        case "VERTICAL_FLIP":
          features.verticalFlip(currentImageName, "mockImage_vflip");
          currentImageName = "mockImage_vflip";
          refreshImagePlaceholder();
          operationLog.append("Mock image flipped vertically.");
          break;
        case "GRAYSCALE":
          features.greyscale(currentImageName, "mockImage_greyscale");
          currentImageName = "mockImage_greyscale";
          refreshImagePlaceholder();
          operationLog.append("Mock image converted to greyscale.");
          break;
        case "SEPIA":
          features.sepia(currentImageName, "mockImage_sepia");
          currentImageName = "mockImage_sepia";
          refreshImagePlaceholder();
          operationLog.append("Mock image converted to sepia.");
          break;
        case "RED_COMPONENT":
          features.redComponent(currentImageName, "mockImage_redComponent");
          currentImageName = "mockImage_redComponent";
          refreshImagePlaceholder();
          operationLog.append("Extracted red component from mock image.");
          break;
        case "GREEN_COMPONENT":
          features.greenComponent(currentImageName, "mockImage_greenComponent");
          currentImageName = "mockImage_greenComponent";
          refreshImagePlaceholder();
          operationLog.append("Extracted green component from mock image.");
          break;
        case "BLUE_COMPONENT":
          features.blueComponent(currentImageName, "mockImage_blueComponent");
          currentImageName = "mockImage_blueComponent";
          refreshImagePlaceholder();
          operationLog.append("Extracted blue component from mock image.");
          break;
        case "COLOR_CORRECT":
          features.colorCorrect(currentImageName, "mockImage_colorCorrect");
          currentImageName = "mockImage_colorCorrect";
          refreshImagePlaceholder();
          operationLog.append("Mock image color corrected.");
          break;
        default:
          operationLog.append("Unknown command: ").append(command);
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage());
    }
  }

  public void simulateActionCompress(String command, double percent) {
    try {
      switch (command) {
        case "COMPRESS":
          features.compress(currentImageName, "mockImage_compress", percent);
          currentImageName = "mockImage_compress";
          refreshImagePlaceholder();
          operationLog.append("Mock image compressed by ").append(percent).append("%.");
          break;
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage());
    }
  }

  public void simulateActionLevelsAdjust(String command, int b, int m, int w) {
    try {
      switch (command) {
        case "LEVELS_ADJUST":
          features.levelsAdjust(currentImageName, "mockImage_levelsAdjust", b,m,w);
          currentImageName = "mockImage_levelsAdjust";
          refreshImagePlaceholder();
          operationLog.append("Mock image levels adjusted by b = ").append(b).append(" m = ")
                  .append(m).append(" w = ").append(w).append(".");
          break;
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage());
    }
  }

  public String getOperationLog() {
    return String.valueOf(operationLog);
  }

  //  @Override
  public void updateOperationLog(String message) {
    return;
  }

  // Simulate item state change if needed
  public void simulateItemStateChange(ItemEvent e) {
    operationLog.append("MockGUIBasedView: Item state changed - ").append(e.getItem());
  }
}

