package tile;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.Main;
import main.UtilityTool;

import static MenuSetUp.DimensionSize.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {

        setup(0, "grass", false, false);
        setup(1, "water", true, false);
        setup(2, "wood_box", true, true, 20);
    }

    public void setup(int index, String imageName, boolean collision, boolean destructible, int point) {
        setup(index, imageName, collision, destructible);
        tile[index].point = point;
    }

    public void setup(int index, String imageName, boolean collision, boolean destructible) {

        try {
            tile[index] = new Tile();
            tile[index].name = imageName;
            tile[index].image = ImageIO.read(new File(Main.res + "\\tiles\\" + imageName + ".png"));
            tile[index].image = UtilityTool.scaleImage(tile[index].image, tileSize, tileSize);
            tile[index].collision = collision;
            tile[index].destructible = destructible;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}