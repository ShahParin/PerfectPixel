package view.components;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;

public class GenericDropdown extends JComboBox<String> {
  public GenericDropdown(String[] items, ActionListener listener) {
    super(items); // Passes items to the JComboBox constructor
    addActionListener(listener); // Adds the action listener
  }

  // Optional: Get selected item as a String
  @Override
  public String getSelectedItem() {
    return (String) super.getSelectedItem();
  }
}
