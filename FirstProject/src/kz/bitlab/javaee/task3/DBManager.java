package kz.bitlab.javaee.task3;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getAllUsers() {
        return users;
    }
}
