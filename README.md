# PerfectPixel

## Project Overview

This project contains a set of image processing commands applied to an image. The script performs operations such as extracting color components, flipping, applying filters (sepia, greyscale, etc.), and combining channels, all on a single image. The commands can be run through a script file, executing various transformations in sequence.

## Folder Structure

## How to Use

### 1. Place the Image
Put your input image (khoury.ppm) in the input/ folder. The image file khoury.ppm is the original
photo provided by the user.

### 2. Run the Script
To execute the default set of processing commands, navigate to the `src/` folder in the terminal
and run the commands:

```
>javac Main.java
>java Main
```

The script will process the image and save various results in the output/ folder.

### 3. Generated Output
The following files will be generated based on the operations:
- Running all the commands in a script file\
`run RunScript.txt`<br/><br/>

- Load an image and name it\
`load input/khoury.ppm khoury`<br/><br/>

- Save a loaded image named "khoury"\
`save input/khoury.ppm khoury`<br/><br/>

- Create a new image with just the red component\
`red-component khoury khoury-red`<br/><br/>

- Create a new image with just the green component\
`green-component khoury khoury-green`<br/><br/>

- Create a new image with just the blue component\
`blue-component khoury khoury-blue`<br/><br/>

- Create a new image using the value component\
`value-component khoury khoury-value`<br/><br/>

- Create a new image using the intensity component\
`intensity-component khoury khoury-intensity`<br/><br/>

- Create a new image using the luma component\
`luma-component khoury khoury-luma`<br/><br/>

- Split the image into RGB components\
`rgb-split khoury khoury-red khoury-green khoury-blue`<br/><br/>

- Combine 3 different red, green, and blue component images into a single image\
`rgb-combine khoury-new khoury-red khoury khoury`<br/><br/>

- Brighten the image by adding a constant (here, 25)\
`brighten 25 khoury khoury-brighter`<br/><br/>

- Darken the image by adding a negative constant (here, -25)\
`brighten -25 khoury khoury-brighter`<br/><br/>

- Flip the image horizontally\
`horizontal-flip khoury khoury-horizontal`<br/><br/>

- Flip the image vertically\
`vertical-flip khoury khoury-vertical`<br/><br/>

- Add sepia transform to the image\
`sepia khoury khoury-sepia`<br/><br/>

- Add greyscale transform to the image\
`greyscale khoury khoury-greyscale`<br/><br/>

- Add blur filter to the image\
`blur khoury khoury-blur`<br/><br/>

- Add sharpen filter to the image\
`sharpen khoury khoury-greyscale`<br/><br/>

- Overwrite the image with another file\
`load input/sample.ppm khoury`<br/><br/>


### 4. Modify or Add Commands
You can add a new file `CustomScript.txt` to the root folder and add custom operations to update the order of
operations or add new ones from the supported list. Once the file has been added, open the command
prompt and run the following commands,

```
>javac Main.java
>java Main CustomScript.txt
```

### 5. Citation

- The image, `khoury.ppm`, used in this project is clicked and owned by
[@shivang2402](https://github.com/shivang2402).
Please credit accordingly if used elsewhere.
- The image, `sample.ppm` is a custom image created and owned by
[@ShahParin](https://github.com/ShahParin). Please provide appropriate credit when used elsewhere.