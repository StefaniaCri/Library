package service;

import entity.Book.Author;
import entity.Book.Book;
import entity.Book.ISBNFormatException;
import entity.Users.Librarian;
import entity.Users.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibrarianService extends MainService {
    protected static LibrarianService instance = null;
    private BookService bookService = BookService.getInstance();
    private AuthorService authorService = AuthorService.getInstance();
    private WriteToCSV writeToCSV = WriteToCSV.getInstance();
    private List<Librarian> librarians = new ArrayList<>();
    private AuditService auditService = AuditService.getInstance();
    private ReadFromCSV reader = ReadFromCSV.getInstance();
    private Boolean needUpdate = false;

    public LibrarianService() {
    }

    public static LibrarianService getInstance() {
        if (instance == null) {
            return instance = new LibrarianService();
        }
        return instance;
    }

    public Librarian createLibrarian() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        User a = addUser();
        writeCSV(a);
        loggedIn = new Librarian(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword());
        usersList.add(loggedIn);
        librarians.add((Librarian) loggedIn);
        return (Librarian) loggedIn;
    }

    public void seeUsers() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn instanceof Librarian) {
            List<User> users = instance.sortList();
            System.out.println(users);
        } else System.out.println("You are not logged in an account");
    }

    public void addBook() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn instanceof Librarian) {
            Book book = bookService.readBook();
            instance.books.add(book);
        } else System.out.println("You are not logged in an account");

    }

    public void editBook() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn instanceof Librarian) {
            System.out.println("Title of the book you are looking for");
            String title = read.next();
            //pentru simplificarea idei voi considera ca bibliotecarul va cauta dupa titlul cartii
            //de asemenea va putea edita doar numarul de pagini
            for (Book book1 : instance.books) {

                if (book1.getTitle().equals(title)) {
                    Integer pages = read.nextInt();
                    book1.setPages(pages);
                }
            }
        } else System.out.println("You are not logged in an account");

    }

    public void removeBook() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn instanceof Librarian) {
            System.out.println("Title of the book you are looking for");
            String title1 = read.next();
            instance.books.removeIf(book2 -> book2.getTitle().equals(title1));
        } else System.out.println("You are not logged in an account");

    }

    public void addAuthor() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn instanceof Librarian) {
            Author a = authorService.readAuthor();
            instance.authors.add(a);
        } else System.out.println("You are not logged in an account");
    }

    public void removeAuthor() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn instanceof Librarian) {
            System.out.println("First name of the author you are looking for");
            String first_name = read.next();
            System.out.println("Last name of the author you are looking for");
            String lasr_name = read.next();
            instance.authors.removeIf(author -> author.getFirst_name().equals(first_name) && author.getLast_name().equals(lasr_name));
        } else System.out.println("You are not logged in an account");

    }

    public void readCSV() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        var columns = reader.getCSVcolumns(".\\src\\data\\librarian.csv");
        for (var column : columns) {
            librarians.add(new Librarian(column[0], column[1], column[2], column[3], column[4]));
        }
        usersList.addAll(librarians);
    }

    public void writeCSV(User a) {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        List<String> toWrite = new ArrayList<>();
        toWrite.addAll(Arrays.asList(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword()));
        writeToCSV.writeCSV(".\\src\\data\\librarian.csv", toWrite);
    }

    public void updateCSV() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        try {
            var file = new FileWriter(".\\src\\data\\librarian.csv");

            for (var librarian : librarians) {
                writeCSV(librarian);
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
