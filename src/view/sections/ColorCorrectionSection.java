package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class ColorCorrectionSection extends GenericPanel {
  private GenericButton colorCorrectButton;

  public ColorCorrectionSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Color Correction"));


    // Add button for color correction
    colorCorrectButton = new GenericButton("Color Correct", "COLOR_CORRECT", listener);

//    add(new JLabel("Color Correction"));
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(colorCorrectButton);
  }
}
