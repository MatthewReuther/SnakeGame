package com.games.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import com.games.snakegame.MyKeyAdapter;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;

    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    //higher the slower game is
    static final int DELAY = 75;

    //holds coordinates for all body parts of our snake
    final int xCords[]   = new int[GAME_UNITS];
    final int yCords[]   = new int[GAME_UNITS];

    //initial amount of snake body parts
    int bodyParts = 6;

    //Set score to amount of 'apples' eaten
    int score;
    int scoreX;
    int scoreY;

    //Set direction of snake at start
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {

    }

    public void startGame() {

    }

    public void paintComponent(Graphics graphics) {

    }

    public void drawComponent(Graphics graphics) {

    }

    public void move() {

    }

    public void checkScore() {

    }

    public void checkCollision() {

    }

    public void gameOver(Graphics graphics) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
