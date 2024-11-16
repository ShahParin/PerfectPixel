package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class TransformationsSection extends GenericPanel {
  private GenericButton grayscaleButton;
  private GenericButton sepiaButton;

  public TransformationsSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Transformations"));


    // Add buttons for transformations
    grayscaleButton = new GenericButton("Grayscale", "GRAYSCALE", listener);
    sepiaButton = new GenericButton("Sepia", "SEPIA", listener);

//    add(new JLabel("Transformations"));
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(grayscaleButton);
    add(sepiaButton);
  }
}
