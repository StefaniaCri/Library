package service;

import entity.Book.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookService extends MainService {
    protected static BookService instance = null;
    AuthorService authorService = AuthorService.getInstance();
    ISBNService isbnService = ISBNService.getInstance();
    WriteToCSV write = WriteToCSV.getInstance();
    ReadFromCSV reader = ReadFromCSV.getInstance();
    AuditService auditService = AuditService.getInstance();
    private Boolean needUpdate = false;
    public BookService() {
    }

    public static BookService getInstance() {
        if (instance == null) {
            return instance = new BookService();
        }
        return instance;
    }

    Book readBook() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());

        ISBN isbn = null;
        try {
            isbn = isbnService.readISBN();
        } catch (ISBNFormatException e) {
            e.printStackTrace();
        }

        System.out.println("Enter title");
        String title = read.next();
        System.out.println("Enter pages");
        Integer pages = read.nextInt();
        System.out.println("Enter author");
        Author author = authorService.readAuthor();
        System.out.println("Pick a genre");
        BookType[] bookTypes = BookType.values();
        for (int i = 0; i < bookTypes.length; i++) {
            System.out.println(i + ": " + bookTypes[i] + "\n");
        }

        System.out.println("Pick the number of booktype");
        Integer i = read.nextInt();

        BookType bookType = bookTypes[i];
        System.out.println("Publishing house");
        String publishingHouse = read.next();


        Book a = new Book(isbn, title, pages, author, bookType, publishingHouse);
        writeCSV(a);
        return a;
    }

    public Book getBookByTitle() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        System.out.println("Title of the book you are looking for");
        String title = read.next();
        for (Book book1 : instance.books) {

            if (book1.getTitle().equals(title)) {
                return book1;
            }
        }
        return null;
    }

    public void readCSV() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        try {
            var columns = reader.getCSVcolumns(".\\src\\data\\book.csv");
            for (var column : columns) {
                ISBN isbn = new ISBN(column[0]);
                Author aut = new Author(column[3], column[4]);
                Integer pages = Integer.parseInt(column[2]);
                BookType book = BookType.valueOf(column[5]);
                books.add(new Book(isbn, column[1], pages, aut, book, column[6]));
            }
        } catch (ISBNFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeCSV(Book a) {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        List<String> toWrite = new ArrayList<>();
        toWrite.addAll(Arrays.asList(a.getIsbn().getIsbn(), a.getTitle(), String.valueOf(a.getPages()), a.getAuthor().getFirst_name(), a.getAuthor().getLast_name(), String.valueOf(a.getBookType()), a.getPublishingHouse()));
        write.writeCSV(".\\src\\data\\book.csv", toWrite);
    }

    public void updateCSV() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        try {
            var file = new FileWriter(".\\src\\data\\book.csv");

            for (var book : books) {
                writeCSV(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(Boolean needUpdate) {
        this.needUpdate = needUpdate;
    }
}
