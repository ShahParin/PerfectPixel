package view.sections;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.ImageDisplay;

/**
 * A modal dialog that displays an image in a scrollable viewer, with options to save or
 * close the image.
 * This dialog is intended for viewing images with the ability to apply operations and
 * close the dialog.
 */
public class ImageDialogSection extends JDialog {
  private final GenericButton saveButton;
  private final GenericButton closeButton;
  private final ImageDisplay imageDisplay;
  private final ActionListener listener;

  /**
   * Constructs the ImageDialogSection dialog.
   *
   * @param parent   The parent frame that this dialog is centered relative to.
   * @param listener The ActionListener that handles actions for save and close buttons.
   */
  public ImageDialogSection(JFrame parent, ActionListener listener) {
    super(parent, "Image Viewer", true); // Create a modal dialog
    this.listener = listener;
    this.imageDisplay = new ImageDisplay();
    this.saveButton = new GenericButton("Save", null, null); // No direct action tied to Save
    this.closeButton = new GenericButton("Close", null, null); // No direct action tied to Close

    setupLayout();
    setSize(600, 400); // Default size
    setLocationRelativeTo(parent); // Center relative to parent
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    // Add scrollable image display
    JScrollPane scrollPane = new JScrollPane(imageDisplay);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    add(scrollPane, BorderLayout.CENTER);

    // Add button panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    // Save button: Trigger APPLY_SAVE_SPLIT and close the dialog
    saveButton.addActionListener(e -> saveAndClose());

    // Close button: Confirm and close
    closeButton.addActionListener(e -> confirmAndClose());

    buttonPanel.add(saveButton);
    buttonPanel.add(closeButton);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  private void saveAndClose() {
    // Trigger the "APPLY_SAVE_SPLIT" action
    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "APPLY_SAVE_SPLIT"));
    dispose(); // Close the dialog
  }

  private void confirmAndClose() {
    // Show a confirmation dialog
    int choice = JOptionPane.showConfirmDialog(
            this,
            "ARE YOU SURE YOU DO NOT WANT TO APPLY THE SELECTED OPERATION?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
    );

    // If the user clicks "Yes"
    if (choice == JOptionPane.YES_OPTION) {
      // Trigger the "APPLY_CLOSE_SPLIT" action
      listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "APPLY_CLOSE_SPLIT"));
      dispose(); // Close the dialog
    }
  }

  /**
   * Updates the image display with a new image icon.
   *
   * @param imageIcon The ImageIcon to display in the dialog.
   */
  public void updateImageDisplay(ImageIcon imageIcon) {
    imageDisplay.updateImage(imageIcon);
  }

  public static void showImageDialog(JFrame parent, ImageIcon imageIcon, ActionListener listener) {
    ImageDialogSection dialog = new ImageDialogSection(parent, listener);
    dialog.updateImageDisplay(imageIcon);
    dialog.setVisible(true); // Show the dialog
  }
}
