package Entity;

import RPGgame.Panel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    Panel panel;
    // x,y, & speed values for ALL entities (player, enemies, npc, etc)
    public int currentHealth;
    public int maxHealth;
    public int mapX, mapY; // entity position on world map
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle hitbox;
    public int hitboxBaseX, hitboxBaseY;
    public boolean collisonOn = false;

    public Entity(Panel panel) {
        this.panel = panel;
    }
}
