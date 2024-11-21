# PerfectPixel

## Project Overview
PerfectPixel is a-based image processing application. It supports a range of image processing operations such as color adjustments, filtering, transformations, and image compression. Users can interact with the application via text-based scripts or command-line inputs to perform various image manipulations.

## Features
- **Load/Save Images**: Supports JPG, JPEG, PNG, and PPM file formats.
- **Channel Visualization**: Isolates the red, green, or blue channels for analysis or display.
- **Brightness Adjustment**: Brightens or darkens an image by adjusting each pixel's color values.
- **Image Flipping**: Provides options to flip images horizontally or vertically.
- **Filtering**:
  - **Blur**: Applies a Gaussian blur to soften image details.
  - **Sharpen**: Enhances image edges for a crisper look.
- **Color Transformations**:
  - **Greyscale**: Converts images to greyscale using the Luma transformation.
  - **Sepia Tone**: Applies a sepia filter to get the characteristic reddish brown tone of 19th century images.
- **Image Compression**:
  - **Haar Wavelet Transform**: Applies a customizable compression by utilizing the Haar Wavelet.
- **Histograms**:
  - **Visualization**: Generates a histogram displaying the color intensity distribution across the red, green, and blue channels.
  - **Color Correction**: Adjusts the color balance by aligning histogram peaks of individual channels.
  - **Levels Adjustment**: Enhances image contrast using black, midtone, and white points on the histogram.
- **Split View Preview**: Provides a side-by-side view of the original and modified image to visualize transformations in real time.
- **Partial Image Manipulation**: Applies transformations like filtering, color transforms, and channel visualization on a small part of the image (defined by a mask.).


## Folder Structure
This project follows an MVC architecture, with organized directories for images, source code (Model, View, Controller, Main), and Unit tests for ease of understanding.
```
  .
  ├── inputImages             # Contains project-related images
  ├── res                     # Contains output images, scripts and UML diagram
  │   ├── outputImages        # Images produced by the application      
  │   ├── PerfectPixel.jar    # The JAR file for execution      
  ├── src                     # Source code for the application      
  │   ├── controller          # Application logic and user input handling      
  │   ├── model               # Business logic    
  │   ├── view                # View Components
  │   └── Main                # Entry point of the application  
  ├── test                    # Contains Unit testing
  │   ├── testcontroller      # Controller Testing    
  │   ├── testmodel           # Model Testing         
  │   └── testview            # View Testing       
  ├── README.md               # Overview of the project, features supported, dependencies, and testing
  └── USEME.md                # Contains detailed instructions on how to use the project for end-users
```

## Classes & Interfaces
Below is the class diagram for the application,
![UML Class Diagram](res/PerfectPixel_UML.png)

- **Model:**
  - **Image:** This is the representation class of any Image that is going to be processed in the entire application. It contains 3 channels, Red, Green and Blue as 2D Arrays. It also contains logic for intensity, luma and value for a particular pixel.
  - **ImageModel:** This interface defines a set of operations that can be performed on the images. It provides operations like loading, saving, and applying transformations to images, changing color components, flipping, brightening, filter, and transformations.
  - **ImageModelImpl:** This class provides functionality to manage and manipulate images. It supports operations like loading, saving, applying color filters, transforming, and combining images. The images can be loaded and saved in different formats, and the class offers various methods to extract and manipulate specific color components (red, green, blue) or grayscale versions of images (based on value, intensity, and luma).
  - **ImageModelV2:** This interface extends the ImageModel and supports additional functions like compression, histogram, color correction, level adjustment and split image display.
  - **ImageModelImplV2:** This class extends the class, ImageModelImpl and supports additional operations like compression, histogram, image corrections, and split image view.
  - **ImageModelV3:** Interface for image model functionality with support for applying partial image manipulations using a mask image. Extends the functionality of ImageModelV2.
  - **ImagemodelImplV3:** Implementation of the ImageModelV3 interface, extending the functionality of ImageModelImplV2. This class provides methods for manipulating images using partial image manipulations with masks and various image operations.
  - **ImageOperations:** This class contains the logic for all the operations that should be performed on the images. Some of the supported operations are filters, transforms, splitting and combining RGB images.
  - **ImageUtil:** This is a utilities class for the ImageModel. It abstracts some of the operations to be performed on the images, like fetching the image dimensions, abstracting logic for filters and transformations.
  - **ImageEffectProcessor:** Interface for applying image effects to an image.
  - **ParameterizedImageEffectProcessor:** Interface for image effect processors that require additional parameters, black point, mid point, and white point to apply effects on an image.
  - **NothingThereException:** A custom exception class when the image has no pixels defined.
  - **ComponentType:** Enum representing different components that can be extracted or visualized for the image.


