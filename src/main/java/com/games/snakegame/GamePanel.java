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

        //create for loop to loop through all body parts of the snake
        for (int i = 0; i < bodyParts; i++) {
            //if index is head of snake
            if (i == 0) {
                graphics.setColor(Color.cyan);
                //fill coordinate with with cyan color and size of grid item (25px)
                graphics.fillRect(xCords[i], yCords[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                //dealing with body of snake
                graphics.setColor(Color.green);
                graphics.fillRect(xCords[i], yCords[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
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

    //checks if head of snake collides with body of snake
    public void checkCollision() {
        //iterate through all of the body parts of the snake
        //decrement i to go through each of the body parts
        for (int i = bodyParts; i > 0; i--) {
            //if the head of the snake x cord is equal this index x cord
            //and the head of the snake y cord is equal this index y cord you collided
            if((xCords[0] == xCords[i]) && (yCords[0] == yCords[i])) {
                running = false;
            }

            //check if the head of the snake hits left side of game panel border
            if(xCords[0] < 0 ) {
                running = false;
            }

            //check if the head of the snake hits right side of game panel border
            if(xCords[0] > SCREEN_WIDTH ) {
                running = false;
            }

            //check if the head of the snake hits top side of game panel border
            if(yCords[0] < 0 ) {
                running = false;
            }

            //check if the head of the snake hits bottom side of game panel border
            //if yCord is greater than our screen height
            if(yCords[0] > SCREEN_HEIGHT ) {
                running = false;
            }

            //if not running stop the timer
            if (!running) {
                timer.stop();
            }

        }

    }

    public void gameOver(Graphics graphics) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //if the game is running
        if (running) {
            //move the snake
            move();
            //if we ate an edible
            checkEdible();
            //if we collided with our snake body"
            checkCollision();
        }
        //if it is not running call repaint method and create new panel
        repaint();

    }
}
