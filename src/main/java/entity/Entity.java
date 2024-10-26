package entity;

import main.GamePanel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static MenuSetUp.DimensionSize.tileSize;

public class Entity {

    GamePanel gp;
    public String name;
    public int x, y;
    public int speed;

    public BufferedImage[] sprites;
    public String direction;// direction of sprites
    public int spriteTime;// time between 2 sprites
    public int spriteNum = 0;// index of the using sprite
    public int spriteCounter = 0;// should be frame counter

    public Rectangle solidArea;
    public boolean collisionOn = true;
    public boolean canMoveUp = true, canMoveDown = true, canMoveLeft = true, canMoveRight = true;
    public boolean isHit = false;
    public boolean isDead = false;
    public void resetCollision(boolean state) {
        canMoveUp = state;
        canMoveDown = state;
        canMoveLeft = state;
        canMoveRight = state;
    }

    public int col() {

        return (x + tileSize / 2) / tileSize;
    }

    public int row() {

        return (y + tileSize / 2) / tileSize;
    }

    public void beingHit() {
        isHit = true;
        System.out.println("hit " + name);
    }
}