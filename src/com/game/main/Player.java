package com.game.main;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

/**
 * Created by Bist333 on 6/6/2017.
 */
public class Player extends GameObject {

    private Handler handler;
    private Instant lastshot = Instant.now();
    private boolean canShoot = true;
    public static boolean alive = true;

    public Player(int x,int y, ID id,Handler handler){
        super(x,y,id);
        this.handler = handler;
    }



    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x,0,Game.WIDTH-37);
        y = Game.clamp(y,0,Game.HEIGHT-60);

        if (Duration.between(lastshot,Instant.now()).getSeconds() >= 1) canShoot = true;

        collision();
    }

    public void collision(){
        LinkedList<GameObject> objects = handler.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            if (temp.getId() == ID.BasicBitch) {
                if (getBounds().intersects(temp.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
            if (temp.getId() == ID.Boss){
                if (getBounds().intersects(temp.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x,y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public void shoot(String dir){
        if (canShoot){
            Bullet b = new Bullet(x+11,y+11,ID.Bullet,handler);
            switch (dir){
                case "U":
                    b.setVelY(-7);
                    break;
                case "D":
                    b.setVelY(7);
                    break;
                case "L":
                    b.setVelX(-7);
                    break;
                case "R":
                    b.setVelX(7);
                    break;
            }
            handler.addObject(b);
            lastshot = Instant.now();
            canShoot = false;

        }
    }


}
