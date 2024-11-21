package view.components;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;


/**
 * A custom dropdown class that extends JComboBox to provide a generic dropdown menu.
 * It allows for easy selection of items from a list and handling of selection events.
 */
public class GenericDropdown extends JComboBox<String> {

  /**
   * Constructs a GenericDropdown with the specified items and an action listener.
   *
   * @param items    the list of items to be displayed in the dropdown menu.
   * @param listener the ActionListener to be added to handle selection events.
   */
  public GenericDropdown(String[] items, ActionListener listener) {
    super(items);
    addActionListener(listener);
  }

  /**
   * Returns the selected item from the dropdown menu.
   *
   * @return the selected item as a String.
   */
  @Override
  public String getSelectedItem() {
    return (String) super.getSelectedItem();
  }
}
