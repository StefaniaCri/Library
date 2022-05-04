package service;

import entity.Book.Author;
import entity.Book.Book;
import entity.Users.Librarian;
import entity.Users.User;
import service.Read.LibrarianReadFromCSV;
import service.Write.LibrarianWriteToCSV;

import java.util.List;

public class LibrarianService extends MainService {
    protected static LibrarianService instance = null;
    private final BookService bookService = BookService.getInstance();
    private final AuthorService authorService = AuthorService.getInstance();
    private final LibrarianWriteToCSV writeToCSV = LibrarianWriteToCSV.getInstance();
    private final List<Librarian> librarians;
    private final AuditService auditService = AuditService.getInstance();
    private final LibrarianReadFromCSV reader = LibrarianReadFromCSV.getInstance();


    private LibrarianService() {
        librarians = reader.readCSV();
        usersList.addAll(librarians);
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
        writeToCSV.writeCSV(a);
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
    void updateCSV()
    {
        writeToCSV.updateCSV(librarians);
    }
}
