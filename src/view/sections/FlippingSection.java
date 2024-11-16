package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class FlippingSection extends GenericPanel {
  private GenericButton horizontalFlipButton;
  private GenericButton verticalFlipButton;

  public FlippingSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Flip Operations"));

    // Add buttons for flipping
    horizontalFlipButton = new GenericButton("Horizontal Flip", "HORIZONTAL_FLIP", listener);
    verticalFlipButton = new GenericButton("Vertical Flip", "VERTICAL_FLIP", listener);

//    add(new JLabel("Flipping"));
    add(horizontalFlipButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(verticalFlipButton);
  }
}
