package repository;

import config.DatabaseConfiguration;
import entity.Book.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRepository {
    AuthorRepository authorRepository = new AuthorRepository();
    List<Author> authors = authorRepository.getAllAuthors();
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS  books" +
                "(isbn varchar(30) PRIMARY KEY, title varchar(30), pages int, authorId int, booktype int, publishingHouse varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println("s");
            e.printStackTrace();
        }
    }

    public void addBook(Book b) {
        String insertBook = "INSERT INTO books(isbn,title,pages,authorId,booktype,publishingHouse) VALUES (?,?,?,?,?,?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(insertBook);
            statement.setString(1, b.getIsbn().getIsbn());
            statement.setString(2, b.getTitle());
            statement.setInt(3,b.getPages());
            statement.setInt(4,b.getAuthor().getId());
            statement.setInt(5, b.getBookType().getId());
            statement.setString(6, b.getPublishingHouse());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        String selectSQL = "SELECT * FROM books";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                var isbn = resultSet.getString("isbn");
                var title = resultSet.getString("title");
                var pages = resultSet.getInt("pages");
                var authorId = resultSet.getInt("authorId");
                var booktype = resultSet.getInt("booktype");
                var publishingHouse = resultSet.getString("publishingHouse");
                ISBN is = new ISBN(isbn);
                List<Author> a = authors.stream().filter(x -> x.getId() == authorId).collect(Collectors.toList());
                BookType[] bookTypes = BookType.values();
                BookType b = bookTypes[booktype];
                Book book = new Book(is,title,pages, a.get(0),b,publishingHouse);
                books.add(book);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ISBNFormatException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void deleteBook(String title) {
        try {
            String deleteSql = "delete from books where title = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, title);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editBook(Book book, Integer pages) {
        try {
            String updateSql = "update books set pages=?  where title = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, pages);
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

}
