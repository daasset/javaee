package kz.bithub.javaee.example;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Item> itemsList = new ArrayList<>();

    public static void addItem(Item item) {
        itemsList.add(item);
    }

    public static ArrayList<Item> getAllItems() {
        return itemsList;
    }
}
