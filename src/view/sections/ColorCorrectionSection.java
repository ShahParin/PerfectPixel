package view.sections;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericPanel;

/**
 * A panel section for color correction functionality in the GUI.
 * This section contains a button to trigger the color correction operation.
 */
public class ColorCorrectionSection extends GenericPanel {

  /**
   * Constructs a ColorCorrectionSection with a button to trigger color correction.
   * The button is associated with an action listener to handle the operation when clicked.
   *
   * @param listener the ActionListener that will handle the button click event for color
   *                 correction.
   */
  public ColorCorrectionSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Color Correction"));

    GenericButton colorCorrectButton = new GenericButton("Color Correct",
            "COLOR_CORRECT", listener);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(colorCorrectButton);
  }
}
