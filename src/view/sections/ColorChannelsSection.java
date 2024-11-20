package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class ColorChannelsSection extends GenericPanel {
  private GenericButton redButton;
  private GenericButton greenButton;
  private GenericButton blueButton;

  public ColorChannelsSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Color Channels"));


    // Add buttons for color channels
    redButton = new GenericButton("Red", "RED_COMPONENT", listener);
    greenButton = new GenericButton("Green", "GREEN_COMPONENT", listener);
    blueButton = new GenericButton("Blue", "BLUE_COMPONENT", listener);

//    add(new JLabel("Color Channels"));
//    add(Box.createRigidArea(new Dimension(25, 0)));

    add(redButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(greenButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(blueButton);
  }
}
