
package main;

import objects.GameChar;
import objects.Pipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


import objects.BottomPipe;
import objects.TopPipe;
import static main.Main.*;



public class GamePanel extends JPanel implements ActionListener, KeyListener {
    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    GameChar bird;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;
    
    int pipeHeight = 512;
    int pipeWidth = 64;
    int pipeY = 0;
    int pipeX = BOARD_WIDTH;
    
    ArrayList<TopPipe> topPipes;
    ArrayList<BottomPipe> bottomPipes;
    Random random = new Random();
    GameScore gameScore;

    Timer gameLoop;
    Timer placePipeTimer;

    boolean gameOver = false;

    
    GamePanel(Image selectedChar) throws IOException {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        this.birdImg = selectedChar;
        
        gameScore = new GameScore();
        
        // Load images
        backgroundImg = GameUI.loadImg(GameUI.BACKGROUND);
        topPipeImg = GameUI.loadImg(GameUI.TOP_PIPE);
        bottomPipeImg = GameUI.loadImg(GameUI.BOTTOM_PIPE);

        bird = new GameChar(birdImg);
        topPipes = new ArrayList<>();
        bottomPipes = new ArrayList<>();

        // Place pipes timer
        placePipeTimer = new Timer(1800, e -> placePipes());
        placePipeTimer.start();

        // Game timer
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    void placePipes() {
        int openingSpace = BOARD_HEIGHT / 4;
        int randomTopPipeY = random.nextInt(pipeY-pipeHeight + BOARD_HEIGHT / 5,
                pipeY-pipeHeight + 352);
        TopPipe topPipe = new TopPipe(pipeX, randomTopPipeY, pipeWidth, pipeHeight, topPipeImg, gameScore.getScore());
        
        int randomBottomPipeY = randomTopPipeY + pipeHeight + openingSpace;
        BottomPipe bottomPipe = new BottomPipe(BOARD_WIDTH, randomBottomPipeY, 
                64, 512, bottomPipeImg, gameScore.getScore());
        
        if (GameScore.score >= 5) {
        int initialDirection = random.nextBoolean() ? 1 : -1;
        topPipe.setDirection(initialDirection);
        bottomPipe.setDirection(initialDirection);
        }

        topPipes.add(topPipe);
        bottomPipes.add(bottomPipe);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            draw(g);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void draw(Graphics g) throws IOException {
        g.drawImage(backgroundImg, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);
        bird.draw(g);

        for (TopPipe pipe : topPipes) 
            pipe.draw(g);
        for (BottomPipe pipe : bottomPipes) 
            pipe.draw(g);
        
        gameScore.draw(g, gameOver);
    }

    public void move() {
        velocityY += gravity;
        
        bird.setBirdY(Math.min(bird.getBirdY() + velocityY, BOARD_HEIGHT));
        
        for (TopPipe pipe : topPipes) {
            pipe.moveHorizontally(velocityX);
            if (gameScore.getScore() >= 5) {
                pipe.moveVertically();
            }
            if (!pipe.isPassed() && bird.getBirdX() > pipe.getPipeX() + pipe.getPipeWidth()) {
                gameScore.setScore(gameScore.getScore() + 0.5);
                pipe.setPassed(true);
            }
            if (collision(bird, pipe)) {
                gameScore.newHighscore(gameScore.getScore());
                gameOver = true;
            }
        }

        for (BottomPipe pipe : bottomPipes) {
            pipe.moveHorizontally(velocityX);
            
            if (gameScore.getScore() >= 5) {
                pipe.moveVertically();
                
            }
            if (!pipe.isPassed() && bird.getBirdX() > pipe.getPipeX() + pipe.getPipeWidth()) {
                gameScore.setScore(gameScore.getScore() + 0.5);
                pipe.setPassed(true);
            }
            if (collision(bird, pipe)) {
                gameScore.newHighscore(gameScore.getScore());
                gameOver = true;
            }
        }

        if (bird.getBirdY() >= BOARD_HEIGHT) {
            gameOver = true;
        }
    }

    boolean collision(GameChar a, Pipe b) {
        return a.getBirdX() < b.getPipeX() + b.getPipeWidth()
                && a.getBirdX() + a.getBirdWidth() > b.getPipeX()
                && a.getBirdY() < b.getPipeY() + b.getPipeHeight()
                && a.getBirdY() + a.getBirdHeight() > b.getPipeY();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        } else {
            placePipeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if(!gameOver)
                    velocityY = -9;
                break;
            case KeyEvent.VK_ENTER:
                if(gameOver)
                    resetGame();
            default:
        }
    }

    private void resetGame() {
        velocityY = 0;
        bird.setBirdY(BOARD_HEIGHT / 2);
        topPipes.clear();
        bottomPipes.clear();
        gameOver = false;
        gameScore.setScore(0);
        gameLoop.start();
        placePipeTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
