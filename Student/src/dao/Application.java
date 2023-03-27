package dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public enum Application {
    INSTANCE;

    private PGSimpleDataSource dataSource;

    public DataSource dataSource() {
        if (dataSource == null) {
            dataSource = new PGSimpleDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPortNumber(5432);
            dataSource.setDatabaseName("mydb");
            dataSource.setUser("postgres");
            dataSource.setPassword("123qwe123");
        }

        return dataSource;
    }
}
