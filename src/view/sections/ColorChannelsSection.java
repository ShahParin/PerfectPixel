package view.sections;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericPanel;

/**
 * A panel section that contains buttons for interacting with color channel operations
 * (Red, Green, Blue).
 */
public class ColorChannelsSection extends GenericPanel {

  /**
   * Constructs a ColorChannelsSection with buttons for each color channel (Red, Green, Blue).
   * The buttons are linked to the provided action listener which handles the respective actions.
   *
   * @param listener the ActionListener that will handle the button click events.
   */
  public ColorChannelsSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Color Channels"));

    GenericButton redButton = new GenericButton("Red", "RED_COMPONENT", listener);
    GenericButton greenButton = new GenericButton("Green", "GREEN_COMPONENT", listener);
    GenericButton blueButton = new GenericButton("Blue", "BLUE_COMPONENT", listener);

    add(redButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(greenButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(blueButton);
  }
}
