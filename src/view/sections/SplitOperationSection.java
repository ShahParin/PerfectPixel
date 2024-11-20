package view.sections;

import view.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SplitOperationSection extends GenericPanel {
  private final GenericInputField inputField;
  private final GenericDropdown dropdown;
  private final GenericButton applyButton;
  private GenericInputField blackField;
  private GenericInputField midField;
  private GenericInputField whiteField;

  public SplitOperationSection(ActionListener listener) {
    super(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Split Operation"));
    setPreferredSize(new Dimension(200, 280));

    // Create components
    GenericLabel inputLabel = new GenericLabel("Input:");
    GenericLabel dropdownLabel = new GenericLabel("Options:");

    // Input field that accepts only double values
    inputField = new GenericInputField("Enter a double value...");
    setInputFieldToDoubleOnly(inputField);

    // Dropdown with the desired options
    dropdown = new GenericDropdown(
            new String[]{"Select a split operation...", "blur", "sharpen", "sepia", "greyscale", "color correction", "levels adjustment"},
            this::onDropdownSelection
    );

    // Apply button that triggers the ActionListener passed into the constructor
    applyButton = new GenericButton("Apply", "APPLY_SPLIT", listener);
    applyButton.setEnabled(false); // Disabled until valid dropdown selection

    // Input panel
    GenericPanel inputPanel = new GenericPanel(new GridLayout(2, 2, 10, 10));
    inputPanel.add(inputLabel);
    inputPanel.add(inputField);
    inputPanel.add(dropdownLabel);
    inputPanel.add(dropdown);

    // Button panel
    GenericPanel buttonPanel = new GenericPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(applyButton);

    // Add components to SplitOperationSection
    add(inputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.CENTER);

    // Ensure dropdown default selection is set after all components are initialized
    dropdown.setSelectedIndex(0); // Set default option
  }

  /**
   * Configure the input field to accept only double values.
   */
  private void setInputFieldToDoubleOnly(GenericInputField field) {
    field.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        String text = field.getText();
        if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
          e.consume();
        }
        if (c == '.' && text.contains(".")) {
          e.consume();
        }
      }
    });
  }

  /**
   * Handler for dropdown selection.
   */
  private void onDropdownSelection(ActionEvent e) {
    String selectedOption = (String) dropdown.getSelectedItem();
    applyButton.setEnabled(!selectedOption.equals("Select a split operation..."));
    if (selectedOption.equals("levels adjustment")) {
      showLevelsAdjustmentFields();
    } else {
      hideLevelsAdjustmentFields();
    }
  }

  /**
   * Show the input fields for levels adjustment.
   */
  private void showLevelsAdjustmentFields() {
    if (blackField == null) {
      blackField = new GenericInputField("black");
      setInputFieldToDoubleOnly(blackField);
      blackField.setPreferredSize(new Dimension(50, 30));
      midField = new GenericInputField("mid");
      setInputFieldToDoubleOnly(midField);
      midField.setPreferredSize(new Dimension(50, 30));
      whiteField = new GenericInputField("white");
      setInputFieldToDoubleOnly(whiteField);
      whiteField.setPreferredSize(new Dimension(50, 30));
      GenericPanel levelsPanel = new GenericPanel(new GridLayout(3, 2, 10, 10));
      levelsPanel.add(new GenericLabel("Black:"));
      levelsPanel.add(blackField);
      levelsPanel.add(new GenericLabel("Mid:"));
      levelsPanel.add(midField);
      levelsPanel.add(new GenericLabel("White:"));
      levelsPanel.add(whiteField);
      add(levelsPanel, BorderLayout.SOUTH);
      revalidate();
      repaint();
    }
  }

  /**
   * Hide the input fields for levels adjustment.
   */
  private void hideLevelsAdjustmentFields() {
    if (blackField != null) {
      remove(blackField.getParent());
      blackField = null;
      midField = null;
      whiteField = null;
      revalidate();
      repaint();
    }
  }

  /**
   * Gets the selected operation.
   *
   * @return The operation name or null if invalid.
   */
  public String getSelectedOperation() {
    String selected = (String) dropdown.getSelectedItem();
    return "Select a split operation...".equals(selected) ? null : selected;
  }

  /**
   * Gets the input value.
   *
   * @return The input value as a double.
   * @throws IllegalArgumentException If the input is invalid.
   */
  public double getInputValue() throws IllegalArgumentException {
    String text = inputField.getText();
    if (text.isEmpty() || "Enter a double value...".equals(text)) {
      throw new IllegalArgumentException("Input field is empty.");
    }
    try {
      return Double.parseDouble(text);
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("Invalid input. Enter a valid double value.");
    }
  }

  /**
   * Gets the black, mid, and white values for levels adjustment.
   *
   * @return An array of integer values [black, mid, white].
   * @throws IllegalArgumentException If any of the inputs are invalid.
   */
  public int[] getLevelsAdjustmentValues() throws IllegalArgumentException {
    if (blackField == null || midField == null || whiteField == null) {
      throw new IllegalArgumentException("Levels adjustment fields are not visible.");
    }

    try {
      int black = Integer.parseInt(blackField.getText());
      int mid = Integer.parseInt(midField.getText());
      int white = Integer.parseInt(whiteField.getText());
      return new int[]{black, mid, white};
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("Invalid input in levels adjustment fields. Enter valid "
              +               "integer values.");
    }
  }

}
