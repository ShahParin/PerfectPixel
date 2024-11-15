package view.components;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class GenericButton extends JButton {
  public GenericButton(String text, String actionCommand, ActionListener listener) {
    super(text);
    setActionCommand(actionCommand);
    addActionListener(listener);
  }
}
