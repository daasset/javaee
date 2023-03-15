package kz.bitlab.javaee.task2;

import java.util.ArrayList;
import java.util.Arrays;

public class DBManager {

    private final ArrayList<String> headers = new ArrayList<>(
            Arrays.asList("NAME", "SURNAME", "DEPARTMENT", "SALARY")
    );
    private static ArrayList<User> users = new ArrayList<>(
            Arrays.asList(
                    new User("Ilyas", "Zhuanyshev", "IT", 550000),
                    new User("Aybek", "Bagit", "Management", 650000),
                    new User("Alibek", "Serikov", "HR", 350000),
                    new User("Serzhan", "Berikov", "IT", 450000),
                    new User("Madina", "Assetova", "PR", 350000),
                    new User("Anel", "Mukhamejanova", "Management", 400000)
            )
    );

    public ArrayList<String> getHeaders() {
        return headers;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
