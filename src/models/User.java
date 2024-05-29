package models;

public class User {
    private int id;
    private String name;
    private String password;

    private static User instance;

    private User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public static User getInstance(int id, String name, String password) {
        if (instance == null) instance = new User(id, name, password);
        return instance;
    }

    public static User getInstance() {
        return instance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
