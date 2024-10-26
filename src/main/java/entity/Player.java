package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bomb.NormalBomb;
import main.GamePanel;
import main.KeyHandler;
import res.LoadResource;

import static MenuSetUp.DimensionSize.tileSize;

public class Player extends Entity {

    KeyHandler keyH;

    BufferedImage[] playerUp, playerDown, playerLeft, playerRight;
    // idle, death will be up direction
    // bombing, riding animal, kicking bomb, picking up bomb will depend on the
    // direction

    public ArrayList<NormalBomb> bombs = new ArrayList<>();
    private int maxBombs = 1;
    private int flameLength = 3;
    double cooldownInSecond;
    double cooldownInFrame;
    int cooldown;
    int timer = 0;

    public int score = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        this.name = "player";

        cooldownInSecond = 0.1;
        cooldownInFrame = cooldownInSecond * gp.FPS;
        cooldown = (int) cooldownInFrame;

        solidArea = new Rectangle(12, 20, 24, 24);

        setDefaultValues();
        getPlayerImage();
    }

    void setDefaultValues() {

        x = tileSize * 2;
        y = tileSize * 10;
        speed = 4;
        direction = "down";
        spriteTime = 6;
    }

    void getPlayerImage() {

        playerUp = LoadResource.playerUp;
        playerDown = LoadResource.playerDown;
        playerLeft = LoadResource.playerLeft;
        playerRight = LoadResource.playerRight;
        sprites = playerUp;
    }

    public void update() {

        // placing bomb
        gp.bombs.addAll(bombs);
        if (keyH.enterPressed && bombs.size() < maxBombs
                && timer == 0 && gp.cChecker.canPlaceBomb(this)) {
            placingBomb();
            timer = cooldown;
        }

        bombs.forEach(NormalBomb::update);
        bombs.removeIf(bomb -> bomb.exploded);

        if (timer > 0) {
            timer--;
        }

        if (keyH.movePressed) {

            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK COLLISION
            gp.cChecker.checkTile(this);
            gp.cChecker.checkBombForMoving(this);

            move();
        }

        if (++spriteCounter > spriteTime) {

            if (keyH.movePressed) spriteNum++;
            if (spriteNum == sprites.length) {

                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        switch (direction) {
            case "up":
                sprites = playerUp;
                break;
            case "down":
                sprites = playerDown;
                break;
            case "left":
                sprites = playerLeft;
                break;
            case "right":
                sprites = playerRight;
                break;
        }

        g2.drawImage(sprites[spriteNum], x, y, null);

        bombs.forEach(bomb -> bomb.draw(g2));
    }

    public void move() {

        switch (direction) {
            case "up":
                if (canMoveUp) y -= speed;
                break;
            case "down":
                if (canMoveDown) y += speed;
                break;
            case "left":
                if (canMoveLeft) x -= speed;
                break;
            case "right":
                if (canMoveRight) x += speed;
                break;
        }
        resetCollision(true);
    }

    @Override
    public void beingHit() {
        super.beingHit();
        isDead = true;
    }

    public void addMoreBombs(int n) {
        maxBombs += n;
    }

    public void addMoreFlame(int n) {
        flameLength += n;
    }

    public void addScore(int point) {
        score += point;
    }

    public void placingBomb() {
        bombs.add(new NormalBomb(gp, col() * tileSize, row() * tileSize, this, flameLength));
    }
}