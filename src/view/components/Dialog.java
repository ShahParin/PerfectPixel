package view.components;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Dialog {
  public static void showMessage(JFrame parent, String message) {
    JOptionPane.showMessageDialog(parent, message);
  }
}
