package service.Read;

import entity.Users.Librarian;

import java.util.ArrayList;
import java.util.List;

public class LibrarianReadFromCSV implements IRead<Librarian> {

    private static LibrarianReadFromCSV readInstance = null;

    private LibrarianReadFromCSV(){

    }

    public static LibrarianReadFromCSV getInstance() {
        if (readInstance == null)
            readInstance = new LibrarianReadFromCSV();
        return readInstance;
    }

    @Override
    public List<Librarian> readCSV() {
        List<Librarian> librarians = new ArrayList<>();
        var columns =getCSVcolumns(".\\src\\data\\librarian.csv");
        for (var column : columns) {
            librarians.add(new Librarian(column[0], column[1], column[2], column[3], column[4]));
        }
        return librarians;
    }
}
