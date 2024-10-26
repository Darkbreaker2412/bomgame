package MenuSetUp;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import main.Main;

public class Sound {
    Clip clip;
    File file;
    FloatControl controlMusic;
    FloatControl controlSE;
    String name;
    float musicVolume;
    public static float SEVolume;
    public static Boolean SE;
    Boolean Music;


    public Sound(String a) {
        name = a;
        file = new File(Main.res + nameFile(a));
        this.setFile();
        readSetting(this);
        if (name.equals("Music")) {
            controlMusic = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (Music) {
                controlMusic.setValue(musicVolume);
            } else controlMusic.setValue(-80);
        } else {
            controlSE = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (SE) {
                controlSE.setValue(SEVolume);
            } else controlSE.setValue(-80);
        }
    }

    public void setFile() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        checkVolume();
        clip.start();
    }

    public void loop() {
        clip.loop(-1);
    }

    public void stopMusic() {
        clip.stop();
    }

    public String nameFile(String a) {
        return "/sound/" + a + ".wav";
    }

    public void checkVolume() {
        if (name.equals("Music")) {
            if (Music) {
                controlMusic.setValue(musicVolume);
                clip.start();
            } else clip.stop();
        } else {
            if (SE) controlSE.setValue(SEVolume);
            else controlSE.setValue(-80);
        }
    }

    void readSetting(Sound x) {
        File file = new File(Main.res + "/userData/setting.txt");
        try {
            Scanner in = new Scanner(file);
            x.musicVolume = in.nextFloat();
            Sound.SEVolume = in.nextFloat();
            x.Music = in.nextBoolean();
            Sound.SE = in.nextBoolean();
            in.close();
        } catch (Exception e) {
            System.out.println("Errol open file");
        }
    }

    void saveSetting(Sound music) {
        try {
            FileWriter writer = new FileWriter(Main.res + "/userData/setting.txt");
            writer.write(music.musicVolume + "\n");
            writer.write(Sound.SEVolume + "\n");
            writer.write(music.Music + "\n");
            writer.write(Sound.SE + "\n");
            writer.close();

        } catch (Exception e) {
            System.out.println("Errol save setting file");
        }
    }
}
