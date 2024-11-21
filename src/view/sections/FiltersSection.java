package view.sections;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericPanel;

/**
 * A panel section for applying image filters in the GUI.
 * This section provides buttons to apply the "Blur" and "Sharpen" filters to an image.
 */
public class FiltersSection extends GenericPanel {

  /**
   * Constructs a FiltersSection with buttons for applying "Blur" and "Sharpen" filters.
   * These buttons trigger the respective filter operations when clicked.
   *
   * @param listener the ActionListener that will handle the button click events for applying
   *                 filters.
   */
  public FiltersSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Filters"));

    GenericButton blurButton = new GenericButton("Blur", "BLUR", listener);
    GenericButton sharpenButton = new GenericButton("Sharpen", "SHARPEN", listener);

    add(blurButton);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(sharpenButton);
  }
}
