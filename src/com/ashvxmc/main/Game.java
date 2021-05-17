package com.ashvxmc.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    // 16:9 ratio window
    public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private final Random random = new Random();
    private final Handler handler;

    // Constructor
    public Game(){
        handler = new Handler();
        // Keyboard input listener
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT , "Project Xeonixe [Build 0.0.1]", this);

        Random random = new Random();

        handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            // FPS Counter
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
              //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args){
        new Game();
    }
}
