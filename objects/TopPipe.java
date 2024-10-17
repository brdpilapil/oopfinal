package objects;

import java.awt.Graphics;
import java.awt.Image;

public class TopPipe extends Pipe {
    
    private int speed;
    private double score;
    public TopPipe(int x, int y, int width, int height, Image img, double score) {
        super(x, y, width, height, img);
        this.score = score;
        this.speed=(int) ((score/5) * 2);
    }

    public void moveVertically() {
        setPipeY(getPipeY() + speed * direction);

        if (getPipeY() + getPipeHeight() <= 128 || getPipeY() + getPipeHeight() >= 512 - 160) {
            speed = -speed;
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(getImg(), getPipeX(), getPipeY(), getPipeWidth(), getPipeHeight(), null);
    }

    public int getPipeY() {
        return pipeY;
    }

    public void setPipeY(int pipeY) {
        this.pipeY = pipeY;
    }

    public int getPipeHeight() {
        return pipeHeight;
    }

    public void setPipeHeight(int pipeHeight) {
        this.pipeHeight = pipeHeight;
    }
    
}
