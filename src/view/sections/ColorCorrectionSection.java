package view.sections;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericPanel;

public class ColorCorrectionSection extends GenericPanel {

  public ColorCorrectionSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Color Correction"));

    GenericButton colorCorrectButton = new GenericButton("Color Correct", "COLOR_CORRECT",
            listener);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(colorCorrectButton);
  }
}
