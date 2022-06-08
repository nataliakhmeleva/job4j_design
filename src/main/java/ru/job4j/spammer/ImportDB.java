package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            users = rd.lines()
                .filter(line -> !line.isEmpty())
                .map(line -> line.split(";", 2))
                .filter(this::checkIsEmpty)
                .map(str -> new User(str[0], str[1]))
                .collect(Collectors.toList());
        }
        return users;
    }

    public boolean checkIsEmpty(String[] data) {
        if (data[0].isEmpty() || data[1].isEmpty()) {
            throw new IllegalArgumentException("The data is empty.");
        }
        return true;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
            cfg.getProperty("jdbc.url"),
            cfg.getProperty("jdbc.username"),
            cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (Statement statement = cnt.createStatement()) {
                    statement
                        .execute(String.format("create table if not exists  users (%s, %s, %s);",
                            "id serial primary key",
                            "name varchar(255)",
                            "email varchar(255)"));
                }
                try (PreparedStatement ps = cnt
                    .prepareStatement("insert into users (name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {

        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader()
            .getResourceAsStream("app1.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}