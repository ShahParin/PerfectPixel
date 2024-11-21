package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A panel section for handling file input/output operations in the GUI.
 * This section allows the user to load and save images through buttons.
 */
public class FileIOSection extends GenericPanel {

  /**
   * Constructs a FileIOSection with buttons for loading and saving images.
   * The buttons trigger the respective load and save operations when clicked.
   *
   * @param listener the ActionListener that will handle the button click events for load and save
   *                 operations.
   */
  public FileIOSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("File IO"));

    GenericButton loadButton = new GenericButton("Load Image", "LOAD_IMAGE", listener);
    GenericButton saveButton = new GenericButton("Save Image", "SAVE_IMAGE", listener);

    add(loadButton);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(saveButton);
  }
}
