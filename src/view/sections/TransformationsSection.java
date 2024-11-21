package view.sections;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericPanel;


/**
 * A section that provides buttons for image transformations, such as Grayscale and Sepia.
 * Each button is linked to a specific transformation action.
 */
public class TransformationsSection extends GenericPanel {

  /**
   * Constructs the TransformationsSection.
   * Initializes the buttons for grayscale and sepia transformations and sets up the layout.
   *
   * @param listener The ActionListener for handling button actions.
   */
  public TransformationsSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Transformations"));


    GenericButton grayscaleButton = new GenericButton("Grayscale",
            "GRAYSCALE", listener);
    GenericButton sepiaButton = new GenericButton("Sepia",
            "SEPIA", listener);


    add(grayscaleButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(sepiaButton);
  }
}
