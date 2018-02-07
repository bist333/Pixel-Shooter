package com.game.main;

import java.util.Random;

import static com.game.main.Game.HEIGHT;
import static com.game.main.Game.WIDTH;

/**
 * Created by Bist333 on 6/8/2017.
 */
public class Spawner {

    private Random random = new Random();
    private Handler handler;
    public static int numenemies = 1;
    private int wave = 1;
    public static boolean boss = false;

    public Spawner(Handler handler){
        this.handler = handler;
    }

    public void tick(){
        if (numenemies == 0){
            if (wave < 10 && !boss) wave++;
            if (wave == 5 && !boss) boss(5);
            else if (!boss) addenemys(wave);
        }
    }

    public void addenemys(int num){
        for (int i = 0; i < num; i++){
            handler.addObject(new BasicEnemy(Game.clamp(random.nextInt(WIDTH-17),1,WIDTH-32),Game.clamp(random.nextInt(HEIGHT-17),0,HEIGHT-32),ID.BasicBitch));
            numenemies++;
        }
    }

    public void boss(int wave){
        boss = true;
        handler.addObject(new Boss(WIDTH/2,30,ID.Boss,handler));
    }

}
