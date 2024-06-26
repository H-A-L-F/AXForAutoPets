package repository;

import constants.Connect;

import java.sql.ResultSet;

public abstract class ModelRepository {
    protected int id;
    protected static Connect con = Connect.getInstance();

    public int getId() {
        return id;
    }

    public static ResultSet getRsFromId(long id, String table) {
        String query = "SELECT * FROM `%s` WHERE id = " + id;
        ResultSet rs = con.execQueryWithRes(String.format(query, table));
        return rs;
    }
}
