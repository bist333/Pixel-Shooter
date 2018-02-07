package com.game.main;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 800, HEIGHT = 600;
    private Thread thread;
    private boolean running = false;
    private Random random = new Random();
    private Handler handler;
    private HUD hud;
    private Spawner spawner;

    private int cointicks = 0;


    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));
        this.hud = new HUD(handler);
        spawner = new Spawner(handler);
        new Window(WIDTH,HEIGHT,"swaggy game",this);


        handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler));
        handler.addObject(new BasicEnemy(random.nextInt(WIDTH),random.nextInt(HEIGHT),ID.BasicBitch));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                if (cointicks >= 10){
                    handler.addObject(new Coin(random.nextInt(WIDTH),random.nextInt(HEIGHT),ID.Coin,handler));
                    cointicks = 0;
                }
                cointicks++;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }



    public static int clamp(int var,int min,int max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return (var = min);
        else
            return var;
    }


    public static void main(String[] args) {
        Game game = new Game();
    }
}
