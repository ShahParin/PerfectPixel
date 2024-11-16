package view.sections;

import view.components.GenericPanel;
import javax.swing.*;
import java.awt.*;

public class HistogramSection extends GenericPanel {

  public HistogramSection() {
    super(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Histogram"));

    setPreferredSize(new Dimension(256, 256));
//    add(new JLabel("Histogram"), BorderLayout.CENTER);  // Placeholder for actual histogram
  }
}
