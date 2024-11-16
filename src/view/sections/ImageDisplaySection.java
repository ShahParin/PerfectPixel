package view.sections;

import view.components.ImageDisplay;
import view.components.GenericPanel;

import javax.swing.*;
import java.awt.*;

public class ImageDisplaySection extends GenericPanel {

  private ImageDisplay imageDisplay;

  public ImageDisplaySection() {
    super(null);
    // Initialize the ImageDisplay component
    imageDisplay = new ImageDisplay();
    setupLayout();
  }

  private void setupLayout() {
    // Set the layout of the panel
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Image Display"));

    // Add the image display component to the panel
    add(imageDisplay, BorderLayout.CENTER);
  }

  // Method to update the image in the display section
  public void updateImageDisplay(ImageIcon imageIcon) {
    imageDisplay.updateImage(imageIcon);
  }
}
