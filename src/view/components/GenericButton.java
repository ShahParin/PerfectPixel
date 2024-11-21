package view.components;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A custom button class that extends JButton to provide a generic button with an
 * action command and an action listener. This allows for easy handling of button events.
 */
public class GenericButton extends JButton {

  /**
   * Constructs a GenericButton with the specified text, action command, and action listener.
   *
   * @param text          the text to be displayed on the button.
   * @param actionCommand the action command string associated with this button.
   *                      It is typically used to identify the button in action events.
   * @param listener      the ActionListener to be added to the button for handling click events.
   */
  public GenericButton(String text, String actionCommand, ActionListener listener) {
    super(text);
    setActionCommand(actionCommand);
    addActionListener(listener);
  }
}
