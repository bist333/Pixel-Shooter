package com.game.main;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Bist333 on 6/7/2017.
 */
public class Bullet extends GameObject {

    protected Handler handler;


    public Bullet(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        if (x < 0 || x > Game.WIDTH || y < 0 || y > Game.HEIGHT){
            handler.removeObject(this);
        }

        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,10,10);
    }

    public void collision(){
        LinkedList<GameObject> objects = handler.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            if (temp.getId() == ID.BasicBitch) {
                if (getBounds().intersects(temp.getBounds())) {
                    handler.removeObject(temp);
                    Spawner.numenemies--;
                    HUD.KILLS++;
                    handler.removeObject(this);
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,10,10);
    }
}
