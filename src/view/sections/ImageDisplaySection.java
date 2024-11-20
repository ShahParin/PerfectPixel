package view.sections;

import view.components.ImageDisplay;
import view.components.GenericPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class ImageDisplaySection extends GenericPanel {
  private static ImageDisplay imageDisplay;

  public ImageDisplaySection() {
    super(null);
    imageDisplay = new ImageDisplay();
    setupLayout();
  }

  private void setupLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Image Display"));

    JScrollPane scrollPane = new JScrollPane(imageDisplay);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    add(scrollPane, BorderLayout.CENTER);
  }

  public void updateImageDisplay(ImageIcon imageIcon, String newTitle) {
    imageDisplay.updateImage(imageIcon);
    setBorderTitle(newTitle);
  }

  private void setBorderTitle(String title) {
    TitledBorder border = (TitledBorder) getBorder();
    if (border != null) {
      border.setTitle("Image Display" + title);
      repaint(); // Ensure the UI updates to reflect the new border title
    } else {
      // If no border exists, create a new one
      setBorder(BorderFactory.createTitledBorder(title));
    }
  }
}
