package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main {
    public static final int BOARD_WIDTH = 360;
    public static final int BOARD_HEIGHT = 640;
    public static void main(String[] args) {
        
        

        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create character selection screen with listener
        SelectChar select = new SelectChar((selectedImage) -> {
            // Remove SelectChar panel and switch to GamePanel with selected image
            frame.getContentPane().removeAll();
            GamePanel gamePanel = null;
            try {
                gamePanel = new GamePanel(selectedImage);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.requestFocus();
        });
        frame.add(select);
        frame.setVisible(true);
        
    }


}
