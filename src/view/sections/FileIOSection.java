package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FileIOSection extends GenericPanel {

  private GenericButton loadButton;
  private GenericButton saveButton;

  public FileIOSection(ActionListener listener) {
    super(null);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("File IO"));


    // Create Load and Save buttons
    loadButton = new GenericButton("Load Image", "LOAD_IMAGE", listener);
    saveButton = new GenericButton("Save Image", "SAVE_IMAGE", listener);

    // Add buttons to the panel
    add(loadButton);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(saveButton);
  }
}
