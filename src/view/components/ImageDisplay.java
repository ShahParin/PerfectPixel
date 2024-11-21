package view.components;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * A custom JLabel class that is designed to display images.
 * It provides a method to update the displayed image by setting a new ImageIcon.
 */
public class ImageDisplay extends JLabel {

  /**
   * Constructs an ImageDisplay with centered alignment.
   * The alignment ensures that the image is displayed in the center of the label.
   */
  public ImageDisplay() {
    setHorizontalAlignment(JLabel.CENTER);
  }

  /**
   * Updates the image displayed in the label with the specified ImageIcon.
   *
   * @param imageIcon the ImageIcon to be displayed on the label.
   */
  public void updateImage(ImageIcon imageIcon) {
    setIcon(imageIcon);
  }
}
