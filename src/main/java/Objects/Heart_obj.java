package Objects;

import RPGgame.Panel;
import javax.imageio.ImageIO;

public class Heart_obj extends PolyObject{

    Panel panel;

    public Heart_obj(Panel panel){

        objectName = "Heart";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/heart1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart2.png"));
            image = opt.scaleImage(image, panel.tileSize, panel.tileSize);
            image2= opt.scaleImage(image2, panel.tileSize, panel.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
