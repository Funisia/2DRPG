package Entity;

import RPGgame.KeyInputHandler;
import RPGgame.Optimization;
import RPGgame.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyInputHandler key;
    public final int screenX;
    public final int screenY;
    public int keys = 0;

    public Player(Panel panel, KeyInputHandler key) {
        super(panel);
        this.key = key;
        screenX = panel.screenWidth/2 - (panel.tileSize/2);
        screenY = panel.screenHeight/2 - (panel.tileSize/2);
        // Player hitbox
        hitbox = new Rectangle();
        hitbox.x = 8;
        hitbox.y = 16;
        hitbox.width = 32;
        hitbox.height = 32;
        //
        hitboxBaseX = 8;
        hitboxBaseY = 16;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        maxHealth = 3;
        currentHealth = maxHealth;

        // Initializes Player Position
        mapX = panel.tileSize * 20;
        mapY = panel.tileSize * 18;
        //
        speed = 4;
        direction = "down";
    }

    public void update() {

        if (key.up || key.down || key.left || key.right) {
            // Updates character's position in game
            if (key.up) {
                direction = "up";
            }
            else if (key.down) {
                direction = "down";
            }
            else if (key.left) {
                direction = "left";
            }
            else if (key.right) {
                direction = "right";
            }

            // checks collision
            collisonOn = false;
            panel.collision.getTileCollision(this);
            int index = panel.collision.getObjectCollision(this, true);
            obtainObject(index);

            if (!collisonOn) {
                switch (direction) {
                    case "up":
                        mapY -= speed;
                        break;
                    case "down":
                        mapY += speed;
                        break;
                    case "left":
                        mapX -= speed;
                        break;
                    case "right":
                        mapX += speed;
                        break;
                }
            }

            // loops between player images to imitate walking
            spriteCount++;
            if (spriteCount > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }
    }

    public void obtainObject(int index) {
        if (index != 1000) {
            String objName = panel.obj[index].objectName;

            switch (objName) {
                case "Key":
                    keys++;
                    panel.obj[index] = null;
                    break;
                case "Chest":
                    if (keys >= 3) {
                        panel.obj[index] = null;
                        keys = keys - 3;
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2D) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                break;
        }
        g2D.drawImage(image, screenX, screenY, null); // draws player sprite
    }

    public void getPlayerImage() {
        up1 = prelude("knight_up1");
        up2 = prelude("knight_up2");
        down1 = prelude("knight_down1");
        down2 = prelude("knight_down2");
        left1 = prelude("knight_left1");
        left2 = prelude("knight_left2");
        right1 = prelude("knight_right1");
        right2 = prelude("knight_right2");
    }

    public BufferedImage prelude(String name) {
        Optimization opt = new Optimization();
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Player/"+ name +".png"));
            img = opt.scaleImage(img, panel.tileSize, panel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
