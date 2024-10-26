package res;

import MenuSetUp.Sound;
import main.Main;
import main.UtilityTool;
import tile.Tile;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import static MenuSetUp.DimensionSize.tileSize;

public class LoadResource {
    public static HashMap<String, BufferedImage> itemImgMap = new HashMap<>();
    public static HashMap<String, Integer> itemPointMap = new HashMap<>();
    public static HashMap<String, BufferedImage> monsterImgMap = new HashMap<>();

    // sound
    public static Sound explosionSound = new Sound("small_explosion");

    // font
    public static Font Courier_New_Bold_20 = new Font("Courier New", Font.BOLD, 20);
    public static Font Courier_New_Bold_30 = new Font("Courier New", Font.BOLD, 30);
    public static Font Courier_New_Bold_38 = new Font("Courier New", Font.BOLD, 38);
    public static Font Courier_New_Italic_16 = new Font("Courier New", Font.ITALIC, 16);

    // image icon
    public static ImageIcon musicOnBtnIcon = new ImageIcon(Main.res + "/button/musicButton.png");
    public static ImageIcon musicOffBtnIcon = new ImageIcon(Main.res + "/button/musicOffButton.png");
    public static ImageIcon soundOnBtnIcon = new ImageIcon(Main.res + "/button/soundButton.png");
    public static ImageIcon soundOffBtnIcon = new ImageIcon(Main.res + "/button/soundOffButton.png");
    public static ImageIcon dialogBackground = new ImageIcon(Main.res + "/background/dialogBackground.png");

    // tiles
    public static Tile[] tiles = new Tile[10];

    // player
    public static BufferedImage[] playerUp, playerDown, playerLeft, playerRight;

    // flame
    public static BufferedImage[] headUp, headDown, headLeft, headRight;
    public static BufferedImage[] verticalBody, horizontalBody;

    // bomb
    public static BufferedImage[] idle, explosion;
    //monster
    //public static BufferedImage[] monsterUp, monsterDown, monsterLeft, monsterRight;
    //public static BufferedImage monsterImage;
    static {

        loadTile();
        loadPlayer();
        loadBomb();
        loadFlame();
        loadItem();
        //loadMonster();
    }

    static void loadTile() {

    }

    static void loadItem() {

        File file = new File(Main.res + "\\item\\itemList.txt");
        try (Scanner reader = new Scanner(file)) {
            String[] nameAndPoint;
            String itemName;
            int itemPoint;
            while (reader.hasNextLine()) {
                nameAndPoint = reader.nextLine().split(" ");
                itemName = nameAndPoint[0];
                itemPoint = Integer.parseInt(nameAndPoint[1]);
                itemImgMap.put(itemName, itemImage(itemName));
                itemPointMap.put(itemName, itemPoint);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage itemImage(String name) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Main.res + "\\item\\" + name + ".png"));
            img = UtilityTool.scaleImage(img, tileSize, tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    static void loadFlame() {
        headUp = UtilityTool.loadSpriteSheet("\\flame\\headUpFlame_48_x_16_.png");
        headDown = UtilityTool.loadSpriteSheet("\\flame\\headDownFlame_48_x_16_.png");
        headLeft = UtilityTool.loadSpriteSheet("\\flame\\headLeftFlame_48_x_16_.png");
        headRight = UtilityTool.loadSpriteSheet("\\flame\\headRightFlame_48_x_16_.png");
        verticalBody = UtilityTool.loadSpriteSheet("\\flame\\verticalBodyFlame_48_x_16_.png");
        horizontalBody = UtilityTool.loadSpriteSheet("\\flame\\horizontalBodyFlame_48_x_16_.png");
    }

    static void loadBomb() {
        idle = UtilityTool.loadSpriteSheet("\\bomb\\normal\\bomb_64_x_16_.png");
        explosion = UtilityTool.loadSpriteSheet("\\bomb\\normal\\explosion_128_x_16_.png");
    }

    static void loadPlayer() {
        playerUp = UtilityTool.loadSpriteSheet("\\player\\up_32_x_16_.png");
        playerDown = UtilityTool.loadSpriteSheet("\\player\\down_32_x_16_.png");
        playerLeft = UtilityTool.loadSpriteSheet("\\player\\left_32_x_16_.png");
        playerRight = UtilityTool.loadSpriteSheet("\\player\\right_32_x_16_.png");
    }
    // public static  void loadMonster(){
        
    // //     // //monsterUp = UtilityTool.loadSpriteSheet("\\monster\\quaivat 2_up.png");
    // //     // monsterDown = UtilityTool.loadSpriteSheet("\\monster\\quaivat 2_down.png");
    // //     // monsterLeft = UtilityTool.loadSpriteSheet("\\monster\\quaivat 2_left.png");
    // //     // monsterRight = UtilityTool.loadSpriteSheet("\\monster\\quaivat 2_right.png");
    // }
    
}
