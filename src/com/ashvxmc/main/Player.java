package com.ashvxmc.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
    Random random = new Random();

    public Player(int x, int y, ID id) {
        super(x, y, id);

        velX = random.nextInt(5) + 1;
        velY = random.nextInt(5);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x,y,32,32);
    }
}
