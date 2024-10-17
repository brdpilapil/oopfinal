package objects;

import java.awt.Graphics;
import java.awt.Image;

public class BottomPipe extends Pipe {

    private int speed;
    private double score;
    public BottomPipe(int x, int y, int width, int height, Image img, double score) {
        super(x, y, width, height, img);
        this.score = score;
        this.speed=(int) ((score/5) * 2);
    }  

    public void moveVertically() {
        setPipeY(getPipeY() + speed * direction);

        if (getPipeY() <= 128 + 160 || getPipeY() >= 512) {
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

}
