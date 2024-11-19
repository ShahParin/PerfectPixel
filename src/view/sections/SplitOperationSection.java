package view.sections;

import view.components.*;
import view.components.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SplitOperationSection extends GenericPanel {

  private final GenericInputField inputField;
  private final GenericDropdown dropdown;
  private final GenericButton applyButton;
  private GenericInputField blackField;
  private GenericInputField midField;
  private GenericInputField whiteField;

  public SplitOperationSection() {
    super(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Split Operation"));
    setPreferredSize(new Dimension(200, 280)); // Increased height to accommodate the extra fields

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

    // Apply button initially disabled
    applyButton = new GenericButton("Apply", "apply", this::onApplyButtonClicked);
    applyButton.setEnabled(false); // Disabled until valid dropdown selection

    // Create an input panel
    GenericPanel inputPanel = new GenericPanel(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns with spacing
    inputPanel.add(inputLabel);
    inputPanel.add(inputField);
    inputPanel.add(dropdownLabel);
    inputPanel.add(dropdown);

    // Create a button panel
    GenericPanel buttonPanel = new GenericPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(applyButton);

    // Add components to the SplitOperationSection
    add(inputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.CENTER);

    // Ensure dropdown default selection is set AFTER all components are initialized
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
        // Allow digits, one decimal point, and backspace
        if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
          e.consume();
        }
        // Prevent multiple decimal points
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

    // Enable the apply button only if a valid option is selected
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
   * Handler for apply button click.
   */
  private void onApplyButtonClicked(ActionEvent e) {
    try {
      String inputText = inputField.getText();
      String selectedOption = (String) dropdown.getSelectedItem();
      JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

      // Validate input field
      if (inputText.equals("Enter a double value...") || inputText.isEmpty()) {
        Dialog.showMessage(parentFrame, "Input field is empty. Please enter a value.");
        return;
      }

      if (!isValidDouble(inputText)) {
        Dialog.showMessage(parentFrame, "Invalid input. Please enter a valid double value.");
        return;
      }

      if (selectedOption.equals("Select a split operation...")) {
        Dialog.showMessage(parentFrame, "Please select a valid option from the dropdown.");
        return;
      }

      if (selectedOption.equals("levels adjustment")) {
        // Validate integer inputs for black, mid, and white
        if (!isValidInteger(blackField.getText()) || !isValidInteger(midField.getText()) || !isValidInteger(whiteField.getText())) {
          Dialog.showMessage(parentFrame, "Please enter valid integer values for black, mid, and white.");
          return;
        }
      }

      // Success message
      Dialog.showMessage(parentFrame, "Apply button clicked with input: " + inputText +
              " and dropdown selection: " + selectedOption);

    } catch (Exception ex) {
      // General exception handling
      Dialog.showMessage(null, "An unexpected error occurred: " + ex.getMessage());
    }
  }

  /**
   * Validate if the input is a valid double.
   */
  private boolean isValidDouble(String input) {
    try {
      Double.parseDouble(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Validate if the input is a valid integer.
   */
  private boolean isValidInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
