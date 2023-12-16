package github.com.classes.database;

import github.com.classes.config.DatabaseProperties;
import github.com.classes.config.PropertiesFactory;

import java.sql.*;
import java.util.*;

public class MyDatabase {
    private static MyDatabase instance;

    private DatabaseProperties properties = PropertiesFactory.getProperties();

    public MyDatabase() {
        init();
    }
    public synchronized static MyDatabase getInstance() {
        if (instance == null) {
            instance = new MyDatabase();
        }
        return instance;
    }

    private void init() {
        createSchema();
        createtable_gameTime();
    }



    public void createSchema() {
        String sql = """
                create schema if not exists Race
                """;
        execute(sql);
    }

    public void  createtable_gameTime() {
        String sql = """
                create table if not exists Race.table_game (
                    id serial primary key,
                    gameTime real
              
                )
                """;
        execute(sql);
    }

    public List<HashMap<Integer, Long>> selectALL(String table) {
        List<HashMap<Integer, Long>> result = new ArrayList<>();
        String sql = """
                select *
                from Race.%s 
                """;
        String select = String.format(sql, table);
        try (Connection connection = connect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                HashMap<Integer, Long> IdName = new HashMap<>();
                int id = resultSet.getInt("id");
                long time = resultSet.getInt("gameTime");
                IdName.put(id, time);
                result.add(IdName);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public HashMap<Integer, Long> selectById(String table, int id) {
        HashMap<Integer, Long> result = new HashMap<>();
        String sql = """
                select (gameTime)
                from Race.%s
                where id = %d
                """;
        String select = String.format(sql, table, id);
        try (Connection connection = connect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                result.put(id, resultSet.getLong("gameTime"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public void execute(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(
                properties.getUrl(),
                properties.getLogin(),
                properties.getPassword()
        );
    }





}
