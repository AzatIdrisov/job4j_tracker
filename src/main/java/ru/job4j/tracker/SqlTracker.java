package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    private Connection cn;

    public SqlTracker(Connection connection) {
        this.cn = connection;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        String sql = "INSERT INTO items (name) VALUES (?)";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            while (generatedKey.next()) {
                item.setId(generatedKey.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        String updateName = "update items set name = ? where id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(updateName)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "delete from items where id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> foundItems = new ArrayList<>();
        String sql = "select * from items ";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                foundItems.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return foundItems;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> foundItems = new ArrayList<>();
        String sql = "select * from items where name = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                foundItems.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return foundItems;
    }

    @Override
    public Item findById(String id) {
        Item foundItem = new Item();
        String sql = "select * from items where id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundItem.setId(resultSet.getInt("id"));
                foundItem.setName(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundItem;
    }

}