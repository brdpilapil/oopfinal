package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GameScore {

    public static double score = 0;
    private double highscore = 0;
    private PrintWriter print;
    public GameScore() throws IOException {
        print = new PrintWriter(new FileWriter("Leaderboards.txt"));
    }

    public void draw(Graphics g, boolean gameOver) {
       
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Serif", Font.ROMAN_BASELINE, 32));
        if (gameOver) {
            g.drawString("Game Over: " + (int) getScore(), 80, 250);
            g.drawString(String.valueOf("Highscore: " + (int) getHighscore()),
                    90, 290);
            g.setFont(new Font("Serif", Font.ROMAN_BASELINE, 24));
            g.setColor(Color.GREEN);
            g.drawString("Press ENTER to play again", 40, 340);
            print.println("Score: " + (int) getScore());
            print.println(String.valueOf("Highscore: " + (int) getHighscore()));
            print.flush();
        } else {
            g.drawString(String.valueOf((int) getScore()), 10, 35);
            g.drawString(String.valueOf("Highscore: " + (int) getHighscore()),
                    50, 35);  
        }
    }

    public void newHighscore(double score) {
        if (score > highscore) {
            setHighscore(score);
        }
    }
    


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getHighscore() {
        return highscore;
    }

    public void setHighscore(double highscore) {
        this.highscore = highscore;
    }

}
