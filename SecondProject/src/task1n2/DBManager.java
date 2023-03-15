package task1n2;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Item> items = new ArrayList<>();
    private static long count = 0;

    public static void addItem(Item item) {
        if (item.getId() != null) {
            throw new IllegalArgumentException();
        }
        item.setId(count++);
        items.add(item);
    }

    public static ArrayList<Item> getAllItems() {
        return items;
    }

    public static Item getItemById(Long id) {
        if (id == null) {
            return null;
        }

        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null;
    }
}
