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

    //Set score to amount of 'edibles' eaten
    int ediblesAte;
    int edibleX;
    int edibleY;

    //Set direction of snake at start
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        //
        newEdible();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        //"super" calls parent class of current object
        //paintCompoenent method from parent class of GamePanel which is JPanel
        super.paintComponent(graphics);
        draw(graphics);
    }

    //Turns panel into matrix or grid for easier visibility
    public void draw(Graphics graphics) {
        graphics.setColor(Color.lightGray);
        //draws lines across panel to make it become grid
        for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            graphics.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            graphics.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }

        //draw new edible on grid
        graphics.setColor(Color.red);
        graphics.fillOval(edibleX, edibleY, UNIT_SIZE, UNIT_SIZE);
    }

    //generate new edible for game panel when this called
    public void newEdible(){
        //add new edible to panel in random point of our grid
        //set range equal to screen width (800px) divided the size of the edible (25px)
        edibleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        edibleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;

    }

    //method implementing the movement of the snake
    public void move() {
        //iterate through all body part of snake
        for(int i = bodyParts; i > 0; i--) {
            //shift all the coordinates of the xCords arr by one
            xCords[i] = xCords[i-1];
            yCords[i] = yCords[i-1];
        }

        switch (direction) {
            case 'U':
                //set head (0 index) of yCords arr to the next position
                yCords[0] = yCords[0] - UNIT_SIZE;
                break;
            case 'D':
                //set head (0 index) of yCords arr to the next position
                //think of arr on graph going up and down
                yCords[0] = yCords[0] + UNIT_SIZE;
                break;
            case 'L':
                //set head (0 index) of xCords arr to the next position
                xCords[0] = xCords[0] - UNIT_SIZE;
                break;
            case 'R':
                //set head (0 index) of xCords arr to the next position
                //think of arr on graph going right to left
                xCords[0] = xCords[0] + UNIT_SIZE;
                break;

        }
    }

    public void checkEdible() {

    }

    public void checkCollision() {

    }

    public void gameOver(Graphics graphics) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
