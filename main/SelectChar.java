package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import main.GameUI;

public class SelectChar extends JPanel {
    private final Image DION, JUL, KARL, BENZ, KAURI, KYLE, RED, VINCE;
    private final JPanel[] characterPanels = new JPanel[8];
    private final String[] characterNames = {"Dion", "Jul", "Karl", "Benz", "Kauri", "Kyle", "Red", "Vince"};
    private Image selectedImage;
    
    public interface CharacterSelectListener {
        void onCharacterSelected(Image characterImage);
    }

    private CharacterSelectListener listener;

    public SelectChar(CharacterSelectListener listener) {
        this.listener = listener;
        setPreferredSize(new Dimension(360, 640)); // Fixed size
        setLayout(new BorderLayout());

        // Create a title label at the top
        JLabel titleLabel = new JLabel("Select Character", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for character grid
        JPanel gridPanel = new JPanel(new GridLayout(4, 2, 20, 20)); // 4 rows, 2 columns, with spacing
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Padding around grid

        // Load and resize images
        DION = resizeImage(GameUI.loadImg(GameUI.DION), 80, 80);
        JUL = resizeImage(GameUI.loadImg(GameUI.JUL), 80, 80);
        KARL = resizeImage(GameUI.loadImg(GameUI.KARL), 80, 80);
        BENZ = resizeImage(GameUI.loadImg(GameUI.BENZ), 80, 80);
        KAURI = resizeImage(GameUI.loadImg(GameUI.KAURI), 80, 80);
        KYLE = resizeImage(GameUI.loadImg(GameUI.KYLE), 80, 80);
        RED = resizeImage(GameUI.loadImg(GameUI.RED), 80, 80);
        VINCE = resizeImage(GameUI.loadImg(GameUI.VINCE), 80, 80);

        Image[] characterImages = {DION, JUL, KARL, BENZ, KAURI, KYLE, RED, VINCE};

        // Initialize each character panel with resized images and names
        for (int i = 0; i < characterPanels.length; i++) {
            characterPanels[i] = new JPanel(new BorderLayout());
            characterPanels[i].setBackground(Color.LIGHT_GRAY);
            characterPanels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            JLabel imageLabel = new JLabel(new ImageIcon(characterImages[i]));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel nameLabel = new JLabel(characterNames[i], JLabel.CENTER);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            characterPanels[i].add(imageLabel, BorderLayout.CENTER);
            characterPanels[i].add(nameLabel, BorderLayout.SOUTH);

            int index = i;
            characterPanels[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    selectedImage = characterImages[index];
                    if (listener != null) {
                        listener.onCharacterSelected(selectedImage);
                        
                    }
                }
            });

            gridPanel.add(characterPanels[i]);
        }

        // Add the grid panel to the center
        add(gridPanel, BorderLayout.CENTER);
    }

    private Image resizeImage(Image originalImage, int width, int height) {
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    
    public Image getSelectedImage() {
        return selectedImage;
    }
}
