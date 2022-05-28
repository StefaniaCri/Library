package repository;

import config.DatabaseConfiguration;
import entity.Book.Author;
import entity.Book.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class ReviewRepository {
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS  review" +
                "(id int PRIMARY KEY AUTO_INCREMENT, client varchar(30), text varchar(255),data DATE, numberOfStars int)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println("s");
            e.printStackTrace();
        }
    }

    public void addReview(Review r) {
        String insertAuthor = "INSERT INTO review(client,text,data,numberOfStars) VALUES (?,?,?,?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            PreparedStatement statement = connection.prepareStatement(insertAuthor);
            statement.setString(1, r.getClient());
            statement.setString(2, r.getText());
            statement.setDate(3,date);
            statement.setInt(4,r.getNumberOfStars());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
