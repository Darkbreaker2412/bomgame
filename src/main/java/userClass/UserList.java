package userClass;

import java.util.*;

import main.Main;

import java.io.File;
import java.io.FileWriter;

public class UserList {
    public ArrayList<User> list;
    public int size;

    public UserList() {
        File file;
        list = new ArrayList<>();
        try {
            file = new File(Main.res + "/userData/userData.txt");
            Scanner in = new Scanner(file).useDelimiter("\\s*;\\s*");
            while (in.hasNextLine()) {
                list.add(User.getUser(in));
            }
            in.close();
        } catch (Exception e) {
//            System.out.println("Errol is here");
//            e.printStackTrace();
        }
        size = list.size();
    }

    public void addUser(User a) {
        boolean check = true;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getUserName().equals(a.getUserName())) {
                list.set(i, a);
                check = false;
                break;
            }
        }
        if (check) {
            list.addFirst(a);
            size = list.size();
        }
    }

    public void saveGame() {
        try {
            FileWriter writer = new FileWriter(Main.res + "/userData/userData.txt");
            for (User a : list) {
                String x = a.getUserName() + ";" + a.level + ";";
                for (int i = 0; i < a.level; i++) {
                    x += a.lvScore[i] + ";";
                }
                writer.write(x + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Errol save file");
        }
    }
}
