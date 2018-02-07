package com.game.main;

import java.awt.*;

/**
 * Created by Bist333 on 6/7/2017.
 */
public class HUD {

    public static int HEALTH = 100;
    public static int KILLS = 0;
    public static int COINS = 0;
    private Handler handler;
    private Shop shop;

    public HUD(Handler handler){
        this.handler = handler;
        this.shop = new Shop();
    }

    public void tick(){

        HEALTH = Game.clamp(HEALTH,0,100);



    }

    public void render(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(20,20,400,40);
        g.setColor(Color.GREEN);
        g.fillRect(20,20,HEALTH*4,40);
        g.setColor(Color.WHITE);
        g.drawRect(20,20,400,40);

        g.drawString("Enemies:" + Spawner.numenemies,450,20);
        g.drawString("Kills:"+KILLS,450,35);
        g.drawString("Coins:"+COINS,450,50);


        if (HEALTH == 0){
            Player.alive = false;
            g.setColor(Color.RED);
            g.setFont(g.getFont().deriveFont((float)40));
            g.drawString("YOU LOSE",20,55);
        }

        shop.render(g);

    }

}
