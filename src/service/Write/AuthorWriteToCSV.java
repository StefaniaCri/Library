package service.Write;


import entity.Book.Author;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorWriteToCSV implements IWrite<Author> {
    private static AuthorWriteToCSV writeInstance = null;

    private AuthorWriteToCSV() {
    }

    public static AuthorWriteToCSV getInstance() {
        if (writeInstance == null)
            writeInstance = new AuthorWriteToCSV();
        return writeInstance;
    }

    public void writeCSV(String first_name, String last_name) {
        List<String> toWrite = new ArrayList<>();
        toWrite.add(first_name);
        toWrite.add(last_name);
        writeToCSV(".\\src\\data\\author.csv", toWrite);

    }

    @Override
    public void updateCSV(List<Author> whatTo ){
        try {
            new FileWriter(".\\src\\data\\author.csv");

            for (var author : whatTo) {
                writeCSV(author.getFirst_name(), author.getLast_name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
