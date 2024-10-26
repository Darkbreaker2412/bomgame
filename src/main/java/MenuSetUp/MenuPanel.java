package MenuSetUp;

import javax.swing.JPanel;


import main.Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class MenuPanel extends JPanel {
    MyButton start;
    MyButton quit;
    Sound music;

    public MenuPanel(int width, int height) {
        setLayout(null);
        music = new Sound("Music");
        music.play();

        start = new MyButton("start");
        quit = new MyButton("quit");

        //thiet lap vi tri
        start.setLocateButton(width / 2 - 71, (DimensionSize.maxScreenCol - 4) * DimensionSize.tileSize);
        quit.setLocateButton(width / 2 - 71, (DimensionSize.maxScreenCol - 2) * DimensionSize.tileSize);

        add(start);
        add(quit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage background, logo;
        try {
            File Logo = new File(Main.res + "/background/logo1.png");
            File Background = new File(Main.res + "/background/background.png");
            logo = ImageIO.read(Logo);
            background = ImageIO.read(Background);
            g.drawImage(background, 0, 0, DimensionSize.screenWidth, DimensionSize.screenHeight, null);
            g.drawImage(logo, 0, 0, DimensionSize.screenWidth, DimensionSize.screenHeight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
