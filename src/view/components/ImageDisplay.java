package view.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageDisplay extends JLabel {

  public ImageDisplay() {
    setHorizontalAlignment(JLabel.CENTER);
  }

  public void updateImage(ImageIcon imageIcon) {
    setIcon(imageIcon);
  }
}
