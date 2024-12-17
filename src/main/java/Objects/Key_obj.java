package Objects;

import RPGgame.Panel;

import javax.imageio.ImageIO;

public class Key_obj extends PolyObject{

    Panel panel;

    public Key_obj(Panel panel){

        objectName = "Key";
        collision = false;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/key1.png"));
            opt.scaleImage(image, panel.tileSize, panel.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
