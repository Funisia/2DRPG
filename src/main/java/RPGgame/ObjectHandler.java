package RPGgame;

import Objects.*;

public class ObjectHandler {

    Panel panel;

    public ObjectHandler(Panel panel) {
        this.panel = panel;
    }

    public void setObject(Panel panel) {
        panel.obj[0] = new Key_obj(panel);
        panel.obj[0].mapX = panel.tileSize * 23;
        panel.obj[0].mapY = panel.tileSize * 18;

        panel.obj[1] = new Chest_obj(panel);
        panel.obj[1].mapX = panel.tileSize * 17;
        panel.obj[1].mapY = panel.tileSize * 17;

        panel.obj[2] = new Key_obj(panel);
        panel.obj[2].mapX = panel.tileSize * 36;
        panel.obj[2].mapY = panel.tileSize * 35;

        panel.obj[3] = new Key_obj(panel);
        panel.obj[3].mapX = panel.tileSize * 36;
        panel.obj[3].mapY = panel.tileSize * 18;
    }
}
