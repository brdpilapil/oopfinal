package main;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.net.URL;
import javax.swing.ImageIcon;

public class GameUI {

    public static final String BACKGROUND = "flappybirdbg.png";
    //public static final String BIRD = "flappybird.png";
    public static final String TOP_PIPE = "toppipe.png";
    public static final String BOTTOM_PIPE = "bottompipe.png";
    public static final String DION = "dion_bird.png";
    public static final String JUL = "ping_bird.png";
    public static final String KARL = "karl_bird.png";
    public static final String BENZ = "gwapo.png";
    public static final String KAURI = "kauri_bird.png";
    public static final String KYLE = "kyle_bird.png";
    public static final String RED = "red_bird.png";
    public static final String VINCE = "vince_bird.png";
    

    public static Image loadImg(String filename) {


        Image img = null;
        try {
            URL resourceUrl = GameUI.class.getResource("/resources/" + filename);
            if (resourceUrl == null) {
                throw new FileNotFoundException("Resource not found: " + filename);
            }
            img = new ImageIcon(resourceUrl).getImage();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Image file not found. ");
        } catch (Exception e) {
            System.err.println("Error: Unable to load image due to unexpected error. ");
        } finally {
            System.out.println("Resource loading attempt finished for file: " + filename);
        }
        return img;

    }
}
