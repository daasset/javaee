package kz.bitlab.javaee.task7;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Footballer> footballersLis = new ArrayList<>();
    private static long count = 0;

    public void addFootballer(Footballer footballer) {
        if (footballer.getId() != null) {
            throw new IllegalArgumentException("Cannot add user with id");
        }
        footballer.setId(count++);
        footballersLis.add(footballer);
    }

    public static ArrayList<Footballer> getAllFootballersLis() {
        return footballersLis;
    }
}
