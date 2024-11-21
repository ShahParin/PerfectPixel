package view.sections;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import view.components.GenericPanel;
import view.components.ImageDisplay;

/**
 * A panel section that displays the histogram of an image.
 * This section contains an area to visualize the histogram of an image, typically used in
 * image processing tasks.
 */
public class HistogramSection extends GenericPanel {
  private static ImageDisplay histogram;

  /**
   * Constructs the HistogramSection, initializes the histogram display, and sets up the layout.
   */
  public HistogramSection() {
    super(null);
    histogram = new ImageDisplay();
    setupLayout();
  }

  /**
   * Sets up the layout of the HistogramSection.
   * The layout is set to BorderLayout, and the histogram display is added to the center.
   */
  private void setupLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Histogram"));
    setPreferredSize(new Dimension(280, 280));

    add(histogram, BorderLayout.CENTER);
  }

  /**
   * Updates the histogram display with a new image icon.
   *
   * @param imageIcon the ImageIcon representing the histogram image to be displayed.
   */
  public void updateHistogramDisplay(ImageIcon imageIcon) {
    histogram.updateImage(imageIcon);
  }
}
