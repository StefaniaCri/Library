package repository;

import config.DatabaseConfiguration;
import entity.Book.Author;
import entity.Users.Librarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibrarianRepository {
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS  librarians" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30), surname varchar(30), username varchar(30), email varchar(30), password varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println("s");
            e.printStackTrace();
        }
    }

    public void addLibrarian(Librarian l) {
        String insertLibrarians = "INSERT INTO librarians(name,surname,username,email,password) VALUES (?,?,?,?,?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(insertLibrarians);
            statement.setString(1, l.getName());
            statement.setString(2, l.getSurname());
            statement.setString(3,l.getUsername());
            statement.setString(4,l.getEmail());
            statement.setString(5,l.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Librarian> getAllLibrarians() {
        String selectSQL = "SELECT * FROM librarians";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Librarian> librarians = new ArrayList<>();
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
                Librarian librarian = new Librarian(name,surname,username,email,password);
                librarian.setId(id);
                librarians.add(librarian);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return librarians;
    }

    public void changeLibrarianPassword(Librarian librarian,String newPassword) {
        try {
            String updateSql = "update librarians set password=?  where username = ? and password = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(2, librarian.getUsername());
            preparedStatement.setString(3, librarian.getPassword());
            preparedStatement.setString(1, newPassword);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    public void deleteLibrarian(Librarian l) {
        try {
            String deleteSql = "delete from librarians where username = ? and password = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, l.getUsername());
            preparedStatement.setString(2, l.getPassword());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
