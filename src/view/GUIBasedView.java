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

import view.components.GenericLabel;
import view.components.ImageDisplay;
import view.sections.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import controller.GUIFeatures;
import model.Image;

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
    leftPanel.setPreferredSize(new Dimension(480, 800));  // 40% width
    leftPanel.add(fileIOSection);
    leftPanel.add(filtersSection);
    leftPanel.add(flippingSection);
    leftPanel.add(transformationsSection);
    leftPanel.add(colorChannelsSection);
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

  //  @Override
  public void refreshImagePlaceholder(Image image) {
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

    imageDisplaySection.updateImageDisplay(new ImageIcon(outputImage));
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
      default:
        System.out.println("Unknown command: " + e.getActionCommand());
    }
  }

  private void handleLoadImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
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
    }
  }

  private void handleSaveImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (*.jpeg, *.jpg, *.png, *.ppm)", "jpeg", "jpg", "png", "ppm");
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

  @Override
  public void LoadFile(Image image) {
    refreshImagePlaceholder(image);
  }

  @Override
  public void SaveFile(String filename) {
    fileSaveDisplay.setText("Image saved to: " + filename);
  }
}