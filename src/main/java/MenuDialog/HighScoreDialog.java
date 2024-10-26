package MenuDialog;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.*;

import MenuSetUp.MyButton;
import res.LoadResource;
import userClass.User;
import userClass.UserList;

import java.util.*;

public class HighScoreDialog extends SuperDialog {
    MyButton back;
    UserList userList;

    public HighScoreDialog(JFrame parent, UserList userList) {
        super(parent);
        this.userList = userList;

        //SORT BY SCORE IN DESCENDING
        this.userList.list.sort((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));

        setTitle("HIGH SCORE");
        setContent();

        //SET BACK BUTTON
        back = new MyButton("back");
        back.setLocateButton((500 - 50) / 2, 400);
        back.addActionListener(e -> setVisible(false));
        getContentPane().add(back);

        setBackground();

    }

    void setContent() {
        JPanel content = new JPanel();
        content.setSize(150, 200);
        content.setLocation(150, 100);
        content.setOpaque(false);
        //ADD USER INTO CONTENT PANEL
        int numHighScore;
        if (userList.size <= 5) numHighScore = userList.size;
        else numHighScore = 5;
        for (int i = 0; i < numHighScore; i++) {
            if (userList.list.get(i).getScore() == 0) break;
            JLabel name = new JLabel();
            name.setText(userList.list.get(i).getUserName());
            name.setFont(LoadResource.Courier_New_Bold_20);
            content.add(name);

            Border border = new BevelBorder(1);
            JLabel score = new JLabel();
            score.setText(" " + userList.list.get(i).getScore());
            score.setBorder(border);
            score.setFont(LoadResource.Courier_New_Bold_20);
            content.add(score);
        }
        content.setLayout(new GridLayout(5, 2, 10, 10));
        getContentPane().add(content);
    }
}
