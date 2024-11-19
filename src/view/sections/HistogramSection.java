package view.sections;

import view.components.GenericPanel;
import view.components.ImageDisplay;

import javax.swing.*;
import java.awt.*;

public class HistogramSection extends GenericPanel {
  private static ImageDisplay histogram;

  public HistogramSection() {
    super(new BorderLayout());
    histogram = new ImageDisplay();

    setBorder(BorderFactory.createTitledBorder("Histogram"));

    setPreferredSize(new Dimension(256, 256));
  }

  public void updateHistogramDisplay(ImageIcon imageIcon) {
    histogram.updateImage(imageIcon);
  }
}
