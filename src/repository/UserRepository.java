package repository;

public class UserRepository extends ModelRepository {

    private static UserRepository instance;

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

    public void insert(String name, String password) {
        String query = "INSERT INTO User (name, password) values('%s', '%s')";
        con.execUpdate(String.format(query, name, password));
    }
}
