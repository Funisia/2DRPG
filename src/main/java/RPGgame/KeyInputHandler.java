package RPGgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener {

    Panel panel;

    public boolean up, down, left, right;

    public KeyInputHandler(Panel panel) {
        this.panel = panel;

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Gets what key is pressed
        int key = e.getKeyCode();

        // Conditionals to check which key is pressed
        if (key == KeyEvent.VK_W) {
            up = true;
        }
        if (key == KeyEvent.VK_A) {
            left = true;
        }
        if (key == KeyEvent.VK_S) {
            down = true;
        }
        if (key == KeyEvent.VK_D) {
            right = true;
        }
        if (key == KeyEvent.VK_P) {
            if (panel.gameState == panel.playState) {
                panel.gameState = panel.pauseState;
            }
            else if (panel.gameState == panel.pauseState) {
                panel.gameState = panel.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Gets what key is pressed
        int key = e.getKeyCode();

        // Conditionals to check which key is released
        if (key == KeyEvent.VK_W) {
            up = false;
        }
        if (key == KeyEvent.VK_A) {
            left = false;
        }
        if (key == KeyEvent.VK_S) {
            down = false;
        }
        if (key == KeyEvent.VK_D) {
            right = false;
        }
    }
}
