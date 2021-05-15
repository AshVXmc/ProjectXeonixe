package com.ashvxmc.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private static final long serialVersionUID = 1L;

    public Window(int width, int length, String title, Game game){
        JFrame jFrame = new JFrame(title);

        jFrame.setPreferredSize(new Dimension(width, length));
        jFrame.setMaximumSize(new Dimension(width, length));
        jFrame.setMinimumSize(new Dimension(width,length));

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(game);
        jFrame.setVisible(true);
        game.start();
        game.run();
    }
}
