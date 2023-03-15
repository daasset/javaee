package kz.bitlab.javaee.task4;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public static ArrayList<Order> getAllOrders() {
        return orders;
    }
}
