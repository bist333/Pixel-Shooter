package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by Bist333 on 6/6/2017.
 */
public class KeyInput extends KeyAdapter {

    private static final int pSpeed = 3;
    private TreeSet<Integer> curheld;
    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
        curheld = new TreeSet<>();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        LinkedList<GameObject> objects = handler.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);

            if (temp.getId() == ID.Player && Player.alive) {
                if (key == KeyEvent.VK_W) {
                    temp.setVelY(-pSpeed);
                    curheld.add(KeyEvent.VK_W);
                }
                if (key == KeyEvent.VK_A) {
                    temp.setVelX(-pSpeed);
                    curheld.add(KeyEvent.VK_A);
                }
                if (key == KeyEvent.VK_S) {
                    temp.setVelY(pSpeed);
                    curheld.add(KeyEvent.VK_S);
                }
                if (key == KeyEvent.VK_D) {
                    temp.setVelX(pSpeed);
                    curheld.add(KeyEvent.VK_D);
                }
                if (key == KeyEvent.VK_UP) {
                    Player pTemp = (Player) temp;
                    pTemp.shoot("U");
                }
                if (key == KeyEvent.VK_DOWN) {
                    Player pTemp = (Player) temp;
                    pTemp.shoot("D");
                }
                if (key == KeyEvent.VK_LEFT) {
                    Player pTemp = (Player) temp;
                    pTemp.shoot("L");
                }
                if (key == KeyEvent.VK_RIGHT) {
                    Player pTemp = (Player) temp;
                    pTemp.shoot("R");
                }
            }

        }

        if (key== KeyEvent.VK_ESCAPE) System.exit(1);
        if (key == KeyEvent.VK_P) handler.pause();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (GameObject temp: handler.getObjects()){
            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    if (curheld.contains(KeyEvent.VK_S)) {
                        temp.setVelY(pSpeed);
                    } else {
                        temp.setVelY(0);
                    }
                    curheld.remove((Integer) KeyEvent.VK_W);
                }
                if (key == KeyEvent.VK_A) {
                    if (curheld.contains(KeyEvent.VK_D)) {
                        temp.setVelX(pSpeed);
                    } else {
                        temp.setVelX(0);
                    }
                    curheld.remove((Integer) KeyEvent.VK_A);
                }
                if (key == KeyEvent.VK_S) {
                    if (curheld.contains(KeyEvent.VK_W)) {
                        temp.setVelY(-pSpeed);
                    } else {
                        temp.setVelY(0);
                    }
                    curheld.remove((Integer) KeyEvent.VK_S);
                }
                if (key == KeyEvent.VK_D) {
                    if (curheld.contains(KeyEvent.VK_A)) {
                        temp.setVelX(-pSpeed);
                    } else {
                        temp.setVelX(0);
                    }
                    curheld.remove((Integer) KeyEvent.VK_D);
                }
            }

        }



    }
}
