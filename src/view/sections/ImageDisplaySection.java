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

    JScrollPane scrollPane = new JScrollPane(imageDisplay);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Add the image display component to the panel
    add(scrollPane, BorderLayout.CENTER);
  }

  // Method to update the image in the display section
  public void updateImageDisplay(ImageIcon imageIcon) {
    imageDisplay.updateImage(imageIcon);
  }
}
