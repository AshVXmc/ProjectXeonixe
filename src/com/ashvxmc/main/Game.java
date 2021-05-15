package com.ashvxmc.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas {
    private static final long serialVersionUID = 1L;

    // 16:9 ratio window
    public static int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    // Constructor
    public Game(){
        new Window(WIDTH, HEIGHT , "Project Xeonixe [Build 0.0.1]", this);
    }

    public synchronized void start(){
        thread = new Thread((Runnable) this);
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

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0D;
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
            if (running) {
                render();
                frames++;
            }

            // FPS Counter
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("Current FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

    }

    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null){
            this.createBufferStrategy(3);
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.GREEN);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args){
        new Game();
    }
}
