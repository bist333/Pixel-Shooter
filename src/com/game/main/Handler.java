package com.game.main;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Bist333 on 6/6/2017.
 */
public class Handler {
    private LinkedList<GameObject> objects = new LinkedList<>();
    private boolean pause = false;


    public synchronized void tick(){
        while (pause) try {
            Thread.sleep(100);
        } catch (Exception e){
            e.printStackTrace();
        };
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g){
        for (GameObject temp : objects){
            temp.render(g);
        }
    }

    public void pause(){
        if (pause){
            pause = false;
            notifyAll();
        }
        else pause = true;
    }

    public void addObject(GameObject add){
        this.objects.add(add);
    }

    public void removeObject(GameObject rem){
        this.objects.remove(rem);
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }

    public int numof(ID id){
        int num = 0;
        for (GameObject temp: objects){
            if (temp.getId() == id) num++;
        }
        return num;
    }

    //    public GameObject find(ID id){
//        for (GameObject temp: objects){
//
//        }
//    }
}
