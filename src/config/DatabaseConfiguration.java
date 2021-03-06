package config;

import entity.Users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc-library";
    private static final String USER = "root";
    private static final String password = "DoggieMusk";

    private static Connection databaseConnection;

    private DatabaseConfiguration(){

    }

    public static Connection getDatabaseConnection(){
        try {
            if(databaseConnection == null || databaseConnection.isClosed()) {
                    databaseConnection = DriverManager.getConnection(DB_URL, USER,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseConnection;
    }

    public static void closeDatabaseConfiguration()
    {
        try{
            if(databaseConnection == null || !databaseConnection.isClosed())
                databaseConnection.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
