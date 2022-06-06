package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");

        Connection connection = DriverManager.getConnection(url, login, password);
        this.connection = connection;
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData.getUserName());
        System.out.println(metaData.getURL());

    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("create table if not exists %s();", tableName);
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }


    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table %s;", tableName);
            statement.execute(sql);
            System.out.println(tableName + " dropped.");
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String
                .format("ALTER TABLE %s ADD COLUMN  %s %s;", tableName, columnName, type);
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String
                .format("ALTER TABLE %s DROP COLUMN  %s;", tableName, columnName);
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
        throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String
                .format("ALTER TABLE %s RENAME COLUMN  %s TO %s;", tableName, columnName,
                    newColumnName);
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                    metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("app.properties"));
        TableEditor editor = new TableEditor(properties);
        editor.createTable("table1");
        editor.addColumn("table1", "name", "text");
        editor.addColumn("table1", "anotherName", "text");
        editor.renameColumn("table1", "anotherName", "surname");
        editor.dropColumn("table1", "name");
        editor.dropTable("table1");
    }
}
