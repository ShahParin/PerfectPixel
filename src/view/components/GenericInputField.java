package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GenericInputField extends JTextField {

  private String placeholder;

  public GenericInputField(String placeholder) {
    super(placeholder);
    this.placeholder = placeholder;

    // Set the text color to light gray to indicate placeholder
    setForeground(Color.LIGHT_GRAY);

    // Add focus listeners for placeholder behavior
    addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        // Remove placeholder text when the field is focused
        if (getText().equals(placeholder)) {
          setText("");
          setForeground(Color.BLACK);  // Change text color to black when typing
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        // Reset placeholder text if the field is empty
        if (getText().isEmpty()) {
          setText(placeholder);
          setForeground(Color.LIGHT_GRAY);  // Change text color back to light gray
        }
      }
    });
  }

  @Override
  public Dimension getPreferredSize() {
    // Limit the size of the input field
    return new Dimension(50, 25); // Width: 200px, Height: 30px
  }

  @Override
  public Dimension getMaximumSize() {
    // Restrict the maximum size further if required
    return getPreferredSize();
  }
}
