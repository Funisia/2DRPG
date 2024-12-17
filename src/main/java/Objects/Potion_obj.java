package Objects;

import RPGgame.Panel;

import javax.imageio.ImageIO;

public class Potion_obj extends PolyObject{

    Panel panel;

    public Potion_obj(Panel panel){

        objectName = "Potion";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/potion1.png"));
            opt.scaleImage(image, panel.tileSize, panel.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
