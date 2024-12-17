package RPGgame;

import java.awt.*;

public class EventHandler {

    Panel panel;
    Rectangle eventHitbox;
    int eventX, eventY;

    public EventHandler(Panel panel) {
        this.panel = panel;
        eventHitbox = new Rectangle(23,23,2,2);
        eventX = 23;
        eventY = 23;
    }

    public void getEvent() {

    }

    public boolean eventHit(int eventCol, int eventRow, String direction) {
        boolean hit = false;

        panel.player.hitbox.x = panel.player.mapX + panel.player.hitbox.x;
        panel.player.hitbox.y = panel.player.mapY + panel.player.hitbox.y;
        eventHitbox.x = eventCol*panel.tileSize + eventX;
        eventHitbox.y = eventRow*panel.tileSize + eventY;

        if(panel.player.hitbox.intersects(eventHitbox)) {
            if (panel.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
                hit = true;
            }
        }

        panel.player.hitbox.x = panel.player.mapX;
        panel.player.hitbox.y = panel.player.mapY;
        eventHitbox.x = eventX;
        eventHitbox.y = eventY;

        return hit;
    }

    public void teleport (int gameState) {

        panel.gameState = gameState;


    }


}
