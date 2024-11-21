package view.sections;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericDropdown;
import view.components.GenericInputField;
import view.components.GenericLabel;
import view.components.GenericPanel;

/**
 * A section for handling and displaying split operations, including an input field,
 * a dropdown for selecting operations, and an "Apply" button.
 * The section also supports levels adjustment (black, mid, white) for certain operations.
 */
public class SplitOperationSection extends GenericPanel {
  private final GenericInputField inputField;
  private final GenericDropdown dropdown;
  private final GenericButton applyButton;
  private GenericInputField blackField;
  private GenericInputField midField;
  private GenericInputField whiteField;
  private final GenericPanel levelsPanel;

  /**
   * Constructs the SplitOperationSection.
   * Initializes the components such as input fields, dropdown, and apply button.
   * Also sets up listeners and layout.
   *
   * @param listener The ActionListener for handling apply button actions.
   */
  public SplitOperationSection(ActionListener listener) {
    super(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Split Operation"));
    setPreferredSize(new Dimension(200, 280));

    // Create components
    GenericLabel inputLabel = new GenericLabel("Input:");
    GenericLabel dropdownLabel = new GenericLabel("Options:");

    inputField = new GenericInputField("Enter a double value...");
    setInputFieldToDoubleOnly(inputField);

    dropdown = new GenericDropdown(
            new String[]{"Select a split operation...", "blur", "sharpen", "sepia", "greyscale",
                    "color correction", "levels adjustment"},
            this::onDropdownSelection
    );

    applyButton = new GenericButton("Apply", "APPLY_SPLIT", listener);
    applyButton.setEnabled(false);

    GenericPanel inputPanel = new GenericPanel(new GridLayout(2, 2, 10, 10));
    inputPanel.add(inputLabel);
    inputPanel.add(inputField);
    inputPanel.add(dropdownLabel);
    inputPanel.add(dropdown);

    levelsPanel = new GenericPanel(new GridLayout(3, 2, 10, 10));
    levelsPanel.setVisible(false); // Hidden initially

    GenericPanel buttonPanel = new GenericPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(applyButton);

    // Main panel to stack components vertically
    GenericPanel mainPanel = new GenericPanel(new BoxLayout(this, BoxLayout.Y_AXIS));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(inputPanel);
    mainPanel.add(levelsPanel);
    mainPanel.add(buttonPanel);

    add(mainPanel, BorderLayout.CENTER);

    dropdown.setSelectedIndex(0);
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
    String selectedOption = dropdown.getSelectedItem();
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
    if (!levelsPanel.isVisible()) {
      levelsPanel.removeAll();
      blackField = new GenericInputField("black");
      setInputFieldToDoubleOnly(blackField);
      blackField.setPreferredSize(new Dimension(50, 30));
      midField = new GenericInputField("mid");
      setInputFieldToDoubleOnly(midField);
      midField.setPreferredSize(new Dimension(50, 30));
      whiteField = new GenericInputField("white");
      setInputFieldToDoubleOnly(whiteField);
      whiteField.setPreferredSize(new Dimension(50, 30));

      levelsPanel.add(new GenericLabel("Black:"));
      levelsPanel.add(blackField);
      levelsPanel.add(new GenericLabel("Mid:"));
      levelsPanel.add(midField);
      levelsPanel.add(new GenericLabel("White:"));
      levelsPanel.add(whiteField);

      levelsPanel.setVisible(true);
      revalidate();
      repaint();
    }
  }

  /**
   * Hide the input fields for levels adjustment.
   */
  private void hideLevelsAdjustmentFields() {
    levelsPanel.setVisible(false);
    levelsPanel.removeAll();
    blackField = null;
    midField = null;
    whiteField = null;
    revalidate();
    repaint();
  }

  /**
   * Gets the selected operation from the dropdown.
   *
   * @return The selected operation name, or null if no valid operation is selected.
   */
  public String getSelectedOperation() {
    String selected = dropdown.getSelectedItem();
    return "Select a split operation...".equals(selected) ? null : selected;
  }

  /**
   * Gets the input value from the input field.
   *
   * @return The input value as a double.
   * @throws IllegalArgumentException If the input is empty or not a valid double value.
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
   * Gets the values for the levels adjustment (black, mid, white).
   *
   * @return An array of integer values [black, mid, white].
   * @throws IllegalArgumentException If any of the fields are empty or not valid integers.
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
              + "integer values.");
    }
  }

}
