package testview;

import java.io.IOException;
import java.util.Objects;

import controller.GUIFeatures;
import view.ImageView;

public class MockGUIBasedView implements ImageView {

  private GUIFeatures features;
  private String currentImageName;
  private final StringBuilder operationLog;
  private String saveSplitOperation;
  private double saveSplitPercent;

  public MockGUIBasedView() {
    operationLog = new StringBuilder();
    saveSplitOperation = "";
  }

  //  @Override
  public void setFeatures(GUIFeatures features) {
    this.features = features;
  }

  @Override
  public void printStatements(String message) {
    operationLog.append("MockGUIBasedView: ").append(message).append("\n");
  }

  //  @Override
  public void refreshImagePlaceholder() {
    if (features != null && currentImageName != null) {
      operationLog.append("MockGUIBasedView: Image refreshed for ").append(currentImageName)
              .append("\n");
      refreshHistogram();
    } else {
      operationLog.append("MockGUIBasedView: No image to refresh.").append("\n");
    }
  }

  //  @Override
  public void refreshHistogram() {
    if (features != null && currentImageName != null) {
      operationLog.append("MockGUIBasedView: Histogram refreshed for ").append(currentImageName)
              .append("\n");
    } else {
      operationLog.append("MockGUIBasedView: No histogram to refresh.").append("\n");
    }
  }

