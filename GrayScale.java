
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;

public class GrayScale {

  static BufferedImage image;
  static int width;
  static int height;
  static File input;
  static String output;
  static String fileName;

  public GrayScale(int number) {

    try {
      image = ImageIO.read(input);

      for (int i = 0; i < number; i++) {
        System.out.println("Persentage " + height / number);

        for (int j = 0; j < width; j++) {

          Color c = new Color(image.getRGB(j, i));

          int alpha = (image.getRGB(i, j) >> 24) & 0xff;

          if (alpha == 0) {
            // Now we will have pixel with Alpha 0 (Transparent
            // pixel)
            // As example If you need to fill transparent pixels
            // with white color
            // use this code
            // buffImage.setRGB(i, j, Color.white.getRGB());
            image.setRGB(i, j, 0);
          } else {

            int red = (int) (c.getRed() * 0.299);
            int green = (int) (c.getGreen() * 0.587);
            int blue = (int) (c.getBlue() * 0.114);
            Color newColor = new Color(red + green + blue,

            red + green + blue, red + green + blue);

            image.setRGB(j, i, newColor.getRGB());
          }

        }
      }

      File ouptut = new File(output + fileName + number + ".png");
      ImageIO.write(image, "png", ouptut);

    } catch (Exception e) {
    }
  }

  static public void main(String args[]) throws Exception {

    input = new File(args[0]);
    output = args[2];
    fileName = stripExtension(input.getName());
    System.out.println(fileName);

    image = ImageIO.read(input);
    width = image.getWidth();
    height = image.getHeight();

    for (int count = height; count >= 1; count--) {

      GrayScale obj = new GrayScale(count);
    }
  }

  static String stripExtension(String str) {
    // Handle null case specially.

    if (str == null)
      return null;

    // Get position of last '.'.

    int pos = str.lastIndexOf(".");

    // If there wasn't any '.' just return the string as is.

    if (pos == -1)
      return str;

    // Otherwise return the string, up to the dot.

    return str.substring(0, pos);
  }
}