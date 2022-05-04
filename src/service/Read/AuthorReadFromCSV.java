package service.Read;


import entity.Book.Author;


import java.util.ArrayList;
import java.util.List;


public class AuthorReadFromCSV implements IRead<Author> {
    private static AuthorReadFromCSV readInstance = null;

    private AuthorReadFromCSV() {
    }

    public static AuthorReadFromCSV getInstance() {
        if (readInstance == null)
            readInstance = new AuthorReadFromCSV();
        return readInstance;
    }

    @Override
    public List<Author> readCSV() {
        List<Author> authors = new ArrayList<>();
        var columns = getCSVcolumns(".\\src\\data\\author.csv");
        for (var column : columns) {
            authors.add(new Author(column[0], column[1]));
        }
        return authors;
    }
}