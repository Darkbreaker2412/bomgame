package bomb;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.CollisionChecker;
import main.GamePanel;
import res.LoadResource;
import tile.Tile;

import static MenuSetUp.DimensionSize.tileSize;

public class Flame {

    Bomb creator;

    GamePanel gp;
    int x, y;

    BufferedImage[] headUp, headDown, headLeft, headRight;
    BufferedImage[] verticalBody, horizontalBody;
    public int spriteTime = 3;// time between 2 sprites
    public int spriteNum = 0;// index of the using sprite
    public int spriteCounter = 0;// should be frame counter
    public int duration;

    public Rectangle verticalSolidArea, horizontalSolidArea;
    boolean solidAreaIsCreated = false;
    public int length;

    public Flame(Bomb creator, int x, int y, int length, int duration) {

        this.creator = creator;
        this.gp = creator.gp;
        this.x = x;
        this.y = y;
        this.length = length;
        this.duration = duration;

        verticalSolidArea = new Rectangle(x, y, tileSize, tileSize);
        horizontalSolidArea = new Rectangle(x, y, tileSize, tileSize);
        getFlameImage();
    }

    void getFlameImage() {
        headUp = LoadResource.headUp;
        headDown = LoadResource.headDown;
        headLeft = LoadResource.headLeft;
        headRight = LoadResource.headRight;
        verticalBody = LoadResource.verticalBody;
        horizontalBody = LoadResource.horizontalBody;
    }

    public void update() {

        CollisionChecker.checkEntityForFlame(this, creator.owner);
        gp.map.items.forEach(item -> CollisionChecker.checkItemForFlame(this, item));

        duration--;
        if (++spriteCounter > spriteTime) {

            if (++spriteNum == headUp.length) {

                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        drawUp(g2);
        drawDown(g2);
        drawLeft(g2);
        drawRight(g2);
        solidAreaIsCreated = true;
    }

    void drawUp(Graphics2D g2) {

        Tile tile;
        int fx, fy, offset = 0;
        for (int i = 1; i <= length; i++) {
            offset = i * tileSize;
            fx = x;
            fy = y - offset;
            tile = gp.tileManager.tile[gp.map.mapTileNum[fx / tileSize][fy / tileSize]];
            if (tile.collision && !tile.destructible) {
                offset -= tileSize;
                break;
            }
            if (i == length) {
                // head
                g2.drawImage(headUp[spriteNum], fx, fy, null);
            } else {
                // body
                g2.drawImage(verticalBody[spriteNum], fx, fy, null);
            }

            //a function to change the object tile to the base tile
            if (tile.destructible) {
                if (duration == 1) {
                    gp.map.toBaseTile(fx / tileSize, fy / tileSize);
                }
                break;
            }
        }

        if (!solidAreaIsCreated) {
            verticalSolidArea.y -= offset;
            verticalSolidArea.height += offset;
        }
    }

    void drawDown(Graphics2D g2) {

        Tile tile;
        int fx, fy, offset = 0;
        for (int i = 1; i <= length; i++) {
            offset = i * tileSize;
            fx = x;
            fy = y + offset;
            tile = gp.tileManager.tile[gp.map.mapTileNum[fx / tileSize][fy / tileSize]];
            if (tile.collision && !tile.destructible) {
                offset -= tileSize;
                break;
            }
            if (i == length) {
                // head
                g2.drawImage(headDown[spriteNum], fx, fy, null);
            } else {
                // body
                g2.drawImage(verticalBody[spriteNum], fx, fy, null);
            }

            //a function to change the object tile to the base tile
            if (tile.destructible) {
                if (duration == 1) {
                    gp.map.toBaseTile(fx / tileSize, fy / tileSize);
                }
                break;
            }
        }

        if (!solidAreaIsCreated) {
            verticalSolidArea.height += offset;
        }
    }

    void drawLeft(Graphics2D g2) {

        Tile tile;
        int fx, fy, offset = 0;
        for (int i = 1; i <= length; i++) {
            offset = i * tileSize;
            fx = x - offset;
            fy = y;
            tile = gp.tileManager.tile[gp.map.mapTileNum[fx / tileSize][fy / tileSize]];
            if (tile.collision && !tile.destructible) {
                offset -= tileSize;
                break;
            }
            if (i == length) {
                // head
                g2.drawImage(headLeft[spriteNum], fx, fy, null);
            } else {
                // body
                g2.drawImage(horizontalBody[spriteNum], fx, fy, null);
            }

            //a function to change the destructible tile to the base tile -> create a Map class
            if (tile.destructible) {
                if (duration == 1) {
                    gp.map.toBaseTile(fx / tileSize, fy / tileSize);
                }
                break;
            }
        }

        if (!solidAreaIsCreated) {
            horizontalSolidArea.x -= offset;
            horizontalSolidArea.width += offset;
        }
    }

    void drawRight(Graphics2D g2) {

        Tile tile;
        int fx, fy, offset = 0;
        for (int i = 1; i <= length; i++) {
            offset = i * tileSize;
            fx = x + offset;
            fy = y;
            tile = gp.tileManager.tile[gp.map.mapTileNum[fx / tileSize][fy / tileSize]];
            if (tile.collision && !tile.destructible) {
                offset -= tileSize;
                break;
            }
            if (i == length) {
                // head
                g2.drawImage(headRight[spriteNum], fx, fy, null);
            } else {
                // body
                g2.drawImage(horizontalBody[spriteNum], fx, fy, null);
            }

            //a function to change the object tile to the base tile
            if (tile.destructible) {
                if (duration == 1) {
                    gp.map.toBaseTile(fx / tileSize, fy / tileSize);
                }
                break;
            }
        }

        if (!solidAreaIsCreated) {
            horizontalSolidArea.width += offset;
        }
    }
}
