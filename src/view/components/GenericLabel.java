package view.components;

import javax.swing.JLabel;

/**
 * A custom JLabel class for displaying text in the user interface.
 * This class extends JLabel and provides a simple wrapper to label text elements.
 */
public class GenericLabel extends JLabel {

  /**
   * Constructs a GenericLabel with the specified text.
   *
   * @param text the text to be displayed in the label.
   */
  public GenericLabel(String text) {
    super(text);
  }
}
