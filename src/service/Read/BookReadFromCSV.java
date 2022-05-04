package service.Read;

import entity.Book.*;

import java.util.ArrayList;
import java.util.List;

public class BookReadFromCSV implements IRead<Book> {

    private static BookReadFromCSV readInstance = null;

    private BookReadFromCSV() {
    }

    public static BookReadFromCSV getInstance() {
        if (readInstance == null)
            readInstance = new BookReadFromCSV();
        return readInstance;
    }

    @Override
    public List<Book> readCSV() {
        List<Book> books = new ArrayList<>();
        try {
            var columns = getCSVcolumns(".\\src\\data\\book.csv");
            for (var column : columns) {
                ISBN isbn = new ISBN(column[0]);
                Author aut = new Author(column[3], column[4]);
                int pages = Integer.parseInt(column[2]);
                BookType book = BookType.valueOf(column[5]);
                books.add(new Book(isbn, column[1], pages, aut, book, column[6]));
            }
        } catch (ISBNFormatException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

}
