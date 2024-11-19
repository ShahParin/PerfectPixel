//package view;
//
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import controller.GUIFeatures;
//import model.Image;
//
//import static model.ImageUtil.getDimensions;
//
//public class GUIBasedView extends JFrame implements ImageView, ActionListener, ItemListener {
//  private JLabel fileOpenDisplay;
//  private JLabel fileSaveDisplay;
//  private JLabel imagePlaceholder;
//  private JPanel mainPanel;
//  private JScrollPane mainScrollPane;
//  private GUIFeatures features;
//  private String currentImageName;
//
//  public GUIBasedView() {
//    super("PerfectPixel");
//    setSize(1200, 800);
//
//    mainPanel = new JPanel();
//    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
//    mainScrollPane = new JScrollPane(mainPanel);
//    add(mainScrollPane);
//
//    // Load Image Button
//    JButton fileOpenButton = new JButton("Open a file");
//    fileOpenButton.setActionCommand("Open file");
//    fileOpenButton.addActionListener(this);
//    mainPanel.add(fileOpenButton);
//
//    // Text after loading
//    fileOpenDisplay = new JLabel("File path will appear here");
//    mainPanel.add(fileOpenDisplay);
//
//    // Save Image Button
//    JButton fileSaveButton = new JButton("Save a file");
//    fileSaveButton.setActionCommand("Save file");
//    fileSaveButton.addActionListener(this);
//    mainPanel.add(fileSaveButton);
//
//    // Text after saving
//    fileSaveDisplay = new JLabel("File path will appear here");
//    mainPanel.add(fileSaveDisplay);
//
//    // Image display placeholder
//    imagePlaceholder = new JLabel();
//    imagePlaceholder.setHorizontalAlignment(JLabel.CENTER);
//    imagePlaceholder.setVerticalAlignment(JLabel.CENTER);
//    mainPanel.add(imagePlaceholder);
//
//    // Setting close button action
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setVisible(true);
//  }
//
//  public void setFeatures(GUIFeatures features) {
//    this.features = features;
//  }
//
//  // Method to refresh the image placeholder with a new image
//  public void refreshImagePlaceholder(Image image) {
//    int height = getDimensions(image)[0];
//    int width = getDimensions(image)[1];
//
//    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//    for (int y = 0; y < height; y++) {
//      for (int x = 0; x < width; x++) {
//        int red = image.getRedChannel()[y][x];
//        int green = image.getGreenChannel()[y][x];
//        int blue = image.getBlueChannel()[y][x];
//
//        int rgb = (red << 16) | (green << 8) | blue;
//        outputImage.setRGB(x, y, rgb);
//      }
//    }
//
//    ImageIcon icon = new ImageIcon(outputImage);
//    System.out.println("icon "+icon);
//    java.awt.Image img = icon.getImage().getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);  // Scale to fit placeholder
//    icon = new ImageIcon(img);
//    imagePlaceholder.setIcon(icon);
//  }
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    switch (e.getActionCommand()) {
//      case "Open file": {
//        JFileChooser fchooser = new JFileChooser(".");
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                "Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
//        fchooser.setFileFilter(filter);
//
//        int retvalue = fchooser.showOpenDialog(this);
//        if (retvalue == JFileChooser.APPROVE_OPTION) {
//          File f = fchooser.getSelectedFile();
//          fileOpenDisplay.setText("Loading: " + f.getAbsolutePath());
//
//          // Interact with controller to load image
//          currentImageName = f.getName().replace(".", "_");
//          if (features != null) {
//            try {
//              features.load(f.getAbsolutePath(), currentImageName);
//            } catch (IOException ex) {
//              throw new RuntimeException(ex);
//            }
//          } else {
//            printStatements("Error: Features not set");
//          }
//        }
//        break;
//      }
//
//      case "Save file": {
//        JFileChooser fchooser = new JFileChooser(".");
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                "Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
//        fchooser.setFileFilter(filter);
//
//        int retvalue = fchooser.showSaveDialog(this);
//        if (retvalue == JFileChooser.APPROVE_OPTION) {
//          File f = fchooser.getSelectedFile();
//          fileSaveDisplay.setText("Saving to: " + f.getAbsolutePath());
//
//          // Interact with controller to save image
//          if (features != null && currentImageName != null) {
//            try {
//              features.save(f.getAbsolutePath(), currentImageName);
//            } catch (IOException ex) {
//              throw new RuntimeException(ex);
//            }
//          } else {
//            printStatements("Error: No image to save or features not set");
//          }
//        }
//        break;
//      }
//    }
//  }
//
//  @Override
//  public void itemStateChanged(ItemEvent e) {
//    // Handle item state changes if needed
//  }
//
//  @Override
//  public void printStatements(String statement) {
//    System.out.print(statement);
//  }
//
//  public void printStatements(String message) {
//    JOptionPane.showMessageDialog(this, message);
//  }
//
//  @Override
//    public void LoadFile(Image image) {
//    System.out.println("hello");
//    refreshImagePlaceholder(image);
//  }
//
//  @Override
//  public void SaveFile(String filename) {
//    fileSaveDisplay.setText("Image saved to: " + filename);
//  }
//}
//


//package view;
//
//import view.components.GenericButton;
//import view.components.GenericLabel;
//import view.components.ImageDisplay;
//import view.sections.FileIOSection;
//
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import controller.GUIFeatures;
//import model.Image;
//
//import static model.ImageUtil.getDimensions;
//
//public class GUIBasedView extends JFrame implements ImageView, ActionListener, ItemListener {
//
//  private GenericLabel fileOpenDisplay;
//  private GenericLabel fileSaveDisplay;
//  private ImageDisplay imagePlaceholder;
//  private JScrollPane mainScrollPane;
//  private GUIFeatures features;
//  private String currentImageName;
//
//  private FileIOSection fileIOSection;
//
//  public GUIBasedView() {
//    super("PerfectPixel");
//    setSize(1200, 800);
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    // Initialize components and layout
//    initializeComponents();
//    setupLayout();
//
//    setVisible(true);
//  }
//
//  private void initializeComponents() {
//    // Initialize sections and other components
//    fileIOSection = new FileIOSection(this);  // Passing this view as ActionListener
//    fileOpenDisplay = new GenericLabel("File path will appear here");
//    fileSaveDisplay = new GenericLabel("File path will appear here");
//    imagePlaceholder = new ImageDisplay();
//  }
//
//  private void setupLayout() {
//    setLayout(new BorderLayout());
//
//    // Left panel for file I/O section
//    JPanel leftPanel = new JPanel();
//    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//    leftPanel.add(fileIOSection);
//    leftPanel.add(fileOpenDisplay);
//    leftPanel.add(fileSaveDisplay);
//
//    // Right panel for displaying the image
//    JPanel rightPanel = new JPanel(new BorderLayout());
//    rightPanel.add(imagePlaceholder, BorderLayout.CENTER);
//
//    // Scrollable panel for main content
//    mainScrollPane = new JScrollPane(rightPanel);
//    add(leftPanel, BorderLayout.WEST);
//    add(mainScrollPane, BorderLayout.CENTER);
//  }
//
//  public void setFeatures(GUIFeatures features) {
//    this.features = features;
//  }
//
////  @Override
//  public void refreshImagePlaceholder(Image image) {
//    int height = getDimensions(image)[0];
//    int width = getDimensions(image)[1];
//
//    // Combine the RGB channels into a single array
//    int[][] redChannel = image.getRedChannel();
//    int[][] greenChannel = image.getGreenChannel();
//    int[][] blueChannel = image.getBlueChannel();
//
//    // Create a 1D array for efficiency
//    int[] rgbData = new int[width * height];
//    for (int y = 0; y < height; y++) {
//      for (int x = 0; x < width; x++) {
//        int red = redChannel[y][x];
//        int green = greenChannel[y][x];
//        int blue = blueChannel[y][x];
//        rgbData[y * width + x] = (red << 16) | (green << 8) | blue;
//      }
//    }
//
//    // Create the BufferedImage and set pixel data in bulk
//    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//    outputImage.setRGB(0, 0, width, height, rgbData, 0, width);
//
//    // Update the placeholder with the new image
//    imagePlaceholder.updateImage(new ImageIcon(outputImage));
//  }
//
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    switch (e.getActionCommand()) {
//      case "LOAD_IMAGE":
//        handleLoadImage();
//        break;
//      case "SAVE_IMAGE":
//        handleSaveImage();
//        break;
//      default:
//        System.out.println("Unknown command: " + e.getActionCommand());
//    }
//  }
//
//  private void handleLoadImage() {
//    JFileChooser fileChooser = new JFileChooser(".");
//    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
//    fileChooser.setFileFilter(filter);
//
//    int returnValue = fileChooser.showOpenDialog(this);
//    if (returnValue == JFileChooser.APPROVE_OPTION) {
//      File file = fileChooser.getSelectedFile();
//      fileOpenDisplay.setText("Loading: " + file.getAbsolutePath());
//      currentImageName = file.getName().replace(".", "_");
//
//      if (features != null) {
//        try {
//          features.load(file.getAbsolutePath(), currentImageName);
//        } catch (IOException ex) {
//          printStatements("Error: " + ex.getMessage());
//        }
//      } else {
//        printStatements("Error: Features not set");
//      }
//    }
//  }
//
//  private void handleSaveImage() {
//    JFileChooser fileChooser = new JFileChooser(".");
//    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
//    fileChooser.setFileFilter(filter);
//
//    int returnValue = fileChooser.showSaveDialog(this);
//    if (returnValue == JFileChooser.APPROVE_OPTION) {
//      File file = fileChooser.getSelectedFile();
//      fileSaveDisplay.setText("Saving to: " + file.getAbsolutePath());
//
//      if (features != null && currentImageName != null) {
//        try {
//          features.save(file.getAbsolutePath(), currentImageName);
//        } catch (IOException ex) {
//          printStatements("Error: " + ex.getMessage());
//        }
//      } else {
//        printStatements("Error: No image to save or features not set");
//      }
//    }
//  }
//
//  @Override
//  public void itemStateChanged(ItemEvent e) {
//    // Handle item state changes if needed
//  }
//
//  @Override
//  public void printStatements(String message) {
//    JOptionPane.showMessageDialog(this, message);
//  }
//
////  @Override
//  public void updateOperationLog(String message) {
//    System.out.println(message);  // Replace with log updating code if needed
//  }
//
//  @Override
//  public void LoadFile(Image image) {
//    refreshImagePlaceholder(image);
//  }
//
//  @Override
//  public void SaveFile(String filename) {
//    fileSaveDisplay.setText("Image saved to: " + filename);
//  }
//}

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GUIFeatures;
import model.Image;
import view.components.GenericLabel;
import view.components.ImageDisplay;
import view.sections.ColorChannelsSection;
import view.sections.ColorCorrectionSection;
import view.sections.CompressionSection;
import view.sections.FileIOSection;
import view.sections.FiltersSection;
import view.sections.FlippingSection;
import view.sections.HistogramSection;
import view.sections.ImageDisplaySection;
import view.sections.LevelsAdjustmentSection;
import view.sections.OperationLogSection;
import view.sections.TransformationsSection;

import static model.ImageUtil.getDimensions;

public class GUIBasedView extends JFrame implements ImageView, ActionListener, ItemListener {

  private GenericLabel fileOpenDisplay;
  private GenericLabel fileSaveDisplay;
  private ImageDisplay imagePlaceholder;
  private JScrollPane mainScrollPane;
  private GUIFeatures features;
  private String currentImageName;

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
  private ImageDisplaySection imageDisplaySection;  // New section

  public GUIBasedView() {
    super("PerfectPixel");
    setSize(1200, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Initialize components and layout
    initializeComponents();
    setupLayout();

    setVisible(true);
  }

  private void initializeComponents() {
    // Initialize sections
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
    imageDisplaySection = new ImageDisplaySection();  // Initialize ImageDisplaySection

    // Other components
    fileOpenDisplay = new GenericLabel("File path will appear here");
    fileSaveDisplay = new GenericLabel("File path will appear here");
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    // Left panel (commands and operations)
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setPreferredSize(new Dimension(350, 800));  // 40% width
    leftPanel.add(fileIOSection);
    leftPanel.add(filtersSection);
    leftPanel.add(flippingSection);
    leftPanel.add(transformationsSection);
    leftPanel.add(colorChannelsSection);
    leftPanel.add(compressionSection);
    leftPanel.add(levelsAdjustmentSection);
    leftPanel.add(colorCorrectionSection);

    // Right panel (image display and additional info)
    JPanel rightPanel = new JPanel(new BorderLayout());
    rightPanel.setPreferredSize(new Dimension(720, 800));  // 60% width

    // Top section (operation log and histogram)
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(operationLogSection, BorderLayout.WEST);
    topPanel.add(histogramSection, BorderLayout.EAST);
    rightPanel.add(topPanel, BorderLayout.NORTH);

    // Bottom section (image display)
    rightPanel.add(imageDisplaySection, BorderLayout.CENTER);  // Add ImageDisplaySection here
    // Main scroll pane
    mainScrollPane = new JScrollPane(rightPanel);

    // Add panels to the frame
    add(leftPanel, BorderLayout.WEST);
    add(mainScrollPane, BorderLayout.CENTER);
  }

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

  public void refreshImagePlaceholder() {
    Image image = features.getImage(currentImageName);

    imageDisplaySection.updateImageDisplay(new ImageIcon(imageToBufferedImage(image)));
    refreshHistogram();
  }

  public void refreshHistogram() {
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
      default:
        printStatements("Unknown command: " + e.getActionCommand());
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
      fileOpenDisplay.setText("Loading: " + file.getAbsolutePath());
      currentImageName = file.getName().replace(".", "_");

      if (features != null) {
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
      fileSaveDisplay.setText("Saving to: " + file.getAbsolutePath());

      if (features != null && currentImageName != null) {
        try {
          features.save(file.getAbsolutePath(), currentImageName);
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
        int black = levelsAdjustmentSection.getBlack();
        int midTones = levelsAdjustmentSection.getMidTone();
        int white = levelsAdjustmentSection.getWhite();

        String adjustedImageName = currentImageName + "_levelsAdjusted";
        features.levelsAdjust(currentImageName, adjustedImageName, black, midTones, white);

        currentImageName = adjustedImageName;
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
  public void itemStateChanged(ItemEvent e) {
    // Handle item state changes if needed
  }

  @Override
  public void printStatements(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  //  @Override
  public void updateOperationLog(String message) {
    System.out.println(message);  // Replace with log updating code if needed
  }
}