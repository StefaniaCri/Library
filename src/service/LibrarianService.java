package service;

import entity.Book.Author;
import entity.Book.Book;
import entity.Book.ISBNFormatException;
import entity.Users.Librarian;
import entity.Users.User;

import java.util.List;

public class LibrarianService extends MainService{
    protected static LibrarianService instance = null;
    private BookService bookService = BookService.getInstance();
    private AuthorService authorService = AuthorService.getInstance();

    public LibrarianService() {
    }

    public static LibrarianService getInstance() {
        if (instance == null) {
            return instance = new LibrarianService();
        }
        return instance;
    }

    public Librarian createLibrarian() {
        User a = addUser();
        loggedIn = new Librarian(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword());
        usersList.add(loggedIn);
        return (Librarian) loggedIn;
    }

    public static void seeUsers() {
        if (loggedIn instanceof Librarian) {
            List<User> users = instance.sortList();
            System.out.println(users);
        } else System.out.println("You are not logged in an account");
    }

    public void addBook() throws ISBNFormatException {
        if (loggedIn instanceof Librarian) {
            Book book = bookService.readBook();
            instance.books.add(book);
        } else System.out.println("You are not logged in an account");

    }

    public void editBook() {
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
        if (loggedIn instanceof Librarian) {
            System.out.println("Title of the book you are looking for");
            String title1 = read.next();
            instance.books.removeIf(book2 -> book2.getTitle().equals(title1));
        } else System.out.println("You are not logged in an account");

    }

    public void addAuthor() {
        if (loggedIn instanceof Librarian) {
            Author a = authorService.readAuthor();
            instance.authors.add(a);
        } else System.out.println("You are not logged in an account");
    }

    public void removeAuthor() {
        if (loggedIn instanceof Librarian) {
            System.out.println("First name of the author you are looking for");
            String first_name = read.next();
            System.out.println("Last name of the author you are looking for");
            String lasr_name = read.next();
            instance.authors.removeIf(author -> author.getFirst_name().equals(first_name) && author.getLast_name().equals(lasr_name));
        } else System.out.println("You are not logged in an account");

    }

}
