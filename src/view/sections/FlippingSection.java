package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A panel section for performing image flip operations in the GUI.
 * This section provides buttons to flip an image horizontally or vertically.
 */
public class FlippingSection extends GenericPanel {

  /**
   * Constructs a FlippingSection with buttons for performing "Horizontal Flip" and
   * "Vertical Flip" operations.
   * These buttons trigger the respective flip operations when clicked.
   *
   * @param listener the ActionListener that will handle the button click events for
   *                 flipping operations.
   */
  public FlippingSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Flip Operations"));

    GenericButton horizontalFlipButton = new GenericButton("Horizontal Flip",
            "HORIZONTAL_FLIP", listener);
    GenericButton verticalFlipButton = new GenericButton("Vertical Flip",
            "VERTICAL_FLIP", listener);

    add(horizontalFlipButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(verticalFlipButton);
  }
}
