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
 * A panel section for handling image compression functionality in the GUI.
 * This section allows the user to specify the compression percentage and apply
 * the compression operation.
 */
public class CompressionSection extends GenericPanel {
  private final GenericInputField inputField;

  /**
   * Constructs a CompressionSection with a text input field and a button to apply compression.
   * The input field allows the user to specify the compression percentage,
   * and the button triggers the compression operation.
   *
   * @param listener the ActionListener that will handle the button click event for compression.
   */
  public CompressionSection(ActionListener listener) {
    super(null);

    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    setBorder(BorderFactory.createTitledBorder("Compression"));

    inputField = new GenericInputField("Percent");
    GenericButton applyButton = new GenericButton("Apply", "COMPRESS", listener);

    add(inputField);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(applyButton);
  }

  /**
   * Retrieves the compression percentage entered by the user.
   *
   * @return the compression percentage as a double.
   * @throws NumberFormatException if the input is not a valid number.
   */
  public double getPercent() {
    return Double.parseDouble(inputField.getText());
  }
}
