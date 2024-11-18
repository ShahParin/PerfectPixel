package view.sections;

import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericInputField;
import view.components.GenericPanel;

public class LevelsAdjustmentSection extends GenericPanel {

  private final GenericInputField blackInputField;
  private final GenericInputField midInputField;
  private final GenericInputField whiteInputField;
  private final GenericButton applyButton;


  public LevelsAdjustmentSection(ActionListener listener) {
    super(null);

    // Set layout to BoxLayout for vertical arrangement
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    setBorder(BorderFactory.createTitledBorder("Levels Adjustment"));

    // Initialize the input fields with placeholder text
    blackInputField = new GenericInputField("Black Level");
    midInputField = new GenericInputField("Mid Level");
    whiteInputField = new GenericInputField("White Level");
    applyButton = new GenericButton("Apply", "LEVELS_ADJUST", listener);

    // Adding fields to the section
    add(blackInputField);
    add(midInputField);
    add(whiteInputField);
    add(applyButton);
  }

  public int getBlack() {
    return Integer.parseInt(blackInputField.getText());
  }

  public int getMidTone() {
    return Integer.parseInt(midInputField.getText());
  }

  public int getWhite() {
    return Integer.parseInt(whiteInputField.getText());
  }
}
