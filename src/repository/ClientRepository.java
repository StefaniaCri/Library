package repository;

import config.DatabaseConfiguration;
import entity.Users.Client;
import entity.Users.Librarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS  clients" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30), surname varchar(30), username varchar(30), email varchar(30), password varchar(30), phone varchar(10))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println("s");
            e.printStackTrace();
        }
    }

    public void addClient(Client c) {
        String insertClient = "INSERT INTO clients(name,surname,username,email,password,phone) VALUES (?,?,?,?,?,?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(insertClient);
            statement.setString(1, c.getName());
            statement.setString(2, c.getSurname());
            statement.setString(3,c.getUsername());
            statement.setString(4,c.getEmail());
            statement.setString(5,c.getPassword());
            statement.setString(6,c.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllClient() {
        String selectSQL = "SELECT * FROM clients";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var surname = resultSet.getString("surname");
                var username =  resultSet.getString("username");
                var email = resultSet.getString("email");
                var password =  resultSet.getString("password");
                var phone = resultSet.getString("phone");
                Client client = new Client(name,surname,username,email,password,phone);
                client.setId(id);
                clients.add(client);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }

    public void changeClientPassword(Client client,String newPassword) {
        try {
            String updateSql = "update clients set password=?  where username = ? and password = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(2, client.getUsername());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setString(1, newPassword);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    public void deleteClient(Client c) {
        try {
            String deleteSql = "delete from clients where username = ? and password = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, c.getUsername());
            preparedStatement.setString(2, c.getPassword());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
