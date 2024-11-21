package view.sections;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import view.components.GenericButton;
import view.components.GenericInputField;
import view.components.GenericPanel;

/**
 * A section that allows users to adjust the black, mid, and white levels of an image.
 * This section includes input fields for each adjustment and a button to apply the changes.
 */
public class LevelsAdjustmentSection extends GenericPanel {
  private final GenericInputField blackInputField;
  private final GenericInputField midInputField;
  private final GenericInputField whiteInputField;


  /**
   * Constructs the LevelsAdjustmentSection.
   * Initializes the input fields for black, mid, and white levels, and sets up the layout.
   *
   * @param listener The ActionListener to handle button events.
   */
  public LevelsAdjustmentSection(ActionListener listener) {
    super(null);

    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setBorder(BorderFactory.createTitledBorder("Levels Adjustment"));

    blackInputField = new GenericInputField("Black");
    midInputField = new GenericInputField("Mid");
    whiteInputField = new GenericInputField("White");
    GenericButton applyButton = new GenericButton("Apply", "LEVELS_ADJUST", listener);


    add(blackInputField);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(midInputField);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(whiteInputField);
    add(Box.createRigidArea(new Dimension(25, 0)));

    add(applyButton);
  }

  /**
   * Retrieves the value entered for the black level adjustment.
   *
   * @return The black level as an integer.
   */
  public int getBlack() {
    return Integer.parseInt(blackInputField.getText());
  }

  /**
   * Retrieves the value entered for the mid-tone adjustment.
   *
   * @return The mid-tone level as an integer.
   */
  public int getMidTone() {
    return Integer.parseInt(midInputField.getText());
  }

  /**
   * Retrieves the value entered for the white level adjustment.
   *
   * @return The white level as an integer.
   */
  public int getWhite() {
    return Integer.parseInt(whiteInputField.getText());
  }
}
