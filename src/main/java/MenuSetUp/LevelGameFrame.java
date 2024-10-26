package MenuSetUp;

import javax.swing.JFrame;

import main.GamePanel;

public class LevelGameFrame extends JFrame {
    public GamePanel gamePanel;
    public int lv;
    public LevelPanel levelPanel;

    public LevelGameFrame(int lv, LevelPanel levelPanel) {
        new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("BOMB GAME");

        this.lv = lv;
        this.levelPanel = levelPanel;
        gamePanel = new GamePanel(mapFileNameToString(lv), this, levelPanel);
        add(gamePanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(false);

        gamePanel.startGameThread();
    }

    public LevelGameFrame(int lv, LevelPanel levelPanel, GamePanel oldGamePanel) {
        new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("BOMB GAME");

        this.lv = lv;
        this.levelPanel = levelPanel;
        oldGamePanel.setParentFrame(this);
        this.gamePanel = oldGamePanel;
        add(this.gamePanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(false);
    }

    public String mapFileNameToString(int lv) {
        return "level_" + lv;
    }
}
