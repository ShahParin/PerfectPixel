package view.sections;

import java.awt.*;
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

    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Levels Adjustment"));

    blackInputField = new GenericInputField("Black");
    midInputField = new GenericInputField("Mid");
    whiteInputField = new GenericInputField("White");
    applyButton = new GenericButton("Apply", "LEVELS_ADJUST", listener);


    add(blackInputField);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(midInputField);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(whiteInputField);
    add(Box.createRigidArea(new Dimension(25, 0)));

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
