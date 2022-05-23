package service.Write;

import entity.Users.Librarian;
import entity.Users.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibrarianWriteToCSV implements IWrite<Librarian> {
    private static LibrarianWriteToCSV writeInstance = null;

    private LibrarianWriteToCSV() {
    }

    public static LibrarianWriteToCSV getInstance() {
        if (writeInstance == null)
            writeInstance = new LibrarianWriteToCSV();
        return writeInstance;
    }

    public void writeCSV(User a) {
        List<String> toWrite = new ArrayList<>(Arrays.asList(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword()));
        writeToCSV(".\\src\\data\\librarian.csv", toWrite);
    }

    @Override
    public void updateCSV(List<Librarian> whatTo) {
        try {
            new FileWriter(".\\src\\data\\librarian.csv");

            for (var librarian : whatTo) {
                writeCSV(librarian);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
