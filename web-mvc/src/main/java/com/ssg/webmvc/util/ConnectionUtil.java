package com.ssg.webmvc.util;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;

public enum ConnectionUtil {
    INSTANCE;
    private final HikariDataSource ds;

    ConnectionUtil() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        hikariConfig.setUsername("webuser");
        hikariConfig.setPassword("webuser");
        hikariConfig.addDataSourceProperty("cachePrepareStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        ds = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws Exception {
        return ds.getConnection(); //Connection.INSTANCE.getConnection();
    }

}
