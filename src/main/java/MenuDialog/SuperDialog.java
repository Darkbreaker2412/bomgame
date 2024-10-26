package MenuDialog;

import javax.swing.*;

import MenuSetUp.MyButton;
import res.LoadResource;

public class SuperDialog extends JDialog {
    public SuperDialog(JFrame parent) {
        super(parent);
        setSize(500, 520);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

    }

    public void setBackground() {
        Icon background;
        JLabel bg;
        background = LoadResource.dialogBackground;
        bg = new JLabel(background);
        bg.setLocation(-5, -15);
        bg.setSize(500, 520);
        getContentPane().add(bg);
    }

    public void setTitle(String Title) {
        JLabel title = new JLabel(Title);
        title.setFont(LoadResource.Courier_New_Bold_30);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setSize(200, 30);
        title.setLocation(150, 25);
        getContentPane().add(title);
    }

    public void addButton(MyButton button1, MyButton button2) {
        JPanel button = new JPanel();
        button.add(button1);
        button.add(button2);
        button.setSize(500, 55);
        button.setLocation(0, 400);
        button.setOpaque(false);
        getContentPane().add(button);
    }

    public void addButton(JButton button1, JButton button2) {
        JPanel button = new JPanel();
        button.add(button1);
        button.add(button2);
        button.setSize(500, 55);
        button.setLocation(0, 400);
        button.setOpaque(false);
        getContentPane().add(button);
    }

    public void addButton(JButton button1, JButton button2, JButton button3) {
        JPanel button = new JPanel();
        button.add(button1);
        button.add(button2);
        button.add(button3);
        button.setSize(500, 55);
        button.setLocation(0, 400);
        button.setOpaque(false);
        getContentPane().add(button);
    }

}