- **View:**
  - **ImageView:** This is an Interface for the View. It displays all the output statements to the end user.
  - **ConsoleBasedView:** This class represents the View, it will display the logs for each operation asked by the user.
  - **GUIBasedView:** GUIBasedView class provides the graphical user interface (GUI) for the PerfectPixel image editing application. Implements the ImageView interface to update the UI based on the image operations.
  - **GenericButton:** A custom button class that extends JButton to provide a generic button with an action command and an action listener. This allows for easy handling of button events.
  - **GenericDropdown:** A custom dropdown class that extends JComboBox to provide a generic dropdown menu. It allows for easy selection of items from a list and handling of selection events.
  - **GenericInputField:** A custom JTextField with placeholder text functionality. This class displays placeholder text in light gray when the field is empty and focuses on user interaction to clear the placeholder and enable text input.
  - **GenericLabel:** A custom JLabel class for displaying text in the user interface. This class extends JLabel and provides a simple wrapper to label text elements.
  - **GenericPanel:** A custom JPanel class that allows for easy creation of panels with specific layouts. This class extends JPanel and passes the provided LayoutManager to the constructor of JPanel.
  - **ImageDisplay:** A custom JLabel class that is designed to display images. It provides a method to update the displayed image by setting a new ImageIcon.
  - **ColorChannelsSection:** A panel section that contains buttons for interacting with color channel operations (Red, Green, Blue).
  - **ColorCorrectionSection:** A panel section for color correction functionality in the GUI. This section contains a button to trigger the color correction operation.
  - **CompressionSection:** A panel section for handling image compression functionality in the GUI. This section allows the user to specify the compression percentage and apply the compression operation.
  - **FileIOSection:** A panel section for handling file input/output operations in the GUI. This section allows the user to load and save images through buttons.
  - **FiltersSection:** A panel section for applying image filters in the GUI. This section provides buttons to apply the "Blur" and "Sharpen" filters to an image.
  - **FlippingSection:** A panel section for performing image flip operations in the GUI. This section provides buttons to flip an image horizontally or vertically.
  - **HistogramSection:** A panel section that displays the histogram of an image. This section contains an area to visualize the histogram of an image, typically used in image processing tasks.
  - **ImageDialogSection:** A modal dialog that displays an image in a scrollable viewer, with options to save or close the image. This dialog is intended for viewing images with the ability to apply operations and close the dialog.
  - **ImageDisplaySection:** A panel that displays an image in a scrollable area, with the ability to update the displayed image and title. This section is used to show images in the user interface with a border that can dynamically change its title.
  - **LevelsAdjustmentSection:** A section that allows users to adjust the black, mid, and white levels of an image. This section includes input fields for each adjustment and a button to apply the changes.
  - **OperationLogSection:** A section for displaying an operation log in the user interface. This section contains a scrollable text area where log messages can be appended.
  - **SplitOperationSection:** A section for handling and displaying split operations, including an input field, a dropdown for selecting operations, and an "Apply" button. The section also supports levels adjustment (black, mid, white) for certain operations.
  - **TransformationsSection:** A section that provides buttons for image transformations, such as Grayscale and Sepia. Each button is linked to a specific transformation action.


