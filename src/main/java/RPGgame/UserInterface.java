package RPGgame;

import Objects.Heart_obj;
import Objects.Key_obj;
import Objects.PolyObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UserInterface {

    Panel panel;
    Graphics g2D;
    Font textFont;
    BufferedImage key, heart1, heart2;

    public UserInterface(Panel panel) {
        this.panel = panel;
        textFont = new Font("Arial", Font.PLAIN, 40);
        Key_obj keyObj = new Key_obj(panel);
        key = keyObj.image;

        PolyObject heart = new Heart_obj(panel);
        heart1 = heart.image;
        heart2 = heart.image2;
    }

    public void draw(Graphics2D g2D) {
        this.g2D = g2D;

        g2D.setFont(textFont);
        g2D.setColor(Color.black);
        g2D.drawImage(key,1,525, panel.tileSize, panel.tileSize,null);
        g2D.drawString("= " + panel.player.keys,47,560);

        if (panel.gameState == panel.playState) {
            displayHealth();
        }

        if (panel.gameState == panel.pauseState) {
            displayHealth();
        }
    }

    public void displayHealth() {
        panel.player.currentHealth = 3;

        int x = 610;
        int y = 10;

        // Display full hearts for currentHealth
        for (int i = 0; i < panel.player.maxHealth; i++) {
            if (i < panel.player.currentHealth) {
                g2D.drawImage(heart1, x, y, null); // Full heart
            } else {
                g2D.drawImage(heart2, x, y, null); // Empty heart
            }
            x += panel.tileSize; // Move to the next position
        }
    }
}
