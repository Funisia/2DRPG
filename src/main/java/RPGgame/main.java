package RPGgame;

import javax.swing.*;
import java.awt.event.*;

public class main {

    public static void main(String[] args) {
        JFrame window  = new JFrame();
        Panel panel = new Panel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // allows to close window with "x" button
        window.setResizable(false); // Disables resizing the window
        window.setTitle("2D RPG Game");

        // Create new instance of game panel to create game window
        window.add(panel);  // add panel to the window
        window.pack(); // packs the window with the preferred settings defined in Panel

        window.setLocationRelativeTo(null); // Displays window at center of screen
        window.setVisible(true);
        panel.setGame();
        panel.beginGameThread();
    }
}
