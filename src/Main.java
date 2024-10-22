import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageModelImpl;


public class Main {
  // Method to write a BufferedImage to a PPM file
  public static void writePPM(File khouryFile, BufferedImage image) throws IOException {
    try (FileWriter writer = new FileWriter(khouryFile)) {
      int width = image.getWidth();
      int height = image.getHeight();

      // Write the PPM header
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n");  // Max color value

      // Write pixel data (R G B values)
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int pixel = image.getRGB(x, y);

          // Extract RGB components
          int red = (pixel >> 16) & 0xFF;
          int green = (pixel >> 8) & 0xFF;
          int blue = pixel & 0xFF;

          // Write RGB values
          writer.write(red + " " + green + " " + blue + " ");
        }
        writer.write("\n");
      }
    }
  }

  public static void main(String[] args) {
    ImageModelImpl imageAbs = new ImageModelImpl();

    imageAbs.loadImage("src/khoury.ppm","khoury");
    imageAbs.brightenImage(50,"khoury","khouryBrighten");
    imageAbs.saveImage("src/khouryBrighten.ppm","khouryBrighten");

    imageAbs.loadImage("src/khoury.ppm","khoury");
    imageAbs.brightenImage(-50,"khoury","khouryDarken");
    imageAbs.saveImage("src/khouryDarken.ppm","khouryDarken");

//    imageAbs.loadImage("src/khoury.ppm","khoury");
//    imageAbs.flipVertically("khoury","khouryVertical");
//    imageAbs.saveImage("src/khouryVertical.ppm","khouryVertical");
//
//    imageAbs.loadImage("src/khoury.ppm","khoury");
//    imageAbs.flipHorizontally("khoury","khouryHorizontal");
//    imageAbs.saveImage("src/khouryHorizontal.ppm","khouryHorizontal");

//    imageAbs.loadImage("src/khoury.ppm","khoury");
//    imageAbs.sharpenImage("khoury","khourySharpen");
//    imageAbs.saveImage("src/khourySharpen.ppm","khourySharpen");

//    imageAbs.loadImage("src/khoury.ppm", "khoury");
//    imageAbs.saveImage("src/khouryOutput.ppm", "khoury");
//
//    imageAbs.blurImage("khoury", "khouryBlur");
//    imageAbs.saveImage("src/khouryBlur.ppm", "khouryBlur");
//
//    imageAbs.blurImage("khouryBlur", "khouryBlur2");
//    imageAbs.blurImage("khouryBlur2", "khouryBlur3");
//    imageAbs.blurImage("khouryBlur3", "khouryBlur4");
//    imageAbs.saveImage("src/khouryBlur4.ppm", "khouryBlur4");
//
//    imageAbs.applyGreyscale("khoury", "khouryGrey");
//    imageAbs.saveImage("src/khouryGrey.ppm", "khouryGrey");
//
//    imageAbs.applySepia("khoury", "khourySepia");
//    imageAbs.saveImage("src/khourySepia.ppm", "khourySepia");
//
//    imageAbs.applyRedComponent("khoury", "khouryRed");
//    imageAbs.saveImage("src/khouryRed.ppm", "khouryRed");
//
//    imageAbs.applyGreenComponent("khoury", "khouryGreen");
//    imageAbs.saveImage("src/khouryGreen.ppm", "khouryGreen");
//
//    imageAbs.applyBlueComponent("khoury", "khouryBlue");
//    imageAbs.saveImage("src/khouryBlue.ppm", "khouryBlue");

//    imageAbs.flipHorizontally("khoury", "khouryHflip");
//    imageAbs.flipVertically("khoury", "khouryVflip");
//
//    imageAbs.saveImage("src/khouryHflip.ppm", "khouryHflip");
//    imageAbs.saveImage("src/khouryVflip.ppm", "khouryVflip");

    // jpeg to ppm
    /*try {
      // Load the JPEG image
      File inputFile = new File("src/khoury.jpg");
      BufferedImage image = ImageIO.read(inputFile);

      // Save the image as PPM
      File khouryFile = new File("src/khoury.ppm");
      writePPM(khouryFile, image);

      System.out.println("Conversion successful!");

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }*/
  }
}
