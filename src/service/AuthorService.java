package service;

import entity.Book.Author;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService extends MainService {
    protected static AuthorService instance = null;
    WriteToCSV writer = WriteToCSV.getInstance();
    AuditService auditService = AuditService.getInstance();
    ReadFromCSV reader = ReadFromCSV.getInstance();
    private Boolean needUpdate = false;

    public AuthorService() {
    }

    public static AuthorService getInstance() {
        if (instance == null) {
            return instance = new AuthorService();
        }
        return instance;
    }

    Author readAuthor() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());

        System.out.println("Enter first name");
        String first_name = read.next();

        System.out.println("Enter last name");
        String last_name = read.next();

        for (Author author : authors)
            if (author.getFirst_name().equals(first_name) && author.getLast_name().equals(last_name)) {
                System.out.println("Author already exists");
                return author;
            }

        Author author = new Author(first_name, last_name);
        authors.add(author);
        writeCSV(first_name, last_name);
        System.out.println(authors);
        return author;
    }

    public void readCSV() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        var columns = reader.getCSVcolumns(".\\src\\data\\author.csv");
        for (var column : columns) {
            authors.add(new Author(column[0], column[1]));
        }
        //System.out.println(authors);
    }

    public void writeCSV(String first_name, String last_name) {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        List<String> toWrite = new ArrayList<>();
        toWrite.add(first_name);
        toWrite.add(last_name);
        writer.writeCSV(".\\src\\data\\author.csv", toWrite);

    }

    public void updateCSV() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        try {
            var file = new FileWriter(".\\src\\data\\author.csv");

            for (var author : authors) {
                writeCSV(author.getFirst_name(), author.getLast_name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Author getAuthorByFullName() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        System.out.println("Enter first name");
        String first_name = read.next();

        System.out.println("Enter last name");
        String last_name = read.next();
        for (Author author : authors)
            if (author.getFirst_name().equals(first_name) && author.getLast_name().equals(last_name)) {
                return author;
            }
        return null;
    }

    public Boolean getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(Boolean needUpdate) {
        this.needUpdate = needUpdate;
    }
}
