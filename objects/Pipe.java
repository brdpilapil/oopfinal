package objects;

import java.awt.Image;


public abstract class Pipe {

    protected int pipeX, pipeY, pipeWidth, pipeHeight;
    protected float YVelocity = 0;  // Vertical velocity
    protected boolean passed = false;
    protected int direction;
    protected Image img;
    public Pipe(int x, int y, int width, int height, Image img) {
        this.pipeX = x;
        this.pipeY = y;
        this.pipeWidth = width;
        this.pipeHeight = height;
        this.img = img;
    }

    public void moveHorizontally(int velocityX) {
        this.pipeX += velocityX; // Horizontal movement
    }

    public abstract void moveVertically();
    
    
    public void setDirection(int dir) {
        this.direction = dir;
    }
    
    public int getDirection() {
        return direction;
    }

    public int getPipeX() {
        return pipeX;
    }

    public int getPipeY() {
        return pipeY;
    }

    public int getPipeWidth() {
        return pipeWidth;
    }

    public int getPipeHeight() {
        return pipeHeight;
    }

    public float getPipeVelocity() {
        return YVelocity;
    }

    public void setPipeVelocity(float YVelocity) {
        this.YVelocity = YVelocity;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Image getImg() {
        return img;
    }
}
