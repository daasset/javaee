package kz.bitlab.javaee.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DBManager {
    private static Connection connection;

    static  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int[] candidates = new int[1];
       var v = new HashSet<>(Arrays.asList(candidates));

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "123qwe123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addItem(Item item) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into items(name, price, amount) values(?, ?, ?)");
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getPrice());
            stmt.setInt(3, item.getAmount());
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    public static ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from items order by id");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount"));
                items.add(item);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    public static Item getItem(Long id) {
        if (id == null) {
            return null;
        }

        Item item = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from items where id = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet != null) {
                if (resultSet.next()) {
                    item = new Item(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getInt("amount")
                    );
                }
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return item;
    }

    public static boolean setItem(Item item) {
        if (item.getId() == null) {
            return false;
        }

        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("update items set name = ?, price = ?, amount = ? where id = ?");
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getPrice());
            stmt.setInt(3, item.getAmount());
            stmt.setLong(4, item.getId());
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean deleteItem(Long id) {
        if (id == null) {
            return false;
        }

        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from items where id = ?");
            stmt.setLong(1, id);
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }
}
