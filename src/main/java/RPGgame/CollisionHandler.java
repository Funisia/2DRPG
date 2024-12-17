package RPGgame;

import Entity.Entity;
import Tiles.TileHandler;

public class CollisionHandler {

    Panel panel;

    public CollisionHandler(Panel panel) {
        this.panel = panel;
    }

    public void getTileCollision(Entity entity) {

        int entityLeftX = entity.mapX + entity.hitbox.x;
        int entityRightX = entity.mapX + entity.hitbox.x + entity.hitbox.width;
        int entityTopY = entity.mapY + entity.hitbox.y;
        int entityBottomY = entity.mapY + entity.hitbox.y + entity.hitbox.height;

        int entityLeftColumn = entityLeftX/panel.tileSize;
        int entityRightColumn = entityRightX/panel.tileSize;
        int entityTopRow = entityTopY/panel.tileSize;
        int entityBottomRow = entityBottomY/panel.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed)/panel.tileSize; // gets tile we are moving into
                tileNumber1 = panel.tileHandler.mapTileNumber[panel.currentMap][entityLeftColumn][entityTopRow]; // checks top left portion of hitbox
                tileNumber2 = panel.tileHandler.mapTileNumber[panel.currentMap][entityRightColumn][entityTopRow]; // checks top right portion of hitbox
                if (panel.tileHandler.tile[tileNumber1].collision == true || panel.tileHandler.tile[tileNumber2].collision == true) { // True if player is running into non-traversable tile
                    entity.collisonOn = true; // stops player movement
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/panel.tileSize; // gets tile we are moving into
                tileNumber1 = panel.tileHandler.mapTileNumber[panel.currentMap][entityLeftColumn][entityBottomRow]; // checks top left portion of hitbox
                tileNumber2 = panel.tileHandler.mapTileNumber[panel.currentMap][entityRightColumn][entityBottomRow]; // checks top right portion of hitbox
                if (panel.tileHandler.tile[tileNumber1].collision == true || panel.tileHandler.tile[tileNumber2].collision == true) { // True if player is running into non-traversable tile
                    entity.collisonOn = true; // stops player movement
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftX - entity.speed)/panel.tileSize; // gets tile we are moving into
                tileNumber1 = panel.tileHandler.mapTileNumber[panel.currentMap][entityLeftColumn][entityTopRow]; // checks top left portion of hitbox
                tileNumber2 = panel.tileHandler.mapTileNumber[panel.currentMap][entityLeftColumn][entityBottomRow]; // checks top right portion of hitbox
                if (panel.tileHandler.tile[tileNumber1].collision == true || panel.tileHandler.tile[tileNumber2].collision == true) { // True if player is running into non-traversable tile
                    entity.collisonOn = true; // stops player movement
                }
                break;
            case "right":
                entityRightColumn = (entityRightX + entity.speed)/panel.tileSize; // gets tile we are moving into
                tileNumber1 = panel.tileHandler.mapTileNumber[panel.currentMap][entityRightColumn][entityTopRow]; // checks top left portion of hitbox
                tileNumber2 = panel.tileHandler.mapTileNumber[panel.currentMap][entityRightColumn][entityBottomRow]; // checks top right portion of hitbox
                if (panel.tileHandler.tile[tileNumber1].collision == true || panel.tileHandler.tile[tileNumber2].collision == true) { // True if player is running into non-traversable tile
                    entity.collisonOn = true; // stops player movement
                }
                break;
        }
    }

    public int getObjectCollision(Entity entity, boolean character) {

        int index = 1000;

        for (int i = 0; i < panel.obj.length; i++) {
            if (panel.obj[i] != null) {

                // Return entity's hitbox position
                entity.hitbox.x = entity.mapX + entity.hitbox.x;
                entity.hitbox.y = entity.mapY + entity.hitbox.y;
                // Return object's hitbox position
                panel.obj[i].hitbox.x = panel.obj[i].mapX + panel.obj[i].hitbox.x;
                panel.obj[i].hitbox.y = panel.obj[i].mapY + panel.obj[i].hitbox.y;

                switch (entity.direction) {
                    case "up":
                        entity.hitbox.y -= entity.speed;
                        if(entity.hitbox.intersects(panel.obj[i].hitbox)) {
                            if (panel.obj[i].collision) {
                                entity.collisonOn = true;
                            }
                            if (character) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitbox.y += entity.speed;
                        if(entity.hitbox.intersects(panel.obj[i].hitbox)) {
                            if (panel.obj[i].collision) {
                                entity.collisonOn = true;
                            }
                            if (character) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitbox.x -= entity.speed;
                        if(entity.hitbox.intersects(panel.obj[i].hitbox)) {
                            if (panel.obj[i].collision) {
                                entity.collisonOn = true;
                            }
                            if (character) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitbox.x += entity.speed;
                        if(entity.hitbox.intersects(panel.obj[i].hitbox)) {
                            if (panel.obj[i].collision) {
                                entity.collisonOn = true;
                            }
                            if (character) {
                                index = i;
                            }
                        }
                        break;
                }
                // Resets hitbox values
                entity.hitbox.x = entity.hitboxBaseX;
                entity.hitbox.y = entity.hitboxBaseY;
                panel.obj[i].hitbox.x = panel.obj[i].hitboxBaseX;
                panel.obj[i].hitbox.y = panel.obj[i].hitboxBaseY;
            }
        }

        return index;
    }
}
