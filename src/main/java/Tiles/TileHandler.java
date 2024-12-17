package Tiles;

import RPGgame.Optimization;
import RPGgame.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileHandler {

    Panel panel;
    public Tile[] tile;
    public int mapTileNumber[][][];

    public TileHandler(Panel panel) {
        this.panel = panel;
        tile = new Tile[10]; // array to hold the different types of tiles
        mapTileNumber = new int[panel.maxMap][panel.maxWorldColumn][panel.maxWorldRow]; // array to hold tile numbers for map creation
        getTileImage();
        createMap("/Maps/room04.txt", 0); // map path
        //createMap("/Maps/room02.txt", 1);
    }

    public void getTileImage() {
            prelude(0, "grass", false );
            prelude(1, "earth", false );
            prelude(2, "ground", false );
            prelude(3, "wall", true );
            prelude(4, "water", true );
    }

    public void draw(Graphics2D g2D) {

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < panel.maxWorldColumn && worldRow < panel.maxWorldRow) {

            int tileNumber = mapTileNumber[panel.currentMap][worldColumn][worldRow];
            // Determines Player's position on world map
            int worldX = worldColumn * panel.tileSize;
            int worldY = worldRow * panel.tileSize;
            // Determines where to draw tiles from player position
            int screenX = worldX - panel.player.mapX + panel.player.screenX;
            int screenY = worldY - panel.player.mapY + panel.player.screenY;

            if (worldX + panel.tileSize > panel.player.mapX - panel.player.screenX
                    && worldX - panel.tileSize < panel.player.mapX + panel.player.screenX
                    && worldY + panel.tileSize > panel.player.mapY - panel.player.screenY
                    && worldY - panel.tileSize < panel.player.mapY + panel.player.screenY) {
                g2D.drawImage(tile[tileNumber].image, screenX, screenY, null);
            }
            worldColumn++;

            if (worldColumn == panel.maxWorldColumn) {
                worldColumn = 0; // reset if reach end of window
                worldRow++;
            }
        }
    }

    public void createMap (String path, int map) {

        try {
            InputStream input = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            int column = 0;
            int row = 0;

            while (column < panel.maxWorldColumn && row < panel.maxWorldRow) {

                String newLine = reader.readLine();

                while (column < panel.maxWorldColumn) {
                    String numberArray[] = newLine.split(" ");
                    int num = Integer.parseInt(numberArray[column]);
                    mapTileNumber[map][column][row] = num;
                    column++;
                }

                if (column == panel.maxWorldColumn) {
                    column = 0;
                    row++;
                }
            }
            reader.close();
        } catch (Exception e) {

        }

    }

    public void prelude (int index, String name, boolean collision) {
        Optimization opt = new Optimization();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/" + name + ".png"));
            tile[index].image = opt.scaleImage(tile[index].image, panel.tileSize, panel.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
