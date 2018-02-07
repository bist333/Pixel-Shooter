package com.game.main;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Bist333 on 6/8/2017.
 */
public class Coin extends GameObject {

    private Handler handler;
    private float ttl;

    public Coin(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {

        collision();
    }

    public void collision(){
        LinkedList<GameObject> objects = handler.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            if (temp.getId() == ID.Player) {
                if (getBounds().intersects(temp.getBounds())) {
                    HUD.HEALTH += 5;
                    HUD.COINS++;
                    handler.removeObject(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,8,8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