  public void simulateAction(String command) {
    try {
      switch (command) {
        case "LOAD_IMAGE":
          features.load("mock/path/to/image.jpg", "mockImage");
          currentImageName = "mockImage";
          operationLog.append("Mock image loaded successfully.").append("\n");
          refreshImagePlaceholder();
          break;
        case "SAVE_IMAGE":
          if (features != null && currentImageName != null) {
            features.save("mock/path/to/save/image.jpg", currentImageName);
            operationLog.append("Mock image saved successfully.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "BLUR":
          if (features != null && currentImageName != null) {
            features.blur(currentImageName, "mockImage_blur");
            currentImageName = "mockImage_blur";
            refreshImagePlaceholder();
            operationLog.append("Mock image blurred.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "SHARPEN":
          if (features != null && currentImageName != null) {
            features.sharpen(currentImageName, "mockImage_sharpen");
            currentImageName = "mockImage_sharpen";
            refreshImagePlaceholder();
            operationLog.append("Mock image sharpened.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "HORIZONTAL_FLIP":
          if (features != null && currentImageName != null) {
            features.horizontalFlip(currentImageName, "mockImage_hflip");
            currentImageName = "mockImage_hflip";
            refreshImagePlaceholder();
            operationLog.append("Mock image flipped horizontally.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "VERTICAL_FLIP":
          if (features != null && currentImageName != null) {
            features.verticalFlip(currentImageName, "mockImage_vflip");
            currentImageName = "mockImage_vflip";
            refreshImagePlaceholder();
            operationLog.append("Mock image flipped vertically.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "GRAYSCALE":
          if (features != null && currentImageName != null) {
            features.greyscale(currentImageName, "mockImage_greyscale");
            currentImageName = "mockImage_greyscale";
            refreshImagePlaceholder();
            operationLog.append("Mock image converted to greyscale.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "SEPIA":
          if (features != null && currentImageName != null) {
            features.sepia(currentImageName, "mockImage_sepia");
            currentImageName = "mockImage_sepia";
            refreshImagePlaceholder();
            operationLog.append("Mock image converted to sepia.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "RED_COMPONENT":
          if (features != null && currentImageName != null) {
            features.redComponent(currentImageName, "mockImage_redComponent");
            currentImageName = "mockImage_redComponent";
            refreshImagePlaceholder();
            operationLog.append("Extracted red component from mock image.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "GREEN_COMPONENT":
          if (features != null && currentImageName != null) {
            features.greenComponent(currentImageName, "mockImage_greenComponent");
            currentImageName = "mockImage_greenComponent";
            refreshImagePlaceholder();
            operationLog.append("Extracted green component from mock image.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "BLUE_COMPONENT":
          if (features != null && currentImageName != null) {
            features.blueComponent(currentImageName, "mockImage_blueComponent");
            currentImageName = "mockImage_blueComponent";
            refreshImagePlaceholder();
            operationLog.append("Extracted blue component from mock image.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "COLOR_CORRECT":
          if (features != null && currentImageName != null) {
            features.colorCorrect(currentImageName, "mockImage_colorCorrect");
            currentImageName = "mockImage_colorCorrect";
            refreshImagePlaceholder();
            operationLog.append("Mock image color corrected.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "APPLY_SAVE_SPLIT":
          if (features != null && currentImageName != null) {
            if (Objects.equals(saveSplitOperation, "")) {
              operationLog.append("Mock image split operation failed.").append("\n");
            } else {
              simulateSplitOperations(saveSplitOperation, saveSplitPercent);
              operationLog.append("Image operation saved.").append("\n");
            }
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        case "APPLY_CLOSE_SPLIT":
          if (features != null && currentImageName != null) {
            operationLog.append("Pop-up window closed.").append("\n");
          } else {
            operationLog.append("No image loaded or features not set.\n");
          }
          break;
        default:
          operationLog.append("Unknown command: ").append(command).append("\n");
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage()).append("\n");
    }

  }

  public void simulateActionCompress(String command, double percent) {
    try {
      if (command.equals("COMPRESS")) {
        features.compress(currentImageName, "mockImage_compress", percent);
        currentImageName = "mockImage_compress";
        refreshImagePlaceholder();
        operationLog.append("Mock image compressed by ").append(percent).append("%.").append("\n");
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage()).append("\n");
    }
  }

  public void simulateActionLevelsAdjust(String command, int b, int m, int w) {
    try {
      if (command.equals("LEVELS_ADJUST")) {
        features.levelsAdjust(currentImageName, "mockImage_levelsAdjust", b, m, w);
        currentImageName = "mockImage_levelsAdjust";
        refreshImagePlaceholder();
        operationLog.append("Mock image levels adjusted by b = ").append(b).append(" m = ")
                .append(m).append(" w = ").append(w).append(".").append("\n");
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage()).append("\n");
    }
  }

  public void simulateSplitOperations(String command, double percent) {
    try {
      switch (command) {
        case "BLUR":
          features.blurSplitOperation(currentImageName, "mockImage_split_blur",
                  percent);
          currentImageName = "mockImage_split_blur";
          refreshImagePlaceholder();
          operationLog.append("Mock image split operation for blur by ").append(percent)
                  .append("%.").append("\n");
          break;
        case "SHARPEN":
          features.sharpenSplitOperation(currentImageName, "mockImage_split_sharpen",
                  percent);
          currentImageName = "mockImage_split_sharpen";
          refreshImagePlaceholder();
          operationLog.append("Mock image split operation for sharpen by ").append(percent)
                  .append("%.").append("\n");
          break;
        case "SEPIA":
          features.sepiaSplitOperation(currentImageName, "mockImage_split_sepia",
                  percent);
          currentImageName = "mockImage_split_sepia";
          refreshImagePlaceholder();
          operationLog.append("Mock image split operation for sepia by ").append(percent)
                  .append("%.").append("\n");
          break;
        case "GREYSCALE":
          features.greyscaleSplitOperation(currentImageName, "mockImage_split_greyscale",
                  percent);
          currentImageName = "mockImage_split_greyscale";
          refreshImagePlaceholder();
          operationLog.append("Mock image split operation for greyscale by ").append(percent)
                  .append("%.").append("\n");
          break;
        case "COLOR_CORRECT":
          features.colorCorrectionSplitOperation(currentImageName,
                  "mockImage_split_colorCorrect", percent);
          currentImageName = "mockImage_split_colorCorrect";
          refreshImagePlaceholder();
          operationLog.append("Mock image split operation for color correct by ").append(percent)
                  .append("%.").append("\n");
          break;
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage()).append("\n");
    }
  }

  public void simulateOperationSplitLevelsAdjust(String command, int b, int m, int w,
                                                 double percent) {
    try {
      if (command.equals("LEVELS_ADJUST")) {
        features.levelsAdjustmentSplitOperation(currentImageName,
                "mockImage_split_levelsAdjust", b, m, w, percent);
        currentImageName = "mockImage_split_levelsAdjust";
        refreshImagePlaceholder();
        operationLog.append("Mock image levels adjusted by b = ").append(b).append(" m = ")
                .append(m).append(" w = ").append(w).append(".").append("\n");
      }
    } catch (IOException e) {
      operationLog.append("Error simulating action: ").append(e.getMessage()).append("\n");
    }
  }

  public void setSaveSplitOperation(String command, double percent) {
    this.saveSplitOperation = command;
    this.saveSplitPercent = percent;
  }

  public String getOperationLog() {
    return String.valueOf(operationLog);
  }
}

