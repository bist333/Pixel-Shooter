package com.game.main;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bist333 on 6/6/2017.
 */
public class BasicEnemy extends GameObject {

    public BasicEnemy(int x, int y, ID id){
        super(x,y,id);
        Random speed = new Random();
        velY = speed.nextInt(4)+2;
        velX = speed.nextInt(5)+2;
    }

    @Override
    public void tick() {
        x+= velX;
        y+= velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x,y,16,16);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
