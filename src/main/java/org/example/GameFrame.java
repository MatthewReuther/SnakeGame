package org.example;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame() {

        this.add(new GamePanel());
        this.setTitle("Game Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        //have frame appear in middle of screen
        this.setLocationRelativeTo(null);

    }

}
