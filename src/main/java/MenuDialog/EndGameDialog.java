package MenuDialog;

import java.awt.Font;
import javax.swing.*;

import MenuSetUp.ChangePanel;
import MenuSetUp.LevelGameFrame;
import MenuSetUp.MyButton;
import res.LoadResource;

public class EndGameDialog extends SuperDialog {
    public MyButton next, restart, quit;

    public EndGameDialog(String title, LevelGameFrame parent, int score, String time, ChangePanel change) {
        super(parent);
        setTitle(title);
        parent.gamePanel.setGameThread(null);

        next = new MyButton("next");
        next.addActionListener(e -> {
            LevelGameFrame nextLevel = new LevelGameFrame(parent.lv + 1, parent.levelPanel);
            parent.setVisible(false);
            nextLevel.setVisible(true);
        });

        restart = new MyButton("restart");
        restart.addActionListener(e -> {
            setVisible(false);
            parent.setVisible(false);
            LevelGameFrame newParent = new LevelGameFrame(parent.lv, parent.levelPanel);
            newParent.setVisible(true);
        });

        quit = new MyButton("level");
        quit.addActionListener(e -> {
            parent.setVisible(false);
            change.frame.setVisible(true);
        });
        if (title.equals("YOU WIN"))
            addButton(restart, quit, next);
        else addButton(restart, quit);

        String b = String.format("%2d", score);

        getContentPane().add(setLabel("YOUR SCORE", b, 150));
        getContentPane().add(setLabel("TIME", time, 220));

        setBackground();
    }

    JPanel setLabel(String name, String value, int y) {
        //Panel
        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.setLayout(null);
        labelPanel.setSize(500, 520);
        labelPanel.setLocation(10, 0);

        Font font = LoadResource.Courier_New_Bold_20;
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(font);
        nameLabel.setSize(150, 30);
        nameLabel.setLocation((500 - 350) / 2 + 10, y);
        labelPanel.add(nameLabel);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(font);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setSize(200, 30);
        valueLabel.setLocation((500 - 350) / 2 + 150, y);
        labelPanel.add(valueLabel);

        return labelPanel;
    }
}
