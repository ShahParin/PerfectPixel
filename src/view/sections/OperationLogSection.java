package view.sections;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import view.components.GenericPanel;

/**
 * A section for displaying an operation log in the user interface.
 * This section contains a scrollable text area where log messages can be appended.
 */
public class OperationLogSection extends GenericPanel {

  private static JTextArea logArea;

  /**
   * Constructs the OperationLogSection.
   * Initializes the log area and sets up the layout with a scrollable area for
   * displaying log messages.
   */
  public OperationLogSection() {
    super(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Operation Log"));

    logArea = new JTextArea();
    JScrollPane logScrollPane = new JScrollPane(logArea);
    add(logScrollPane, BorderLayout.CENTER);
    setPreferredSize(new Dimension(350, 280));
  }

  /**
   * Appends a log message to the log area.
   * The log message replaces the previous content in the log area.
   *
   * @param message The log message to be displayed.
   */
  public void appendLog(String message) {
    logArea.setText(message);
    logArea.setFont(new Font("Roboto", Font.ITALIC, 16));

  }
}
