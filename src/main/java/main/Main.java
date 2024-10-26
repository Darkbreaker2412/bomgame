package main;

import javax.swing.JFrame;

import MenuSetUp.ChangePanel;
import MenuSetUp.DimensionSize;

import java.io.File;

public class Main {

    public static String res = new File("..src\\main\\java\\res").getAbsolutePath().replace("..", "");

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(DimensionSize.screenWidth, DimensionSize.screenHeight);
        window.setResizable(false);
        window.setTitle("BOMB GAME");

        ChangePanel change = new ChangePanel(window);
        change.actionChange();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}