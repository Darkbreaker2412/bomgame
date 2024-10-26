package MenuSetUp;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import MenuDialog.ContinueDialog;
import MenuDialog.NoticeDialog;
import MenuDialog.RemindDialog;
import MenuDialog.ReplaceDialog;

import MenuDialog.HighScoreDialog;
import MenuDialog.NewGameDialog;
import userClass.User;
import userClass.UserList;

public class ChangePanel {
    public Container contentPane;
    public CardLayout cardLayout;
    public UserList userList;
    public MenuPanel menu;
    public StartPanel start;
    public JFrame frame;

    public ChangePanel(JFrame frame) {

        this.frame = frame;
        cardLayout = new CardLayout();
        contentPane = frame.getContentPane();
        contentPane.setLayout(cardLayout);
        userList = new UserList();

        menu = new MenuPanel(DimensionSize.screenWidth, DimensionSize.screenHeight);
        start = new StartPanel(frame);

        contentPane.setPreferredSize(new Dimension(DimensionSize.screenWidth, DimensionSize.screenHeight));
        contentPane.add(menu, "menu");//panel 1 in contentPane
        contentPane.add(start, "start");//panel 2 in contentPane

    }

    public void actionChange() {
        ReplaceDialog replace = new ReplaceDialog(frame);

        // SET UP DIALOG NEW GAME
        newUserAction NA = new newUserAction(start.newUser, this, replace);
        start.newUser.okButton.addActionListener(NA);
        start.newUser.textField.addActionListener(NA);


        //SET UP DIALOG REPLACE IF USER HAS EXISTED
        replace.okButton.addActionListener(new replaceAction(this, replace));


        //SET UP OTHER BUTTON IN START PANEL

        start.score.addActionListener(e -> {
            HighScoreDialog highScore = new HighScoreDialog(frame, userList);
            highScore.setVisible(true);
        });

        start.continueButton.addActionListener(e -> {
            ContinueDialog dialog = new ContinueDialog(frame, this);
            dialog.setVisible(true);
        });

        start.back.addActionListener(e -> cardLayout.previous(contentPane));

        start.setting.addActionListener(e -> {
            SettingPanel setting = new SettingPanel(menu.music);
            contentPane.add(setting, "setting");
            cardLayout.show(contentPane, "setting");
            setting.back.addActionListener((event) -> {
                cardLayout.show(contentPane, "start");
                menu.music.saveSetting(menu.music);
            });
        });
        // // SET UP MENU PANEL BUTTON
        menu.start.addActionListener((e) -> cardLayout.next(contentPane));

        menu.quit.addActionListener((e) -> {
            RemindDialog a = new RemindDialog(frame, "exit Game ");
            a.setVisible(true);

            //SET UP BUTTON FOR RemindDialog
            a.okButton.addActionListener(event -> {
                userList.saveGame();
                System.exit(0);
            });
            a.noButton.addActionListener(event -> a.setVisible(false));
        });

    }

}

class newUserAction implements ActionListener {
    NewGameDialog newUser;
    ChangePanel change;
    ReplaceDialog replace;

    public newUserAction(NewGameDialog newUser, ChangePanel change, ReplaceDialog replace) {
        this.newUser = newUser;
        this.change = change;
        this.replace = replace;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String x = newUser.textField.getText();
        if (x.isEmpty()) {
            NoticeDialog note = new NoticeDialog(change.frame);
            note.setVisible(true);
        } else {
            boolean check = false;
            for (User a : change.userList.list) {
                if (a.getUserName().equals(x)) {
                    check = true;
                    replace.setVisible(true);
                    break;
                }
            }
            if (!check) {
                User A = new userClass.User(x);
                newUser.setVisible(false);
                newUser.textField.setText("");
                change.contentPane.add(new LevelPanel(A, change), "newLevel");
                change.cardLayout.show(change.contentPane, "newLevel");
            }
        }

    }
}

class replaceAction implements ActionListener {
    ChangePanel changePanel;
    ReplaceDialog replace;

    public replaceAction(ChangePanel changePanel, ReplaceDialog replace) {
        this.changePanel = changePanel;
        this.replace = replace;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String x = changePanel.start.newUser.textField.getText();
        changePanel.start.newUser.setVisible(false);
        changePanel.start.newUser.textField.setText("");
        changePanel.contentPane.add(new LevelPanel(new User(x), changePanel), "level");
        changePanel.cardLayout.show(changePanel.contentPane, "level");
        replace.setVisible(false);
    }
}