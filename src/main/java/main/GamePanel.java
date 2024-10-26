package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;

import javax.swing.JPanel;

import MenuDialog.EndGameDialog;
import MenuDialog.PauseDialog;
import MenuSetUp.DimensionSize;
import MenuSetUp.LevelGameFrame;
import MenuSetUp.LevelPanel;
import MenuSetUp.MyButton;

import bomb.Bomb;
import entity.Item;
import entity.Monster;
import entity.Player;
import res.LoadResource;
import tile.Map;
import tile.TileManager;

import static MenuSetUp.DimensionSize.screenHeight;
import static MenuSetUp.DimensionSize.screenWidth;

public class GamePanel extends JPanel implements Runnable {

    public boolean isPausing = false;

    // FPS
    public int FPS = 60;
    public Clock clock = new Clock(this);

    public LevelGameFrame parent;
    public MyButton button;
    public LevelPanel levelPanel;

    public TileManager tileManager = new TileManager(this);
    public Map map;
    KeyHandler keyH = new KeyHandler();
    private Thread gameThread;
    public Player player = new Player(this, keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public ArrayList<Bomb> bombs = new ArrayList<>();// all bombs in the map
    //public ArrayList<Monster> monsters = new ArrayList<>();
    public Monster monster;
    public GamePanel(String mapFileName) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);// this can be focused to receive key input

        this.map = new Map(this, mapFileName);
        this.setLayout(null);
        addButton();
        //initializeMonsters();
        monster = new Monster(this, 5, 5);
    }

    public GamePanel(String mapFileName, LevelGameFrame parent, LevelPanel level) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);// this can be focused to receive key input
        this.map = new Map(this, mapFileName);
        this.parent = parent;
        this.levelPanel = level;
        this.setLayout(null);
        addButton();
        monster = new Monster(this, 5, 5);
    }

    public void addButton() {
        button = new MyButton("pause");
        button.setLocateButton(screenHeight - 60, 10);
        this.add(button);
        button.addActionListener(e -> endGame("uncompleted"));
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void endGame(String result) {
        // result: win, lose, uncompleted
        switch (result) {
            case "win":
                // UNLOCK NEW LEVEL
                if (levelPanel.user.getLevel() < 3) {
                    levelPanel.user.setLevel(parent.lv + 1);
                    levelPanel.resetLevelPanel(levelPanel.user);
                }
                // READ SCORE
                levelPanel.user.setScore(parent.lv, player.score);
                EndGameDialog winDialog = new EndGameDialog("YOU WIN", parent, player.score, clock.toString(), levelPanel.change);
                winDialog.setVisible(true);
                break;

            case "lose":
                EndGameDialog loseDialog = new EndGameDialog("YOU LOSE", parent, player.score, clock.toString(), levelPanel.change);
                loseDialog.setVisible(true);
                break;

            case "uncompleted":
                isPausing = true;
                PauseDialog pauseDialog = new PauseDialog(parent);
                pauseDialog.setVisible(true);
                break;
        }
    }

    @Override
    public void run() {

        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            if (isPausing) continue;

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();// call paintComponent
                delta--;
                drawCount++;
            }

            if (timer >= 1e9) {
                // System.out.println("FPS: " + drawCount);
                clock.update();
//                System.out.println("SCORE: " + player.score);
//                System.out.println(clock.toString());
                drawCount = 0;
                timer = 0;
            }
            /**
             * if isWin:
             *      endGame("win");
             */

            if (player.isDead) {
                endGame("lose");
            }
        }
    }

    public void update() {

        bombs.clear();
        player.update();
        // for (Monster monster : monsters) {
        //     monster.update();
        // }
        monster.update(); 
        map.items.forEach(Item::update);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // DRAW ORDER
        // background outside the tile map
        // tileMap
        // player and monster
        // bomb and item
        // ui

        map.draw(g2);

        player.draw(g2);
        // for (Monster monster : monsters) {
        //     monster.draw(g2); 
        // }
        monster.draw(g2);
        drawUI(g2, new MyButton("pause"), screenHeight - 60, 20);

        g2.dispose();// save memories
    }

    void drawUI(Graphics2D g2, MyButton button, int x, int y) {
        //draw button
        g2.drawImage(button.getIcon().getImage(), x, 0, 50, 50, null);

        //draw score
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 20));
        g2.drawString("SCORE " + player.score, 10, y);

        //draw clock
        g2.drawString(clock.toString(), (DimensionSize.screenWidth - clock.toString().length()) / 2, y);
    }

    public static class Clock {

        GamePanel gp;

        public int hour = 0;
        public int minute = 0;
        public int second = 0;

        Clock(GamePanel gp) {
            this.gp = gp;
        }

        public void update() {
            second++;
            if (second == 60) {
                minute++;
                second = 0;
                if (minute == 60) {
                    hour++;
                    minute = 0;
                }
            }
        }

        @Override
        public String toString() {
            String out = String.format("%2d:%2d", minute, second).replace(' ', '0');
            if (hour > 0) out = String.format("%2d:", hour).replace(' ', '0') + out;
            return out;
        }
    }

    public void setParentFrame(LevelGameFrame newParent) {
        parent = newParent;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread thread) {
        gameThread = thread;
    }
    // public void initializeMonsters() {
    //     monsters.add(new Monster(this, 1, 1));

	
       
        
    // }
}

