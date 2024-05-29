package repository;

import constants.Connect;

public abstract class ModelRepository {
    protected int id;
    protected static Connect con = Connect.getInstance();

    public int getId() {
        return id;
    }
}
