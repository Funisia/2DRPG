package RPGgame;

import Entity.Player;
import Objects.PolyObject;
import Tiles.TileHandler;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {
    // Settings for screen
    final int baseTileSize = 16;    // 16 x 16 pixel size for characters and tiles
    final int resoltuionScale = 3;  // Scales pixel size to monitor resolution

    // Scales tile size to monitor resolution
    // 48 X 48 tile here
    public final int tileSize = baseTileSize * resoltuionScale;

    // Screen Size
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    // Map Settings
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldColumn * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // Settings
    int FPS = 60; // FPS
    TileHandler tileHandler = new TileHandler(this);
    KeyInputHandler keyHandler = new KeyInputHandler(this);
    public CollisionHandler collision = new CollisionHandler(this);
    public ObjectHandler objectHandler = new ObjectHandler(this);
    public UserInterface userInterface = new UserInterface(this);
    public Player player = new Player(this, keyHandler);
    public EventHandler eventHandler = new EventHandler(this);
    public PolyObject obj[] = new PolyObject[10];

    Thread thread;

    // Other Map Settings
    public final int maxMap = 10;
    public int currentMap = 0;

    // Game state
    public int gameState;
    public final int playState = 0;
    public final int pauseState = 1;

    // constructor to initialize screen
    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true); // improves rendering performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // focuses Panel to receive key input
    }

    public void beginGameThread() {
        thread = new Thread(this);
        thread.start(); // begins thread & calls run method
    }

    public void setGame() {
        gameState = playState;
        objectHandler.setObject(this);
    }

    // Game loop (called by thread when started)
    @Override
    public void run() {

        // Slows draw time down to display game
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCounter = 0;

        // Recursively runs the game
        while (thread != null) {
            // Calculates wait time
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                updateGame(); // updates game
                repaint(); // Calls paintComponent method to draw game pixels
                delta--;
                drawCounter++;
            }

            if (timer >= drawInterval) {
                drawCounter = 0;
                timer = 0;
            }
        }
    }

    public void updateGame() {
        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            // logic to pause game
        }
    }

    // Built in method to draw on JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;  // casts Graphics object g to the Graphics2D class, allows us to use methods for 2D development

        // Draws tiles & Player
        // Tiles must be drawn first as it is layered
        tileHandler.draw(g2D);
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2D, this);
            }
        }
        userInterface.draw(g2D);
        player.draw(g2D);

        // Color Settings
        g2D.dispose(); // release resources after use
    }


}
