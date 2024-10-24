package testModel;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import model.Image;
import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ImageModelImplTest {
  private String filePath;
  private String pathRelative;
  private ImageModel imageModel = new ImageModelImpl();


  @Before
  public void setUp() {
    try {
      filePath = "input/khoury.ppm";
      pathRelative = new File(System.getProperty("user.dir")) + File.separator + "images" + File.separator + filePath;
//      System.out.println(pathRelative);
      imageModel.loadImage(filePath, "khoury");

    } catch (Exception e) {
      fail("Failed to load image file in setup." + e.getMessage());
    }
  }


  @Test
  public void testLoadImagePPM() throws IOException {
    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream(pathRelative));
    } catch (FileNotFoundException e) {
      fail("Failed to load image file in load ppm."+e.getMessage());
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.startsWith("#")) {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());
    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxPixel = sc.nextInt();

    imageModel.saveImage("output/sampletest_b4.ppm", "khoury");
    Image image = imageModel.getImage("khoury");
    imageModel.saveImage("output/sampletest_af.ppm", "khoury");

    int[][] redChannel = image.getRedChannel();
    int[][] greenChannel = image.getGreenChannel();
    int[][] blueChannel = image.getBlueChannel();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        System.out.print(redChannel[i][j] + " ");
        System.out.println(sc.nextInt());
        System.out.print(greenChannel[i][j] + " ");
        System.out.println(sc.nextInt());
        System.out.print(blueChannel[i][j] + " ");
        System.out.println(sc.nextInt());
//        assertEquals(redChannel[i][j], sc.nextInt());
//        assertEquals(greenChannel[i][j], sc.nextInt());
//        assertEquals(blueChannel[i][j], sc.nextInt());
      }
    }
  }
}
