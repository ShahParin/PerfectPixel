package view.sections;

import view.components.GenericButton;
import view.components.GenericPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

public class FileIOSection extends GenericPanel {

  private GenericButton loadButton;
  private GenericButton saveButton;

  public FileIOSection(ActionListener listener) {
    // Call the super constructor with an empty layout; BoxLayout will be set by the parent class
    super(null);

    // Set the BoxLayout in the child class after the panel is initialized
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    // Create Load and Save buttons
    loadButton = new GenericButton("Load Image", "LOAD_IMAGE", listener);
    saveButton = new GenericButton("Save Image", "SAVE_IMAGE", listener);

    // Add buttons to the panel
    setBorder(new EmptyBorder(10, 10, 10, 10));
    add(loadButton);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(saveButton);
  }
}
