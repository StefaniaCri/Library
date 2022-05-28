package repository;

import config.DatabaseConfiguration;
import entity.Book.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS  authors" +
                "(id int PRIMARY KEY AUTO_INCREMENT, firstName varchar(30), lastName varchar(30))";


        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println("s");
            e.printStackTrace();
        }
    }

    public void addAuthor(Author a) {
        String insertAuthor = "INSERT INTO authors(firstName,lastName) VALUES (?,?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(insertAuthor);
            statement.setString(1, a.getFirst_name());
            statement.setString(2, a.getLast_name());
            statement.executeUpdate();
            System.out.println("?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Integer getAuthorID(Author author){
        try {
            String selectSQL = "SELECT * FROM authors where firstName = ? and lastName = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, author.getFirst_name());
            preparedStatement.setString(2, author.getLast_name());
            ResultSet resultSet =  preparedStatement.executeQuery();
            resultSet.next();
            var id = resultSet.getInt("id");
            var first_name = resultSet.getString("firstName");
            var last_name = resultSet.getString("lastName");
            resultSet.next();
            System.out.println(id + first_name +last_name);
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Author> getAllAuthors() {
        String selectSQL = "SELECT * FROM authors";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Author> autori = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var first_name = resultSet.getString("firstName");
                var last_name = resultSet.getString("lastName");

                Author author = new Author(first_name, last_name);
                author.setId(id);
                autori.add(author);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return autori;
    }

    public void deleteAuthor(String first_name, String last_name) {
        try {
            String deleteSql = "delete from authors where firstName = ? and lastName = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editAuthor(Author author) {
        try {
            String updateSql = "update authors set first_name=?  where id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, author.getFirst_name());
            preparedStatement.setInt(2, author.getId());
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

}