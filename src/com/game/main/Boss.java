package com.game.main;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

/**
 * Created by Bist333 on 6/11/2017.
 */
public class Boss extends GameObject {

    private Handler handler;
    private int health;

    private boolean canShoot = true;
    private Instant lastshot = Instant.now();

    public Boss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.health = 20;
        velX = 6;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;


        if (Duration.between(lastshot,Instant.now()).getNano() >= 250000000) canShoot = true;
        shoot("DOESNT MATTER ONLY SHOOTS DOWN");

        if (x <= 0 || x >= Game.WIDTH - 80) velX *= -1;

        if (health == 0){
            handler.removeObject(this);
            Spawner.boss = false;
        }

        collision();
    }

    public void collision(){
        LinkedList<GameObject> objects = handler.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            if (temp.getId() == ID.Bullet) {
                if (getBounds().intersects(temp.getBounds())) {
                    health -= 2;
                    handler.removeObject(temp);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,80,80);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,80,80);
    }

    public void shoot(String dir){
        if (canShoot){
            EnemyBullet b = new EnemyBullet(x+40,y+40,ID.EnemyBullet,handler);
            b.setVelY(7);
            handler.addObject(b);
            lastshot = Instant.now();
            canShoot = false;
        }
    }

}
