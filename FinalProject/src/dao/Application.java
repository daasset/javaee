package dao;

import org.postgresql.ds.PGSimpleDataSource;

public enum Application {
    INSTANCE;

    private PGSimpleDataSource dataSource;

    public PGSimpleDataSource dataSource() {
        if (dataSource == null) {
            dataSource = new PGSimpleDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPortNumber(5432);
            dataSource.setDatabaseName("javaee_final");
            dataSource.setUser("postgres");
            dataSource.setPassword("123qwe123");
        }

        return dataSource;
    }
}
