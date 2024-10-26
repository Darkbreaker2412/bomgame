package tile;

import entity.Item;
import main.GamePanel;
import main.Main;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static MenuSetUp.DimensionSize.*;

public class Map {

    GamePanel gp;
    String mapFileName;
    public int[][] mapTileNum;
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Point> itemPos = new ArrayList<>();
//    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Point> monsterSpawnPos = new ArrayList<>();

    public Point spawnPlayer;
    public Point checkPoint;
    String baseTile;
    int baseIndex;
//    Monster[] monsters;

    public Map(GamePanel gp, String mapFileName) {

        this.gp = gp;
        this.mapFileName = mapFileName;
        mapTileNum = new int[maxScreenCol][maxScreenRow];

        loadMap(Main.res + "/maps/" + mapFileName + ".txt");
        placeItem();
    }

    public void placeItem() {

        Random rand = new Random();
        Point p;
        int pIndex;
        for (Item item : items) {
            pIndex = rand.nextInt(itemPos.size());
            p = itemPos.get(pIndex);
            item.x = p.x * tileSize;
            item.y = p.y * tileSize;
            item.solidArea = new Rectangle(item.x, item.y, tileSize, tileSize);
            itemPos.remove(pIndex);
            System.out.println(p.y + "," + p.x);
        }
    }

    public void toBaseTile(int x, int y) {

        gp.player.score += gp.tileManager.tile[mapTileNum[x][y]].point;

        mapTileNum[x][y] = baseIndex;

        items.forEach(item -> {
            if (item.col() == x && item.row() == y) {
                item.state = Item.States.shown;
            }
        });
    }

    public int findBaseIndex() {
        for (int i = 0; i < gp.tileManager.tile.length; i++) {
            if (gp.tileManager.tile[i].name.equals(baseTile)) return i;
        }
        return 0;
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            baseTile = br.readLine();
            baseIndex = findBaseIndex();
            String[] itemList = br.readLine().split(";");
            String[] tmp;
            int quantity;
            for (String item : itemList) {
                tmp = item.split(" ");
                quantity = Integer.parseInt(tmp[0]);
                while (quantity-- > 0)
                    items.add(new Item(gp, tmp[1]));
            }

            while (col < maxScreenCol && row < maxScreenRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                while (col < maxScreenCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    if (gp.tileManager.tile[num].destructible) {
                        itemPos.add(new Point(col, row));
                    }
                    col++;
                }

                if (col == maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            is.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < maxScreenCol && row < maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(gp.tileManager.tile[tileNum].image, x, y, null);
            col++;
            x += tileSize;

            if (col == maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += tileSize;
            }
        }

        items.forEach(item -> {
            if (item.state == Item.States.shown) {
                item.draw(g2);
            }
        });
    }

}
