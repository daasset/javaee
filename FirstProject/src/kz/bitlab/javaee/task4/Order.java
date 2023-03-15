package kz.bitlab.javaee.task4;

public class Order {
    private String name;
    private String surname;

    private String orderName;

    public Order() {
    }

    public Order(String name, String surname, String orderName) {
        this.name = name;
        this.surname = surname;
        this.orderName = orderName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
