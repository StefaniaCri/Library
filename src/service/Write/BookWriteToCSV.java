package service.Write;

import entity.Book.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookWriteToCSV implements IWrite<Book> {
    private static BookWriteToCSV writeInstance = null;

    private BookWriteToCSV() {
    }

    public static BookWriteToCSV getInstance() {
        if (writeInstance == null)
            writeInstance = new BookWriteToCSV();
        return writeInstance;
    }

    public void writeCSV(Book a) {
        List<String> toWrite = new ArrayList<>(Arrays.asList(a.getIsbn().getIsbn(), a.getTitle(), String.valueOf(a.getPages()), a.getAuthor().getFirst_name(), a.getAuthor().getLast_name(), String.valueOf(a.getBookType()), a.getPublishingHouse()));
        writeToCSV(".\\src\\data\\book.csv", toWrite);
    }

    @Override
    public void updateCSV(List<Book> whatTo) {
        try {
            new FileWriter(".\\src\\data\\book.csv");

            for (var book : whatTo) {
                writeCSV(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
