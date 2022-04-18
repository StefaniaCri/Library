package Users;

import Book.Book;
import Book.Author;
import Book.BookType;
import Users.User;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Client extends User {
    private String phoneNumber;
    private HashMap<String, List<Book>> rentedBooks = new HashMap<String, List<Book>>();
    private Set<Book> favouriteBooks = new HashSet<Book>();
    private List<Author> favouriteAuthors = new ArrayList<Author>();

    public Client(String name, String surname, String username, String email, String password, String phoneNumber) {
        super(name, surname, username, email, password);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashMap<String, List<Book>> getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(HashMap<String, List<Book>> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    public Set<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public void setFavouriteBooks(Set<Book> favouriteBooks) {
        this.favouriteBooks = favouriteBooks;
    }

    public List<Author> getFavouriteAuthors() {
        return favouriteAuthors;
    }

    public void setFavouriteAuthors(List<Author> favouriteAuthors) {
        this.favouriteAuthors = favouriteAuthors;
    }




    @Override
    public String toString() {
        return "Client{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}