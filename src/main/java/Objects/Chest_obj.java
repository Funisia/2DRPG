package Objects;

import RPGgame.Panel;

import javax.imageio.ImageIO;

public class Chest_obj extends PolyObject{

    Panel panel;

    public Chest_obj(Panel panel){

        objectName = "Chest";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest1.png"));
            opt.scaleImage(image, panel.tileSize, panel.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
