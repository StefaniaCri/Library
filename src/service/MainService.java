package service;

import entity.Book.*;
import entity.Users.*;
import repository.ClientRepository;
import repository.LibrarianRepository;

import java.util.*;

public class MainService {

    protected static User loggedIn = null;
    protected static MainService instance = null;
    private AuditService auditService = AuditService.getInstance();
    private LibrarianRepository librarianRepository = new LibrarianRepository();
    private ClientRepository clientRepository = new ClientRepository();

    public MainService() {
    }

    public static MainService getInstance() {
        if (instance == null) {
            return instance = new MainService();
        }
        return instance;
    }

    static List<User> usersList = new ArrayList<>();
    static Scanner read = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();
    static List<Author> authors = new ArrayList<>();

    User addUser() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        var id = 1;
        System.out.println("Enter your name: ");
        String name = read.next();

        System.out.println("Enter your surname");
        String surname = read.next();

        System.out.println("Enter your username");
        String username = read.next();

        System.out.println("Enter your email");
        String email = read.next();

        System.out.println("Enter your password");
        String password = read.next();

        return new User(name, surname, username, email, password);

    }

    List<User> sortList() {
        Collections.sort(usersList);
        return usersList;
    }

    public void logInAccount(String email, String password) {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn == null) {
            User user = isExistingEmail(email, password);
            if (user != null) {
                System.out.println("You have an account");
                loggedIn = user;
            } else {
                System.out.println("Invalid log in");
            }
        } else {
            System.out.println("You are logged in account");
        }
    }

    public void logOut() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        loggedIn = null;
    }

    public User isExistingEmail(String email, String password) {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        System.out.println(usersList);
        for (User user : instance.usersList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    public void changePassword() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        if (loggedIn != null) {
            System.out.println("Current password:");
            String password = read.next();
            if (loggedIn.getPassword().equals(password)) {
                System.out.println("Your new password?");
                String newpassword = read.next();
                if (loggedIn instanceof Librarian){
                    librarianRepository.changeLibrarianPassword((Librarian) loggedIn,newpassword);
                }
                if (loggedIn instanceof Client){
                    clientRepository.changeClientPassword((Client) loggedIn,newpassword);
                }
                loggedIn.setPassword(newpassword);
                System.out.println("Your password has been changed sucesfully!");
            } else {
                System.out.println("Incorrect password");
            }
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
