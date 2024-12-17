package RPGgame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Optimization {

    public BufferedImage scaleImage(BufferedImage base, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, 2);
        Graphics2D g2D = scaledImage.createGraphics();
        g2D.drawImage(base,0,0,width,height, null);
        g2D.dispose();
        return scaledImage;
    }
}
