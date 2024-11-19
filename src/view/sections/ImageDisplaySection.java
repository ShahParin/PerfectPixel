package view.sections;

import view.components.ImageDisplay;
import view.components.GenericPanel;

import javax.swing.*;
import java.awt.*;

public class ImageDisplaySection extends GenericPanel {

  private ImageDisplay imageDisplay;

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

  public void updateImageDisplay(ImageIcon imageIcon) {
    imageDisplay.updateImage(imageIcon);
  }
}
