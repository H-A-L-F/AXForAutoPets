package repository;

public class UserRepository extends ModelRepository {

    private int id;
    private String name;
    private String password;

    private static UserRepository instance;

    private UserRepository(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    private UserRepository(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public static UserRepository getInstance(int id, String name, String password) {
        if (instance == null) instance = new UserRepository(id, name, password);
        return instance;
    }

    public static UserRepository getInstance(String name, String password) {
        if (instance == null) instance = new UserRepository(name, password);
        return instance;
    }

    public void insert(String name, String password) {
        String query = "INSERT INTO User (name, password) values('%s', '%s')";
        con.execUpdate(String.format(query, name, password));
        getLastInserted();
    }

    private UserRepository getLastInserted() {
        String query = "SELECT * FROM user WHERE name = '%s' and password = '%s' ORDER BY id DESC LIMIT 1";
        con.execQuery(String.format(query, name, password));
        try {
            if(!con.rs.next()) {
                System.out.println("Failed to get last inserted user");
                this.id = con.rs.getInt(1);
                this.name = con.rs.getString(2);
                this.password = con.rs.getString(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getId() {
        return id;
    }
}
