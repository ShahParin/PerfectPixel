package view.sections;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import view.components.GenericPanel;
import view.components.ImageDisplay;

/**
 * A panel that displays an image in a scrollable area, with the ability to update
 * the displayed image and title.
 * This section is used to show images in the user interface with a border that
 * can dynamically change its title.
 */
public class ImageDisplaySection extends GenericPanel {
  private static ImageDisplay imageDisplay;

  /**
   * Constructs the ImageDisplaySection.
   * Initializes the image display and sets up the layout for displaying the
   * image in a scrollable area.
   */
  public ImageDisplaySection() {
    super(null);
    imageDisplay = new ImageDisplay();
    setupLayout();
  }

  private void setupLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder("Image Display"));

    JScrollPane scrollPane = new JScrollPane(imageDisplay);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Updates the image displayed in the panel and changes the title of the section's border.
   *
   * @param imageIcon The new image to display.
   * @param newTitle  The new title to display in the section's border.
   */
  public void updateImageDisplay(ImageIcon imageIcon, String newTitle) {
    imageDisplay.updateImage(imageIcon);
    setBorderTitle(newTitle);
  }

  private void setBorderTitle(String title) {
    TitledBorder border = (TitledBorder) getBorder();
    if (border != null) {
      border.setTitle("Image Display" + title);
      repaint(); // Ensure the UI updates to reflect the new border title
    } else {
      // If no border exists, create a new one
      setBorder(BorderFactory.createTitledBorder(title));
    }
  }
}
