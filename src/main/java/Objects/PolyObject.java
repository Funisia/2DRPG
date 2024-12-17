package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import RPGgame.Optimization;
import RPGgame.Panel;

public class PolyObject {

    public BufferedImage image, image2;
    public String objectName;
    public boolean collision = false;
    public int mapX, mapY;
    public Rectangle hitbox = new Rectangle(0,0,48,48);
    public int hitboxBaseX = 0;
    public int hitboxBaseY = 0;

    Optimization opt = new Optimization();

    public void draw(Graphics2D g2D, Panel panel) {

        int screenX = mapX - panel.player.mapX + panel.player.screenX;
        int screenY = mapY - panel.player.mapY + panel.player.screenY;

        if (mapX + panel.tileSize > panel.player.mapX - panel.player.screenX
                && mapX - panel.tileSize < panel.player.mapX + panel.player.screenX
                && mapY + panel.tileSize > panel.player.mapY - panel.player.screenY
                && mapY - panel.tileSize < panel.player.mapY + panel.player.screenY) {
            g2D.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
        }

    }
}
