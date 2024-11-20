package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * A custom JTextField with placeholder text functionality.
 * This class displays placeholder text in light gray when the field is empty and focuses
 * on user interaction to clear the placeholder and enable text input.
 */
public class GenericInputField extends JTextField {

  /**
   * Constructs a GenericInputField with the specified placeholder text.
   *
   * @param placeholder the placeholder text to be displayed when the input field is empty.
   */
  public GenericInputField(String placeholder) {
    super(placeholder);

    setForeground(Color.LIGHT_GRAY);

    addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        if (getText().equals(placeholder)) {
          setText("");
          setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
          setText(placeholder);
          setForeground(Color.LIGHT_GRAY);
        }
      }
    });
  }

  /**
   * Returns the preferred size for the input field.
   *
   * @return the preferred size as a Dimension object.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(50, 25); // Width: 200px, Height: 30px
  }

  /**
   * Returns the maximum size for the input field.
   *
   * @return the maximum size as a Dimension object, which is the same as the preferred size.
   */
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
