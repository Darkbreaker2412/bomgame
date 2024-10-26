package MenuSetUp;


import javax.swing.JLabel;
import javax.swing.JPanel;

import MenuDialog.RemindDialog;
import main.Main;
import res.LoadResource;
import userClass.User;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class LevelPanel extends JPanel {
    MyButton back;
    MyButton setting;
    public User user;
    public MyButton[] level = new MyButton[3];
    public ChangePanel change;

    public LevelPanel(User currentUser, ChangePanel change) {
        setLayout(null);
        this.change = change;

        if (currentUser != null) {
            user = currentUser;
        } else user = new User();
        // SET UP LEVEL BUTTON
        addLevelButton(user);
        actionLevelButton();

        //back button
        back = new MyButton("back");
        back.setLocateButton(10, 10);
        add(back);
        addActionBackButton();

        // SET LABEL
        JLabel lv = new JLabel("LEVEL");
        lv.setSize(200, 100);
        lv.setFont(LoadResource.Courier_New_Bold_38);
        lv.setLocation((DimensionSize.screenWidth - 130) / 2, 20);
        add(lv);

        //setting button
        setting = new MyButton("setting");
        setting.setLocateButton(DimensionSize.screenWidth - 60, 10);
        add(setting);
        setting.addActionListener((event) -> {
            SettingPanel setting = new SettingPanel(change.menu.music);
            change.contentPane.add(setting, "settingInLevel");
            change.cardLayout.show(change.contentPane, "settingInLevel");
            setting.back.addActionListener((e) -> {
                change.cardLayout.previous(change.contentPane);
                change.menu.music.saveSetting(change.menu.music);
            });
        });
    }

    public void setUser(User a) {

    }

    public void addLevelButton(User a) {
        for (int i = 0; i < 3; i++) {
            String x = "level" + (i + 1);
            if (i + 1 <= a.getLevel()) level[i] = new MyButton(x);

            else level[i] = new MyButton("levelBlock"); //nếu levelButton bé hơn level -> block button;
            level[i].setLocateButton(((DimensionSize.maxScreenCol - 5) / 2 + i * 2) * DimensionSize.tileSize
                    , DimensionSize.screenHeight / 2);
            add(level[i]);

        }
    }

    public void actionLevelButton() {

        for (int i = 0; i < user.getLevel(); i++) {
            int a = i + 1;
            if (i < user.getLevel()) {
                level[i].addActionListener(e -> {
                    LevelGameFrame lv1 = new LevelGameFrame(a, this);
                    lv1.setVisible(true);
                    change.frame.setVisible(false);
                });
            }
        }
    }

    public void resetLevelPanel(User a) {

        for (int i = 0; i < 3; i++) {
            remove(level[i]); //delete old button
        }
        //add new button
        addLevelButton(a);

        //add action for new button
        actionLevelButton();

    }

    void addActionBackButton() {
        back.addActionListener((event) -> {
            RemindDialog saveGame = new RemindDialog(change.frame, "save game ");
            saveGame.setVisible(true);

            //saveGame ok button
            saveGame.okButton.addActionListener(e -> {
                change.userList.addUser(user);
                change.userList.saveGame();
                change.cardLayout.show(change.contentPane, "start");
                saveGame.setVisible(false);
            });
            //saveGame no button
            saveGame.noButton.addActionListener(e -> {
                change.cardLayout.show(change.contentPane, "start");
                saveGame.setVisible(false);
            });
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage background;
        try {
            File a = new File(Main.res + "/background/background.png");
            background = ImageIO.read(a);
            g.drawImage(background, 0, 0, DimensionSize.screenWidth
                    , DimensionSize.screenHeight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

