package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GUIFeatures;
import model.Image;
import view.components.GenericPanel;
import view.sections.ColorChannelsSection;
import view.sections.ColorCorrectionSection;
import view.sections.CompressionSection;
import view.sections.FileIOSection;
import view.sections.FiltersSection;
import view.sections.FlippingSection;
import view.sections.HistogramSection;
import view.sections.ImageDialogSection;
import view.sections.ImageDisplaySection;
import view.sections.LevelsAdjustmentSection;
import view.sections.OperationLogSection;
import view.sections.SplitOperationSection;
import view.sections.TransformationsSection;

import static model.ImageUtil.getDimensions;

/**
 * GUIBasedView class provides the graphical user interface (GUI) for the PerfectPixel
 * image editing application.
 * Implements the ImageView interface to update the UI based on the image operations.
 */
public class GUIBasedView extends JFrame implements ImageView, ActionListener {
  private GUIFeatures features;
  private String currentImageName;
  private String tempImageName;
  private boolean levelAdjustParam;
  private final ArrayList<String> savedImageNames;

  private FileIOSection fileIOSection;
  private FiltersSection filtersSection;
  private FlippingSection flippingSection;
  private TransformationsSection transformationsSection;
  private ColorChannelsSection colorChannelsSection;
  private CompressionSection compressionSection;
  private LevelsAdjustmentSection levelsAdjustmentSection;
  private ColorCorrectionSection colorCorrectionSection;
  private OperationLogSection operationLogSection;
  private HistogramSection histogramSection;
  private ImageDisplaySection imageDisplaySection;
  private SplitOperationSection splitOperationSection;

  /**
   * Constructor for initializing the GUIBasedView.
   * Sets up the frame, initializes components, and sets the layout.
   */
  public GUIBasedView() {
    super("PerfectPixel");

    levelAdjustParam = false;
    savedImageNames = new ArrayList<>();

    setSize(1300, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initializeComponents();
    setupLayout();

    setVisible(true);
  }

  private void initializeComponents() {
    fileIOSection = new FileIOSection(this);
    filtersSection = new FiltersSection(this);
    flippingSection = new FlippingSection(this);
    transformationsSection = new TransformationsSection(this);
    colorChannelsSection = new ColorChannelsSection(this);
    compressionSection = new CompressionSection(this);
    levelsAdjustmentSection = new LevelsAdjustmentSection(this);
    colorCorrectionSection = new ColorCorrectionSection(this);
    operationLogSection = new OperationLogSection();
    histogramSection = new HistogramSection();
    splitOperationSection = new SplitOperationSection(this);
    imageDisplaySection = new ImageDisplaySection();
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    // Left panel (commands and operations)
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(8, 1));
    leftPanel.setPreferredSize(new Dimension(350, 800));
    leftPanel.add(fileIOSection);
    leftPanel.add(filtersSection);
    leftPanel.add(flippingSection);
    leftPanel.add(transformationsSection);
    leftPanel.add(colorChannelsSection);
    leftPanel.add(compressionSection);
    leftPanel.add(levelsAdjustmentSection);
    leftPanel.add(colorCorrectionSection);

    // Right panel (image display and additional info)
    GenericPanel rightPanel = new GenericPanel(new BorderLayout());
    rightPanel.setPreferredSize(new Dimension(750, 800));

    // Top section (operation log and histogram)
    GenericPanel topPanel = new GenericPanel(new BorderLayout());
    topPanel.add(operationLogSection, BorderLayout.WEST);
    topPanel.add(histogramSection, BorderLayout.EAST);
    topPanel.add(splitOperationSection, BorderLayout.CENTER);
    rightPanel.add(topPanel, BorderLayout.NORTH);

    // Bottom section (image display)
    rightPanel.add(imageDisplaySection, BorderLayout.CENTER);
    // Main scroll pane
    JScrollPane mainScrollPane = new JScrollPane(rightPanel);

    // Add panels to the frame
    add(leftPanel, BorderLayout.WEST);
    add(mainScrollPane, BorderLayout.CENTER);
  }

  @Override
  public void setFeatures(GUIFeatures features) {
    this.features = features;
  }

