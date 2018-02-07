package com.game.main;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Bist333 on 6/11/2017.
 */
public class EnemyBullet extends GameObject {

    private Handler handler;

    public EnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x,y,10,10);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,10,10);
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

    public void collision(){
        LinkedList<GameObject> objects = handler.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            if (temp.getId() == ID.Player) {
                if (getBounds().intersects(temp.getBounds())) {
                    HUD.HEALTH -= 10;
                    handler.removeObject(this);
                }
            }
        }
    }
}
