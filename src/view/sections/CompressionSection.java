package view.sections;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.components.GenericButton;
import view.components.GenericInputField;
import view.components.GenericPanel;

public class CompressionSection extends GenericPanel {
  private final GenericInputField inputField;
  private final GenericButton applyButton;

  public CompressionSection(ActionListener listener) {
    super(null);

    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    setBorder(BorderFactory.createTitledBorder("Compression"));

    inputField = new GenericInputField("Percent");
    applyButton = new GenericButton("Apply", "COMPRESS", listener);

    add(inputField);
    add(Box.createRigidArea(new Dimension(25, 0)));
    add(applyButton);
  }

  public double getPercent() {
    return Double.parseDouble(inputField.getText());
  }
}