  private BufferedImage imageToBufferedImage(Image image) {
    int height = getDimensions(image)[0];
    int width = getDimensions(image)[1];

    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    int[] rgbData = new int[width * height];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = redChannel[y][x];
        int green = greenChannel[y][x];
        int blue = blueChannel[y][x];
        rgbData[y * width + x] = (red << 16) | (green << 8) | blue;
      }
    }

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    outputImage.setRGB(0, 0, width, height, rgbData, 0, width);

    return outputImage;
  }

  private void refreshImagePlaceholder() {
    Image image = features.getImage(currentImageName);
    String savedCheck;

    if (savedImageNames.contains(currentImageName)) {
      savedCheck = " -saved";
    } else {
      savedCheck = " -not saved";
    }

    imageDisplaySection.updateImageDisplay(new ImageIcon(imageToBufferedImage(image)), savedCheck);
    refreshHistogram();
  }

  private void refreshHistogram() {
    Image histogramImage = features.getHistogram(currentImageName);

    histogramSection.updateHistogramDisplay(new ImageIcon(imageToBufferedImage(histogramImage)));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "LOAD_IMAGE":
        handleLoadImage();
        break;
      case "SAVE_IMAGE":
        handleSaveImage();
        break;
      case "BLUR":
        handleBlurImage();
        break;
      case "SHARPEN":
        handleSharpenImage();
        break;
      case "HORIZONTAL_FLIP":
        handleHorizontalFlip();
        break;
      case "VERTICAL_FLIP":
        handleVerticalFlip();
        break;
      case "GRAYSCALE":
        handleGreyscale();
        break;
      case "SEPIA":
        handleSepia();
        break;
      case "RED_COMPONENT":
        handleRedComponent();
        break;
      case "GREEN_COMPONENT":
        handleGreenComponent();
        break;
      case "BLUE_COMPONENT":
        handleBlueComponent();
        break;
      case "COMPRESS":
        handleCompress();
        break;
      case "LEVELS_ADJUST":
        handleLevelsAdjust();
        break;
      case "COLOR_CORRECT":
        handleColorCorrect();
        break;
      case "APPLY_SPLIT":
        handleSplitOperation();
        break;
      case "APPLY_SAVE_SPLIT":
        handleSaveSplit();
        break;
      case "APPLY_CLOSE_SPLIT":
        handleCloseSplit();
        break;
      default:
        printStatements("Unknown command: " + e.getActionCommand());
    }
  }

  private void handleSaveSplit() {
    if (currentImageName != null && tempImageName != null) {
      try {
        String selectedOption = splitOperationSection.getSelectedOperation();
        if (selectedOption == null) {
          printStatements("No operation selected.");
          return;
        }
        printStatements("Selected operation: " + selectedOption);
        if (selectedOption.equals("blur")) {
          handleBlurImage();
          printStatements("Blur operation performing after split");
        }
        if (selectedOption.equals("sharpen")) {
          handleSharpenImage();
          printStatements("Sharpen operation performing after split");
        }
        if (selectedOption.equals("sepia")) {
          handleSepia();
          printStatements("Sepia operation performing after split");
        }
        if (selectedOption.equals("greyscale")) {
          handleGreyscale();
          printStatements("Greyscale operation performing after split");
        }
        if (selectedOption.equals("color correction")) {
          handleColorCorrect();
          printStatements("Color correction operation performing after split");
        }
        if (selectedOption.equals("levels adjustment")) {
          levelAdjustParam = true;
          handleLevelsAdjust();
          printStatements("Levels adjustment operation performing after split");
        }
        printStatements("Image Updated successfully.");
      } catch (Exception ex) {
        printStatements("Error during save split operation: " + ex.getMessage());

      }
    } else {
      printStatements("No image loaded or features not set");
    }

  }

  private void handleCloseSplit() {
    if (currentImageName != null && tempImageName != null) {
      try {
        printStatements("Split Operation Preview Closed.");
      } catch (Exception ex) {
        printStatements("Error during close split operation: " + ex.getMessage());

      }
    } else {
      printStatements("No image loaded or features not set");
    }

  }

  private void handleSplitOperation() {
    try {
      String selectedOption = splitOperationSection.getSelectedOperation();
      if (selectedOption == null) {
        printStatements("No operation selected.");
        return;
      }
      double inputValue = splitOperationSection.getInputValue();

      if (selectedOption.equals("levels adjustment")) {
        int[] levelsValues = splitOperationSection.getLevelsAdjustmentValues();
        int black = levelsValues[0];
        int mid = levelsValues[1];
        int white = levelsValues[2];

        if (features != null && currentImageName != null) {
          String outputImageName = currentImageName + "_levels_adjusted";
          features.levelsAdjustmentSplitOperation(currentImageName, outputImageName, black, mid, white, inputValue);

          tempImageName = outputImageName;

          Image updatedImage = features.getImage(tempImageName);

          if (updatedImage != null) {
            ImageDialogSection.showImageDialog(this, new ImageIcon(imageToBufferedImage(updatedImage)), this);
          } else {
            printStatements("Unable to fetch updated image: " + outputImageName);
          }

          printStatements("Levels adjustment applied: black=" + black + ", mid=" + mid + ", white=" + white);
        } else {
          printStatements("No image loaded or features not set.");
        }
      } else {
        if (features != null && currentImageName != null) {
          String outputImageName = currentImageName + "_split";

          switch (selectedOption) {
            case "blur":
              features.blurSplitOperation(currentImageName, outputImageName, inputValue);
              break;
            case "sharpen":
              features.sharpenSplitOperation(currentImageName, outputImageName, inputValue);
              break;
            case "sepia":
              features.sepiaSplitOperation(currentImageName, outputImageName, inputValue);
              break;
            case "greyscale":
              features.greyscaleSplitOperation(currentImageName, outputImageName, inputValue);
              break;
            case "color correction":
              features.colorCorrectionSplitOperation(currentImageName, outputImageName, inputValue);
              break;
            default:
              printStatements("Operation not implemented: " + selectedOption);
              return;
          }

          tempImageName = outputImageName;

          // Fetch the updated image as an ImageIcon
          Image updatedImage = features.getImage(tempImageName);

          if (updatedImage != null) {
            // Display the updated image in the dialog
            ImageDialogSection.showImageDialog(this, new ImageIcon(imageToBufferedImage(updatedImage)), this);
          } else {
            printStatements("Unable to fetch updated image: " + outputImageName);
          }

          printStatements("Split operation applied: " + selectedOption);
        } else {
          printStatements("No image loaded or features not set.");
        }
      }
    } catch (IllegalArgumentException ex) {
      printStatements("Input Error: " + ex.getMessage());
    } catch (Exception ex) {
      printStatements("Unexpected error: " + ex.getMessage());
    }
  }

  private void handleLoadImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
    fileChooser.setFileFilter(filter);

    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      currentImageName = file.getName().replace(".", "_");

      printStatements("Loaded from: " + file.getAbsolutePath());

      if (features != null && currentImageName != null) {
        try {
          features.load(file.getAbsolutePath(), currentImageName);
        } catch (IOException ex) {
          printStatements("Error: " + ex.getMessage());
        }
      } else {
        printStatements("Error: Features not set");
      }

      refreshImagePlaceholder();
    }
  }

  private void handleSaveImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
    fileChooser.setFileFilter(filter);

    int returnValue = fileChooser.showSaveDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      printStatements("Saving to: " + file.getAbsolutePath());

      if (features != null && currentImageName != null) {
        try {
          features.save(file.getAbsolutePath(), currentImageName);
          savedImageNames.add(currentImageName);
          refreshImagePlaceholder();
        } catch (IOException ex) {
          printStatements("Error: " + ex.getMessage());
        }
      } else {
        printStatements("Error: No image to save or features not set");
      }
    }
  }

  private void handleBlurImage() {
    if (features != null && currentImageName != null) {
      try {
        String blurredImageName = currentImageName + "_blur";
        features.blur(currentImageName, blurredImageName);

        currentImageName = blurredImageName;
        refreshImagePlaceholder();

        printStatements("Image blurred successfully.");
      } catch (Exception ex) {
        printStatements("Error during blur operation: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleSharpenImage() {
    if (features != null && currentImageName != null) {
      try {
        String sharpenImageName = currentImageName + "_sharpen";
        features.sharpen(currentImageName, sharpenImageName);

        currentImageName = sharpenImageName;
        refreshImagePlaceholder();

        printStatements("Image blurred successfully.");
      } catch (Exception ex) {
        printStatements("Error during blur operation: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleHorizontalFlip() {
    if (features != null && currentImageName != null) {
      try {
        String flippedImageName = currentImageName + "_hflip";
        features.horizontalFlip(currentImageName, flippedImageName);
        currentImageName = flippedImageName;
        refreshImagePlaceholder();
        printStatements("Image flipped horizontally.");
      } catch (Exception ex) {
        printStatements("Error during horizontal flip: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleVerticalFlip() {
    if (features != null && currentImageName != null) {
      try {
        String flippedImageName = currentImageName + "_vflip";
        features.verticalFlip(currentImageName, flippedImageName);
        currentImageName = flippedImageName;
        refreshImagePlaceholder();
        printStatements("Image flipped vertically.");
      } catch (Exception ex) {
        printStatements("Error during vertical flip: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleGreyscale() {
    if (features != null && currentImageName != null) {
      try {
        String greyImageName = currentImageName + "_greyscale";
        features.greyscale(currentImageName, greyImageName);
        currentImageName = greyImageName;
        refreshImagePlaceholder();
        printStatements("Image converted to greyscale.");
      } catch (Exception ex) {
        printStatements("Error during greyscale operation: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleSepia() {
    if (features != null && currentImageName != null) {
      try {
        String sepiaImageName = currentImageName + "_sepia";
        features.sepia(currentImageName, sepiaImageName);
        currentImageName = sepiaImageName;
        refreshImagePlaceholder();
        printStatements("Image converted to sepia tone.");
      } catch (Exception ex) {
        printStatements("Error during sepia operation: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleRedComponent() {
    if (features != null && currentImageName != null) {
      try {
        String redImageName = currentImageName + "_red";
        features.redComponent(currentImageName, redImageName);
        currentImageName = redImageName;
        refreshImagePlaceholder();
        printStatements("Red component extracted.");
      } catch (Exception ex) {
        printStatements("Error extracting red component: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleGreenComponent() {
    if (features != null && currentImageName != null) {
      try {
        String greenImageName = currentImageName + "_green";
        features.greenComponent(currentImageName, greenImageName);
        currentImageName = greenImageName;
        refreshImagePlaceholder();
        printStatements("Green component extracted.");
      } catch (Exception ex) {
        printStatements("Error extracting green component: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleBlueComponent() {
    if (features != null && currentImageName != null) {
      try {
        String blueImageName = currentImageName + "_blue";
        features.blueComponent(currentImageName, blueImageName);
        currentImageName = blueImageName;
        refreshImagePlaceholder();
        printStatements("Blue component extracted.");
      } catch (Exception ex) {
        printStatements("Error extracting blue component: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleCompress() {
    if (features != null && currentImageName != null) {
      try {
        double percent = compressionSection.getPercent();

        String compressedImageName = currentImageName + "_compressed";
        features.compress(currentImageName, compressedImageName, percent);

        currentImageName = compressedImageName;
        refreshImagePlaceholder();
        printStatements("Image compressed successfully.");
      } catch (Exception ex) {
        printStatements("Error during compression: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleLevelsAdjust() {
    if (features != null && currentImageName != null) {
      try {
        if (levelAdjustParam) {
          int[] levelsValues = splitOperationSection.getLevelsAdjustmentValues();
          int black = levelsValues[0];
          int mid = levelsValues[1];
          int white = levelsValues[2];

          String adjustedImageName = currentImageName + "_levelsAdjusted";
          features.levelsAdjust(currentImageName, adjustedImageName, black, mid, white);

          currentImageName = adjustedImageName;
        } else {
          int black = levelsAdjustmentSection.getBlack();
          int midTones = levelsAdjustmentSection.getMidTone();
          int white = levelsAdjustmentSection.getWhite();

          String adjustedImageName = currentImageName + "_levelsAdjusted";
          features.levelsAdjust(currentImageName, adjustedImageName, black, midTones, white);

          currentImageName = adjustedImageName;
        }

        refreshImagePlaceholder();
        printStatements("Levels adjusted successfully.");

      } catch (NumberFormatException ex) {
        printStatements("Invalid input for levels adjustment. Please enter numeric values.");
      } catch (Exception ex) {
        printStatements("Error during levels adjustment: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  private void handleColorCorrect() {
    if (features != null && currentImageName != null) {
      try {
        String correctedImageName = currentImageName + "_corrected";
        features.colorCorrect(currentImageName, correctedImageName);
        currentImageName = correctedImageName;
        refreshImagePlaceholder();
        printStatements("Image color corrected.");
      } catch (Exception ex) {
        printStatements("Error during color correction: " + ex.getMessage());
      }
    } else {
      printStatements("No image loaded or features not set.");
    }
  }

  @Override
  public void printStatements(String message) {
    operationLogSection.appendLog(message);
  }
}
