package userClass;

import java.io.File;
import java.util.*;

public class User {
    String userName;
    int level;
    int[] lvScore;

    public User() {
        level = 1;
        lvScore = new int[3];
    }

    public User(String userName) {
        this.userName = userName;
        level = 1;
        lvScore = new int[3];
    }

    public void setLevel() {
        level++;
    }

    public void setLevel(int lv) {
        if (level < lv)
            level = lv;
    }

    public void setScore(int lv, int score) {

        lvScore[lv - 1] += score;
    }

    public int getLevel() {
        return level;
    }

    public int getScoreLv(int lv) {
        return lvScore[lv - 1];
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < lvScore.length; i++) {
            score += lvScore[i];
        }
        return score;
    }

    public String getUserName() {
        return userName;
    }

    public void saveUser(File a) {

    }

    public static User getUser(Scanner in) {
        User re = new User(in.next());
        re.level = in.nextInt();
        for (int i = 0; i < re.level; i++) {
            re.lvScore[i] = in.nextInt();
        }
        return re;
    }


    @Override
    public String toString() {
        return userName;
    }
}