- **Controller:**
  - **ImageController:** This is an Interface for the Controller. It executes the commands provided for image loading, manipulation or storing.
  - **TextBasedController:** This class represents the Controller, acting as the mediator between the inputs from the View and the different operations to be preformed from the Model.
  - **TextBasedControllerV3:** A controller version compatible to ImageModelV3.
  - **ViewController:** This class is responsible for coordinating the actions of the image manipulation operations in the graphical user interface (GUI).
  - **ImageFileUtils:** Utility class for handling image file operations, such as reading and saving images in various formats (PPM, PNG, JPEG, JPG).
  - **ImageService:** Service class responsible for handling image loading and saving operations.
  - **Command:** Represents a command that can be executed to perform numerous image processing operations.
  - **CommandFactory:** A factory interface for creating command instances based on provided arguments.
  - **BlueComponentCommand:** Extracts the blue component from an image and saves the result as a new image.
  - **BlurCommand:** Applies a blur effect to the specified image, creating a softened or smoothed version.
  - **BrightenCommand:** Adjusts the brightness of an image, either increasing or decreasing it based on user input.
  - **ColorCorrectCommand:** Applies color correction to the image to adjust color balance, tones, or saturation.
  - **CompressCommand:** Compresses the image, possibly by reducing its dimensions or color depth to reduce file size.
  - **GreenComponentCommand:** Extracts the green component from an image and saves the result as a new image.
  - **HistogramVisualizationCommand:** Generates a histogram visualization of the image, showing color distributions.
  - **HorizontalFlipCommand:** Flips the image horizontally, creating a mirror image.
  - **IntensityComponentCommand:** Extracts the intensity component, which is typically the average of the RGB values, representing brightness.
  - **LevelsAdjustCommand:** Adjusts the levels of an image, balancing highlights, midtones, and shadows for better contrast.
  - **LoadCommand:** Loads an image into the model for processing.
  - **LumaComponentCommand:** Extracts the luma component, which represents the perceived brightness in an image based on weighted RGB values.
  - **RedComponentCommand:** Extracts the red component from an image and saves the result as a new image.
  - **RGBCombineCommand:** Combines separate red, green, and blue component images into a single RGB image.
  - **RGBSplitCommand:** Splits an RGB image into separate red, green, and blue component images.
  - **SaveCommand:** Saves the processed image to a specified file location.
  - **SepiaCommand:** Applies a sepia tone effect to the image, giving it a warm, antique look.
  - **SharpenCommand:** Sharpens the image to enhance edges and fine details.
  - **ValueComponentCommand:** Extracts the value component, which is the maximum of the RGB values, representing the lightest color in each pixel.
  - **VerticalFlipCommand:** Flips the image vertically, creating an upside-down image.
  - **BlueComponentMaskedCommand:** Command class to extract the blue component with a mask.
  - **BlurMaskedCommand:** Command class to blur the specified image.
  - **GreenComponentMaskedCommand:** Command class to extract the green component with a mask.
  - **IntensityMaskedCommand:** Command class to extract the intensity component with a mask.
  - **LumaMaskedCommand:** Command class to extract the luma component with a mask.
  - **RedComponentMaskedCommand:** Command class to extract the red component with a mask.
  - **SepiaMaskedCommand:** Command class to apply sepia tone with a mask.
  - **SharpenMaskedCommand:** Command class to sharpen the specified image with a mask.
  - **ValueMaskedCommand:** Command class to extract the value component with a mask.

## New Version Updates
- Bug Fixes:
  - Optimized various operations to reduce processing time for larger images.
- Feature Addition:
  - Provided a GUI for the application, while retaining all the previous methods to run the program.
  - Added support for partial image manipulation for the operations - sepia, greyscale, blur, sharpen, individual channel visualizations.

## Design Changes for Partial Image Manipulation
- All the existing code was left untouched.
- Created a new model interface, ImageModelV3 and its implementation, ImageModelImplV3 to support this feature.
- Created individual command classes for each of the new supported operations, following COmmand Design Pattern.
- Extended the existing controller to create a new controller, TextBaseControllerV3. In this way the existing code was left untouched and just reused for the new version.

## Dependencies
The project relies solely on JDK classes and does not require external libraries.

## Testing
Each feature includes unit tests, covering the model and controller logic. Mock objects were used to simulate the controller responses.

## Known Limitations
**File Size**: Large images may slow down processing, but will yield accurate results.

## Previous Version Updates
- Bug Fixes:
  - Updated the getters of the Image class to prevent model leaks.
  - I/O operations - loading and saving of files were moved to the controller.
  - Updated all the methods to throw an IllegalArgumentException when the name of image to fetch is incorrect.
  - De-duplicated the function calls for extracting image components by utilizing an Enum.
- Feature Addition:
  - Added support for operations like,
    - Image compression
    - Histogram visualization
    - Color correction of images
    - Level adjustment to improve image contrast
    - Split view previews to visualize changes
  - Added interactive user input mode
  - Optimized the controller design by utilizing Command Design Pattern
  - User can directly run the program using the JAR file instead building and running the main file


## Citation
The images used in this project are either "clicked and owned" or "created and owned" by
[@shivang2402](https://github.com/shivang2402) or [@ShahParin](https://github.com/ShahParin). Please credit accordingly if used elsewhere.
