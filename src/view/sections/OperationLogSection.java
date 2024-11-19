package view.sections;

import view.components.GenericPanel;
import javax.swing.*;
import java.awt.*;

public class OperationLogSection extends GenericPanel {

  private static JTextArea logArea;

  public OperationLogSection() {
    super(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Operation Log"));


    logArea = new JTextArea();
    logArea.setEditable(false);
    JScrollPane logScrollPane = new JScrollPane(logArea);
    add(logScrollPane, BorderLayout.CENTER);
    setPreferredSize(new Dimension(350, 280));
  }

  public void appendLog(String message) {
    logArea.append(message + "\n");
  }
}
