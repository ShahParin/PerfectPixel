package view.components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * A custom JPanel class that allows for easy creation of panels with specific layouts.
 * This class extends JPanel and passes the provided LayoutManager to the constructor of JPanel.
 */
public class GenericPanel extends JPanel {

  /**
   * Constructs a GenericPanel with the specified layout manager.
   *
   * @param layout the LayoutManager to be used for the panel's layout.
   */
  public GenericPanel(LayoutManager layout) {
    super(layout);
  }

}
