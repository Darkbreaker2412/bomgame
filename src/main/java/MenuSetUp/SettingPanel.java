package MenuSetUp;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import main.Main;
import res.LoadResource;

public class SettingPanel extends JPanel {
    MyButton back;
    Sound music;
    MySlider musicSlider;
    MySlider SESlider;

    public SettingPanel(Sound music) {

        this.music = music;
        setFocusable(false);
        setLayout(null);

        //TITLE PANEL
        JLabel setting = new JLabel("SETTING");
        setting.setSize(235, 100);
        setting.setFont(LoadResource.Courier_New_Bold_38);
        setting.setLocation((DimensionSize.screenWidth - 150) / 2, 20);
        add(setting);

        //BUTTON BACK
        back = new MyButton("back");
        back.setLocateButton(10, 10);
        add(back);

        //SLIDER MUSIC
        musicSlider = new MySlider(250, 25, (int) music.musicVolume, "music");
        musicSlider.setLocateMySlider((DimensionSize.screenWidth - 250) / 2
                , ((DimensionSize.maxScreenRow - 4) / 2) * DimensionSize.tileSize);
        musicSlider.addMySlider(this);
        musicSlider.slider.setEnabled(music.Music);
        musicSlider.slider.addChangeListener((e) -> {
            music.musicVolume = musicSlider.slider.getValue();
            music.controlMusic.setValue(music.musicVolume);
        });

        //SLIDER SOUND EFFECT
        SESlider = new MySlider(250, 25, (int) Sound.SEVolume, "SE");
        SESlider.setLocateMySlider((DimensionSize.screenWidth - 250) / 2
                , ((DimensionSize.maxScreenRow - 4) / 2 + 1) * DimensionSize.tileSize);
        SESlider.addMySlider(this);
        SESlider.slider.setEnabled(Sound.SE);
        SESlider.slider.addChangeListener((e) -> Sound.SEVolume = SESlider.slider.getValue());


        //BUTTON MUSIC AND SE
        addMusicButton();
        addSEButton();

    }

    void addMusicButton() {
        MyButton musicButton = music.Music ? new MyButton("music") : new MyButton("musicOff");
        musicButton.setLocateButton(DimensionSize.screenWidth / 2 - 100
                , ((DimensionSize.maxScreenRow - 4) / 2 + 3) * DimensionSize.tileSize);
        add(musicButton);
        musicButton.addActionListener((e) -> {
            music.Music = !music.Music;
            //set icon
            musicButton.icon = music.Music ? LoadResource.musicOnBtnIcon : LoadResource.musicOffBtnIcon;
            music.checkVolume();

            //slider unmoved
            musicSlider.slider.setEnabled(music.Music);
        });
    }


    void addSEButton() {
        MyButton seButton = Sound.SE ? new MyButton("sound") : new MyButton("soundOff");
        seButton.setLocateButton(DimensionSize.screenWidth / 2 + 50
                , ((DimensionSize.maxScreenRow - 4) / 2 + 3) * DimensionSize.tileSize);
        add(seButton);
        seButton.addActionListener((e) -> {
            Sound.SE = !Sound.SE;
            //set icon
            seButton.icon = Sound.SE ? LoadResource.soundOnBtnIcon : LoadResource.soundOffBtnIcon;

            //slider unmoved
            SESlider.slider.setEnabled(Sound.SE);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage background;
        try {
            File a = new File(Main.res + "/background/background.png");
            background = ImageIO.read(a);
            g.drawImage(background, 0, 0, DimensionSize.screenWidth, DimensionSize.screenHeight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
