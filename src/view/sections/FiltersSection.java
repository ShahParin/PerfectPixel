package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class FiltersSection extends GenericPanel {
  private GenericButton blurButton;
  private GenericButton sharpenButton;

  public FiltersSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Filters"));


    // Add buttons for filters
    blurButton = new GenericButton("Blur", "BLUR", listener);
    sharpenButton = new GenericButton("Sharpen", "SHARPEN", listener);

//    add(new JLabel("Filters"));
    add(blurButton);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(sharpenButton);
  }
}
